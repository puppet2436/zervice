package name.zeno.zervice.constants;

import org.nutz.json.Json;

public class WeixinConf
{
  public final String TOKEN;
  public final String APP_ID;
  public final String APP_SECRET;

  private String accessToken;
  private long   accessTokenExpired;

  private static WeixinConf instance;

  private WeixinConf(String token, String appId, String appSecret)
  {
    this.TOKEN = token;
    this.APP_ID = appId;
    this.APP_SECRET = appSecret;
  }

  public static WeixinConf getInstance()
  {
    return instance;
  }

  public synchronized static void init(String token, String appId, String appSecret)
  {
    synchronized (WeixinConf.class) {
      instance = new WeixinConf(token, appId, appSecret);
    }
  }

  @Override
  public String toString()
  {
    return Json.toJson(this);
  }

  public String getAccessToken()
  {
    return accessToken;
  }

  public void setAccessToken(String accessToken)
  {
    this.accessToken = accessToken;
    this.accessTokenExpired = System.currentTimeMillis() + 432000000;
  }

  public boolean isAccessTokenExpired()
  {
    return accessTokenExpired < System.currentTimeMillis() - 900000L;
  }

}
