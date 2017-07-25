package name.zeno.zervice.model.wexin;

import lombok.Data;
import lombok.EqualsAndHashCode;
import name.zeno.zervice.model.BaseModel;
import org.nutz.json.JsonField;

@SuppressWarnings("unused")
@Data
@EqualsAndHashCode(callSuper = false)
public class WXUser extends BaseModel
{
  @JsonField("unionid") private    String unionId;
  @JsonField("openid") private     String openId;
  @JsonField("nickname") private   String nickName;
  @JsonField("headimgurl") private String headImgUrl;

  private                       String remark;//公众号对粉丝的备注
  @JsonField("groupid") private Long   groupId;//用户所在分组id

  private int sex;

  private String province;
  private String city;
  private String country;

  private String language;//用户的语言

  private                              Integer subscribe;//0:未关注,拉取不到其余信息
  @JsonField("subscribe_time") private long    subscribeTime;//10位时间戳

  @JsonField("errcode") private Integer errorCode;
  @JsonField("errmsg") private  String  errorMsg;
}
