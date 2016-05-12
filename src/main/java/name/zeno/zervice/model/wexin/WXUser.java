package name.zeno.zervice.model.wexin;

import name.zeno.zervice.model.BaseModel;
import org.nutz.json.JsonField;

@SuppressWarnings("unused")
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

  public String getNickName()
  {
    return nickName;
  }

  public void setNickName(String nickName)
  {
    this.nickName = nickName;
  }

  public int getSex()
  {
    return sex;
  }

  public void setSex(int sex)
  {
    this.sex = sex;
  }

  public String getProvince()
  {
    return province;
  }

  public void setProvince(String province)
  {
    this.province = province;
  }

  public String getCity()
  {
    return city;
  }

  public void setCity(String city)
  {
    this.city = city;
  }

  public String getCountry()
  {
    return country;
  }

  public void setCountry(String country)
  {
    this.country = country;
  }

  public String getHeadImgUrl()
  {
    return headImgUrl;
  }

  public void setHeadImgUrl(String headImgUrl)
  {
    this.headImgUrl = headImgUrl;
  }

  public Integer getErrorCode()
  {
    return errorCode;
  }

  public void setErrorCode(Integer errorCode)
  {
    this.errorCode = errorCode;
  }

  public String getErrorMsg()
  {
    return errorMsg;
  }

  public void setErrorMsg(String errorMsg)
  {
    this.errorMsg = errorMsg;
  }

  public String getRemark()
  {
    return remark;
  }

  public void setRemark(String remark)
  {
    this.remark = remark;
  }

  public Long getGroupId()
  {
    return groupId;
  }

  public void setGroupId(Long groupId)
  {
    this.groupId = groupId;
  }

  public String getLanguage()
  {
    return language;
  }

  public void setLanguage(String language)
  {
    this.language = language;
  }

  public Integer getSubscribe()
  {
    return subscribe;
  }

  public void setSubscribe(Integer subscribe)
  {
    this.subscribe = subscribe;
  }

  public long getSubscribeTime()
  {
    return subscribeTime;
  }

  public void setSubscribeTime(long subscribeTime)
  {
    this.subscribeTime = subscribeTime;
  }
}
