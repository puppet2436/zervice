package name.zeno.zervice.action;

import name.zeno.zervice.exception.LogicException;
import name.zeno.zervice.logic.WeixinLogic;
import name.zeno.zervice.model.ApiResult;
import name.zeno.zervice.model.wexin.EventMessage;
import name.zeno.zervice.model.wexin.Message;
import name.zeno.zervice.model.wexin.TextMessage;
import name.zeno.zervice.model.wexin.VoiceMessage;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.nutz.ioc.aop.Aop;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;

/**
 * 微信公众号入口
 */
@SuppressWarnings({"unused", "WeakerAccess"})
@At("/wx")
@IocBean
public class WXAction
{
  private static Logger logger = Logger.getLogger(WXAction.class);

  @Inject
  private WeixinLogic weixinLogic;

  /**
   * 微信验证服务器地址的有效性
   * <p>
   * 加密/校验流程如下：
   * 1. 将token、timestamp、nonce三个参数进行字典序排序
   * 2. 将三个参数字符串拼接成一个字符串进行sha1加密
   * 3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
   */
  @GET
  @Ok("raw")
  @At("/message")
  @Aop("actionLog")
  public String authorization(
      @Param("signature") String signature,
      @Param("timestamp") String timestamp,
      @Param("nonce") String nonce,
      @Param("echostr") String echostr)
  {
    logger.info(String.format("get /wx/message {signature:%s,timestamp:%s," +
        "nonce:%s,echostr:%s}", signature, timestamp, nonce, echostr));
    String result;
    if (weixinLogic.authorization(signature, timestamp, nonce)) {
      result = echostr;
    } else {
      result = "auth failed";
    }
    return result;
  }

  @POST
  @Ok("raw")
  @At("/message")
  @Aop("actionLog")
  public String message(@Param("signature") String signature,
                        @Param("timestamp") String timestamp,
                        @Param("nonce") String nonce,
                        HttpServletRequest request)
  {
    logger.info(String.format("post /wx/message {signature:%s,timestamp:%s,nonce:%s}",
        signature, timestamp, nonce));
    if (!weixinLogic.authorization(signature, timestamp, nonce)) {
      return "auth failed";
    }

    try {
      //read msg
      InputStream is     = request.getInputStream();
      SAXReader   reader = new SAXReader();
      reader.setEncoding("UTF-8");
      Document document = reader.read(is);
      logger.info(document.asXML());
      Message msg = weixinLogic.parseMessage(document);

      //response
      String result;
      switch (msg.getMsgType()) {
        case Message.MSG_TYPE_EVENT:
          result = weixinLogic.responseEventMsg((EventMessage) msg);
          break;
        case Message.MSG_TYPE_TEXT:
          TextMessage textMsg = (TextMessage) msg;
          result = weixinLogic.getChatResponse(textMsg.getContent(),
              textMsg.getToUserName(), textMsg.getFromUserName())
              .toXMLString();
          break;
        case Message.MSG_TYPE_VOICE:
          VoiceMessage voiceMessage = (VoiceMessage) msg;
          String rec = voiceMessage.getRecognition();
          if (rec != null && !rec.isEmpty()) {
            logger.info(voiceMessage.getRecognition());
            result = weixinLogic.getChatResponse(rec,
                voiceMessage.getToUserName(), voiceMessage.getFromUserName())
                .toXMLString();
          } else {
            TextMessage re = new TextMessage();
            re.setToUserName(msg.getFromUserName());
            re.setFromUserName(msg.getToUserName());
            re.setContent("我把耳朵捂住了,听不见");
            result = re.toXMLString();
          }
          break;
        default:
          TextMessage message = new TextMessage();
          message.setToUserName(msg.getFromUserName());
          message.setFromUserName(msg.getToUserName());
          message.setContent("我把眼睛闭上了,看不见");
          result = message.toXMLString();
          break;
      }
      return result;
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      return null;
    }
  }

  @GET
  @At("/user/?")
  @Aop({"actionLog"})
  public ApiResult getUserInfo(String code)
  {
    try {
      return ApiResult.success(weixinLogic.getUserInfo(code));
    } catch (LogicException e) {
      return ApiResult.error(e.getMessage(), e.getErrCode());
    }
  }
}
