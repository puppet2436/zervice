package name.zeno.zervice.dao.entity;

import name.zeno.zervice.constants.MySQL;
import org.nutz.dao.DB;
import org.nutz.dao.entity.annotation.*;

import java.sql.Timestamp;

@Table("t_user")
@View("v_user")
public class User
{
  @Id @Next(@SQL(db = DB.MYSQL, value = MySQL.SQL_GET_NEW_ID))
  @Column("user_id") private   Long   userId;
  @Column("nick_name") private String nickName;
  @Column private              String avatar;

  //from view
  @Readonly @Column private                     String    token;
  @Readonly @Column("token_client") private     String    tokenClient;
  @Readonly @Column("token_created_at") private Timestamp tokenCreatedAt;

  public Long getUserId()
  {
    return userId;
  }

  public void setUserId(Long userId)
  {
    this.userId = userId;
  }

  public String getNickName()
  {
    return nickName;
  }

  public void setNickName(String nickName)
  {
    this.nickName = nickName;
  }

  public String getAvatar()
  {
    return avatar;
  }

  public void setAvatar(String avatar)
  {
    this.avatar = avatar;
  }

  public String getToken()
  {
    return token;
  }

  public void setToken(String token)
  {
    this.token = token;
  }

  public Timestamp getTokenCreatedAt()
  {
    return tokenCreatedAt;
  }

  public void setTokenCreatedAt(Timestamp tokenCreatedAt)
  {
    this.tokenCreatedAt = tokenCreatedAt;
  }

  public String getTokenClient()
  {
    return tokenClient;
  }

  public void setTokenClient(String tokenClient)
  {
    this.tokenClient = tokenClient;
  }
}
