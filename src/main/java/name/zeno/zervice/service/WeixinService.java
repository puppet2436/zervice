package name.zeno.zervice.service;

import name.zeno.zervice.exception.LogicException;
import name.zeno.zervice.model.wexin.AccessToken;
import name.zeno.zervice.model.wexin.ErrorCode;
import name.zeno.zervice.model.wexin.WXUser;
import org.apache.log4j.Logger;
import org.nutz.http.Http;
import org.nutz.ioc.aop.Aop;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;

@SuppressWarnings("unused")
@IocBean
public class WeixinService
{
  private static Logger logger = Logger.getLogger(WeixinService.class);

  //获取基础 access token
  @Aop("serviceLog")
  public AccessToken getBaseAccessToken(String appId, String appSecret)
  {
    String url = String.format("https://api.weixin.qq.com/cgi-bin/token?" +
        "grant_type=client_credential&appid=%s&secret=%s", appId, appSecret);
    String tokenStr = Http.get(url, 2000).getContent();
    return Json.fromJson(AccessToken.class, tokenStr);
  }

  //获取用户 access token
  @Aop("serviceLog")
  public AccessToken getUserAccessToken(String appId, String appSecret, String code)
  {
    String url = String.format("https://api.weixin.qq.com/sns/oauth2/access_token?" +
        "appid=%s&secret=%s&code=%s&grant_type=authorization_code", appId, appSecret, code);
    String tokenStr = Http.get(url, 2000).getContent();
    return Json.fromJson(AccessToken.class, tokenStr);
  }

  /**
   * 由于access_token拥有较短的有效期，当access_token超时后，可以使用refresh_token进行刷新，
   * refresh_token拥有较长的有效期（7天、30天、60天、90天），当refresh_token失效的后，需要用户重新授权。
   */
  @Aop("serviceLog")
  public AccessToken refreshToken(String appId, String refreshToken)
  {
    String url = String.format("https://api.weixin.qq.com/sns/oauth2/refresh_token?"
        + "appid=%s&grant_type=refresh_token&refresh_token=%s", appId, refreshToken);
    return Json.fromJson(AccessToken.class, Http.get(url, 2000).getContent());
  }

  /**
   * 如果网页授权作用域为snsapi_userinfo，或者用户已关注公众号时,
   * 开发者可以通过access_token和openid拉取用户信息了。
   */
  @Aop("serviceLog")
  public WXUser getAuthUserInfo(String userAccessToken, String openId)
  {
    String url = String.format("https://api.weixin.qq.com/sns/userinfo?" +
        "access_token=%s&openid=%s&lang=zh_CN", userAccessToken, openId);
    return Json.fromJson(WXUser.class, Http.get(url, 2000).getContent());
  }

  /**
   * @see <a href='http://mp.weixin.qq.com/wiki/1/8a5ce6257f1d3b2afb20f83e72b72ce9.html'>获取用户基本信息</a>
   */
  @Aop("serviceLog")
  public WXUser getUserInfo(String baseAccessToken, String openId) throws LogicException
  {
    String url = String.format("https://api.weixin.qq.com/cgi-bin/user/info?" +
        "access_token=%s&openid=%s&lang=zh_CN", baseAccessToken, openId);
    WXUser user = Json.fromJson(WXUser.class, Http.get(url, 2000).getContent());
    if (user.getErrorCode() != null) {
      int errorCode = user.getErrorCode();
      throw new LogicException(ErrorCode.Companion.getDescByCode(errorCode), errorCode);
    }

    return user;
  }

}
