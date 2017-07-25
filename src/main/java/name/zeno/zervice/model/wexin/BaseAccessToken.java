package name.zeno.zervice.model.wexin;

import lombok.Data;
import lombok.EqualsAndHashCode;
import name.zeno.zervice.model.BaseModel;
import org.nutz.json.JsonField;

@SuppressWarnings("unused")
@Data
@EqualsAndHashCode(callSuper = false)
public class BaseAccessToken extends BaseModel
{
  private @JsonField("access_token") String accessToken;
  private @JsonField("expires_in")   int    expiresIn;
  private @JsonField("errcode")      int    errCode;
  private @JsonField("errmsg")       String errMsg;
}
