package name.zeno.zervice.dao.entity;

import name.zeno.zervice.constants.MySQL;
import org.nutz.dao.DB;
import org.nutz.dao.entity.annotation.*;

import java.sql.Timestamp;

@SuppressWarnings("unused")
@Table("t_user_auth")
public class UserAuth
{
  @Id @Next(@SQL(db = DB.MYSQL, value = MySQL.SQL_GET_NEW_ID))
  @Column("user_auth_id") private  Long      userAuthId;
  @Column("user_id") private       Long      userId;
  @Column("identity_type") private String    identityType;
  @Column private                  String    identifier;
  @Column private                  String    credential;
  @Column("refresh_token") private String    refreshToken;
  @Readonly
  @Column("created_at") private    Timestamp createdAt;
  @Readonly
  @Column("updated_at") private    Timestamp updatedAt;

  @One(target = User.class, field = "userId")
  private User user;

  public Long getUserAuthId()
  {
    return userAuthId;
  }

  public void setUserAuthId(Long userAuthId)
  {
    this.userAuthId = userAuthId;
  }

  public Long getUserId()
  {
    return userId;
  }

  public void setUserId(Long userId)
  {
    this.userId = userId;
  }

  public String getIdentityType()
  {
    return identityType;
  }

  public void setIdentityType(String identityType)
  {
    this.identityType = identityType;
  }

  public String getIdentifier()
  {
    return identifier;
  }

  public void setIdentifier(String identifier)
  {
    this.identifier = identifier;
  }

  public String getCredential()
  {
    return credential;
  }

  public void setCredential(String credential)
  {
    this.credential = credential;
  }

  public String getRefreshToken()
  {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken)
  {
    this.refreshToken = refreshToken;
  }

  public Timestamp getCreatedAt()
  {
    return createdAt;
  }

  public void setCreatedAt(Timestamp createdAt)
  {
    this.createdAt = createdAt;
  }

  public Timestamp getUpdatedAt()
  {
    return updatedAt;
  }

  public void setUpdatedAt(Timestamp updatedAt)
  {
    this.updatedAt = updatedAt;
  }

  public User getUser()
  {
    return user;
  }

  public void setUser(User user)
  {
    this.user = user;
  }
}
