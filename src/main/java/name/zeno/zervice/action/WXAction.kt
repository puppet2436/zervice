package name.zeno.zervice.action

import name.zeno.zervice.dao.entity.User
import name.zeno.zervice.exception.LogicException
import name.zeno.zervice.logic.WeixinLogic
import name.zeno.zervice.model.ApiResult
import name.zeno.zervice.model.wexin.EventMessage
import name.zeno.zervice.model.wexin.Message
import name.zeno.zervice.model.wexin.TextMessage
import name.zeno.zervice.model.wexin.VoiceMessage
import org.apache.log4j.Logger
import org.dom4j.io.SAXReader
import org.nutz.ioc.aop.Aop
import org.nutz.ioc.loader.annotation.Inject
import org.nutz.ioc.loader.annotation.IocBean
import org.nutz.mvc.annotation.*
import javax.servlet.http.HttpServletRequest

/**
 * 微信公众号入口
 */
@At("/wx")
@IocBean
class WXAction {

  @Inject
  lateinit private var weixinLogic: WeixinLogic

  /**
   * 微信验证服务器地址的有效性
   *
   *
   * 加密/校验流程如下：
   * 1. 将token、timestamp、nonce三个参数进行字典序排序
   * 2. 将三个参数字符串拼接成一个字符串进行sha1加密
   * 3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
   */
  @GET
  @Ok("raw")
  @At("/message")
  @Aop("actionLog")
  fun authorization(
      @Param("signature") signature: String,
      @Param("timestamp") timestamp: String,
      @Param("nonce") nonce: String,
      @Param("echostr") echostr: String): String {
    logger.info("get /wx/message {signature:$signature,timestamp:$timestamp,nonce:$nonce,echostr:$echostr}")
    val result: String
    if (weixinLogic.authorization(signature, timestamp, nonce)) {
      result = echostr
    } else {
      result = "auth failed"
    }
    return result
  }

  @POST
  @Ok("raw")
  @At("/message")
  @Aop("actionLog")
  fun message(@Param("signature") signature: String,
              @Param("timestamp") timestamp: String,
              @Param("nonce") nonce: String,
              request: HttpServletRequest): String? {
    logger.info(String.format("post /wx/message {signature:%s,timestamp:%s,nonce:%s}",
        signature, timestamp, nonce))
    if (!weixinLogic.authorization(signature, timestamp, nonce)) {
      return "auth failed"
    }

    try {
      //read msg
      val `is` = request.inputStream
      val reader = SAXReader()
      reader.encoding = "UTF-8"
      val document = reader.read(`is`)
      logger.info(document.asXML())
      val msg = weixinLogic.parseMessage(document)

      //response
      val result: String
      when (msg.msgType) {
        Message.MSG_TYPE_EVENT -> result = weixinLogic.responseEventMsg(msg as EventMessage)
        Message.MSG_TYPE_TEXT -> {
          val textMsg = msg as TextMessage
          result = weixinLogic.getChatResponse(textMsg.content,
              textMsg.toUserName, textMsg.fromUserName)
              .toXMLString()
        }
        Message.MSG_TYPE_VOICE -> {
          val voiceMessage = msg as VoiceMessage
          val rec = voiceMessage.recognition
          if (rec != null && !rec.isEmpty()) {
            logger.info(voiceMessage.recognition)
            result = weixinLogic.getChatResponse(rec,
                voiceMessage.toUserName, voiceMessage.fromUserName)
                .toXMLString()
          } else {
            val re = TextMessage()
            re.toUserName = msg.getFromUserName()
            re.fromUserName = msg.getToUserName()
            re.content = "我把耳朵捂住了,听不见"
            result = re.toXMLString()
          }
        }
        else -> {
          val message = TextMessage()
          message.toUserName = msg.fromUserName
          message.fromUserName = msg.toUserName
          message.content = "我把眼睛闭上了,看不见"
          result = message.toXMLString()
        }
      }
      return result
    } catch (e: Exception) {
      logger.error(e.message, e)
      return null
    }

  }

  @GET
  @At("/user/?")
  @Aop("actionLog")
  fun getUserInfo(code: String): ApiResult<*> {
    try {
      return ApiResult.success<User>(weixinLogic.getUserInfo(code))
    } catch (e: LogicException) {
      return ApiResult.error(e.message, e.errCode)
    }

  }

  companion object {
    private val logger = Logger.getLogger(WXAction::class.java)
  }
}
