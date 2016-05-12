package name.zeno.zervice.dao.entity;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Readonly;
import org.nutz.dao.entity.annotation.Table;

import java.sql.Timestamp;

@Table("t_token")
public class Token
{
  @Id @Column("token_id") private         long      tokenId;
  @Column("user_id") private              long      userId;
  @Column private                         String    token;
  @Column private                         String    client;
  @Readonly @Column("created_at") private Timestamp createdAt;

  public long getTokenId()
  {
    return tokenId;
  }

  public long getUserId()
  {
    return userId;
  }

  public String getToken()
  {
    return token;
  }

  public String getClient()
  {
    return client;
  }

  public Timestamp getCreatedAt()
  {
    return createdAt;
  }

  public Token setTokenId(long tokenId)
  {
    this.tokenId = tokenId;
    return this;
  }

  public Token setUserId(long userId)
  {
    this.userId = userId;
    return this;
  }

  public Token setToken(String token)
  {
    this.token = token;
    return this;
  }

  public Token setClient(String client)
  {
    this.client = client;
    return this;
  }

  public Token setCreatedAt(Timestamp createdAt)
  {
    this.createdAt = createdAt;
    return this;
  }
}
