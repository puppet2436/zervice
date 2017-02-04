package name.zeno.zervice.model.wexin;

import name.zeno.zervice.model.BaseModel;
import org.nutz.json.JsonField;

/**
 * @since 2016-05-04 11:31:03
 */
public class AccessToken extends BaseModel
{

  public static final String SCOPE_BASE = "snsapi_base";
  public static final String SCOPE_USER_INFO = "snsapi_userinfo";

  @JsonField("unionid")
  private String unionId;         //只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
  @JsonField("openid")
  private String openId;
  @JsonField("access_token")
  private String accessToken;

  private String scope;
  @JsonField("expires_in")
  private String expiresIn;
  @JsonField("refresh_token")
  private String refreshToken;

  @JsonField("errcode")
  private int errCode;
  @JsonField("errmsg")
  private String errMsg;

  public String getUnionId()
  {
    return unionId;
  }

  public void setUnionId(String unionId)
  {
    this.unionId = unionId;
  }

  public String getOpenId()
  {
    return openId;
  }

  public void setOpenId(String openId)
  {
    this.openId = openId;
  }

  public String getAccessToken()
  {
    return accessToken;
  }

  public void setAccessToken(String accessToken)
  {
    this.accessToken = accessToken;
  }

  public String getScope()
  {
    return scope;
  }

  public void setScope(String scope)
  {
    this.scope = scope;
  }

  public String getExpiresIn()
  {
    return expiresIn;
  }

  public void setExpiresIn(String expiresIn)
  {
    this.expiresIn = expiresIn;
  }

  public String getRefreshToken()
  {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken)
  {
    this.refreshToken = refreshToken;
  }

  public int getErrCode()
  {
    return errCode;
  }

  public void setErrCode(int errCode)
  {
    this.errCode = errCode;
  }

  public String getErrMsg()
  {
    return errMsg;
  }

  public void setErrMsg(String errMsg)
  {
    this.errMsg = errMsg;
  }
}
