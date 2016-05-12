package name.zeno.zervice.model.wexin;

import name.zeno.zervice.model.BaseModel;
import org.nutz.json.JsonField;

@SuppressWarnings("unused")
public class BaseAccessToken extends BaseModel
{
  @JsonField("access_token")
  private String accessToken;
  @JsonField("expires_in")
  private int    expiresIn;
  @JsonField("errcode")
  private int    errCode;
  @JsonField("errmsg")
  private String errMsg;

  public String getAccessToken()
  {
    return accessToken;
  }

  public void setAccessToken(String accessToken)
  {
    this.accessToken = accessToken;
  }

  public int getExpiresIn()
  {
    return expiresIn;
  }

  public void setExpiresIn(int expiresIn)
  {
    this.expiresIn = expiresIn;
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
