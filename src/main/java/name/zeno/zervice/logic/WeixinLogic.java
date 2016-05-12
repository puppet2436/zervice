package name.zeno.zervice.logic;

import name.zeno.zervice.constants.IdentityType;
import name.zeno.zervice.constants.WeixinConf;
import name.zeno.zervice.dao.entity.User;
import name.zeno.zervice.dao.entity.UserAuth;
import name.zeno.zervice.exception.LogicException;
import name.zeno.zervice.model.wexin.*;
import name.zeno.zervice.service.WeixinService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.nutz.ioc.aop.Aop;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;

import java.util.Arrays;
import java.util.Date;

/**
 * 微信业务
 */
@SuppressWarnings({"FieldCanBeLocal", "unused"})
@IocBean
public class WeixinLogic
{
  private static Logger logger = Logger.getLogger(WeixinLogic.class);

  @Inject private UserLogic      userLogic;
  @Inject private UserAuthLogic  userAuthLogic;
  @Inject private XiaoDouBiLogic xiaoDouBiLogic;
  @Inject private WeixinService  weixinService;

  /**
   * 验证消息真实性
   * <p>
   * <p>加密/校验流程如下：
   * <p>1. 将token、timestamp、nonce三个参数进行字典序排序
   * <p>2. 将三个参数字符串拼接成一个字符串进行sha1加密
   * <p>3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
   *
   * @param signature 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
   * @param timestamp 时间戳
   * @param nonce     随机数
   */
  public boolean authorization(String signature, String timestamp, String nonce)
  {
    if (signature == null || timestamp == null || nonce == null)
      return false;

    String[] str = {WeixinConf.getInstance().TOKEN, timestamp, nonce};
    Arrays.sort(str); // 字典序排序
    String bigStr = str[0] + str[1] + str[2];
    // SHA1加密
    String digest = DigestUtils.sha1Hex(bigStr.getBytes()).toLowerCase();
    // 确认请求来至微信
    return digest.equals(signature);
  }

  public Message parseMessage(final Document document)
  {
    String  msgType = document.selectSingleNode("/xml/MsgType").getText();
    Message message = Message.createMessage(msgType);
    message.parseXML(document);
    return message;
  }

  public String responseEventMsg(EventMessage eventMessage) throws LogicException
  {
    String result = null;
    if (EventMessage.EVENT_SUBSCRIBE.equals(eventMessage.getEvent())) {
      //关注
      result = getWelcomeMessage(eventMessage.getToUserName(), eventMessage.getFromUserName()).toXMLString();
      onSubscribe(eventMessage.getFromUserName());
    } else if (EventMessage.EVENT_UNSUBSCRIBE.equals(eventMessage.getEvent())) {
      //取消关注
      onUnsubscribe(eventMessage.getFromUserName());
    }

    return result;
  }

  public TextMessage getChatResponse(String chat, String from, String to)
  {
    TextMessage message = new TextMessage();
    message.setFromUserName(from);
    message.setToUserName(to);
    message.setContent(xiaoDouBiLogic.chat(chat));
    if (message.getContent().contains("xiaodouqqcom")) {
      message.setContent("把木家【微信号: mujiastory】推荐给号友,一起来和木家玩耍哦~");
    }
    return message;
  }

  public String getImageMessage(TextMessage m)
  {
    Document r    = DocumentHelper.createDocument();
    Element  root = r.addElement("xml");
    root.addElement("ToUserName").addCDATA(m.getFromUserName());
    root.addElement("FromUserName").addCDATA(m.getToUserName());
    root.addElement("CreateTime").setText(String.valueOf(new Date().getTime()));
    root.addElement("MsgType").addCDATA(Message.MSG_TYPE_NEWS);
    root.addElement("ArticleCount").setText(String.valueOf(2));
    Element articles = root.addElement("Articles");
    Element itemOne  = articles.addElement("item");
    itemOne.addElement("Title").addCDATA("command 2");
    itemOne.addElement("PicUrl")
        .addCDATA("http://img0.bdstatic.com/img/image/shouye/lyj/sheying1219.jpg");
    itemOne.addElement("Url").addCDATA("http://www.baidu.com/");
    Element itemTwo = articles.addElement("item");
    itemTwo.addElement("Title").addCDATA("图文消息");
    itemTwo.addElement("PicUrl")
        .addCDATA("http://img0.bdstatic.com/img/image/shouye/lyj/sheying1219.jpg");
    itemTwo.addElement("Url").addCDATA("http://www.baidu.com/");

    return r.asXML();
  }

  public User getUserInfo(String code) throws LogicException
  {
    User user;

    WeixinConf  conf  = WeixinConf.getInstance();
    AccessToken token = weixinService.getUserAccessToken(conf.APP_ID, conf.APP_SECRET, code);
    if (ErrorCode.ERROR_00000.code != token.getErrCode()) {
      throw new LogicException(ErrorCode.getDescByCode(token.getErrCode()), token.getErrCode());
    }
    UserAuth auth = userAuthLogic.getWechatAuth(token.getOpenId());
    if (auth != null) {
      userAuthLogic.fetchLinkUser(auth);
      user = auth.getUser();
      //if access token 过期,刷新 access token
      if (auth.getUpdatedAt().getTime() + 7200000 < System.currentTimeMillis()) {
        AccessToken newToken = weixinService.refreshToken(
            WeixinConf.getInstance().APP_ID, auth.getRefreshToken());
        auth.setCredential(token.getAccessToken());
        auth.setRefreshToken(token.getRefreshToken());
        userAuthLogic.update(auth);
      }
    } else {
      final User u = new User();
      Trans.exec((Atom) () -> {
        WXUser wxUser = weixinService.getAuthUserInfo(token.getAccessToken(), token.getOpenId());
        u.setNickName(wxUser.getNickName());
        u.setAvatar(wxUser.getHeadImgUrl());
        userLogic.createUser(u);

        UserAuth au = new UserAuth();
        au.setIdentityType(IdentityType.WEIXIN.code);
        au.setIdentifier(token.getOpenId());
        au.setCredential(token.getAccessToken());
        au.setRefreshToken(token.getRefreshToken());
        au.setUserId(u.getUserId());
        userAuthLogic.create(au);
      });
      user = u;
    }
    return user;
  }

  private TextMessage getWelcomeMessage(String from, String to)
  {
    TextMessage message = new TextMessage();
    message.setFromUserName(from);
    message.setToUserName(to);
    message.setContent("只与有趣人,共享自然事. 欢迎关注木家");
    return message;
  }

  @Aop("logicLog")
  private WXUser onSubscribe(String openId) throws LogicException
  {
    return weixinService.getUserInfo(getAccessToken(), openId);
  }

  @Aop("logicLog")
  private WXUser onUnsubscribe(String openId) throws LogicException
  {
    return weixinService.getUserInfo(getAccessToken(), openId);
  }

  private String getAccessToken()
  {
    WeixinConf wxConf = WeixinConf.getInstance();
    String     baseAccessToken;
    if (wxConf.isAccessTokenExpired()) {
      synchronized (WeixinConf.class) {
        if (wxConf.isAccessTokenExpired()) {
          AccessToken token =
              weixinService.getBaseAccessToken(wxConf.APP_ID, wxConf.APP_SECRET);
          baseAccessToken = token.getAccessToken();
          wxConf.setAccessToken(baseAccessToken);
        } else {
          baseAccessToken = wxConf.getAccessToken();
        }
      }
    } else {
      baseAccessToken = wxConf.getAccessToken();
    }

    return baseAccessToken;
  }

}
