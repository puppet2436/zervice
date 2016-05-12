package name.zeno.zervice.form;

@SuppressWarnings({"WeakerAccess", "unused"})
public abstract class ApiForm<T>
{
  protected T      body;
  private   Head   head;
  private   String sign;

  public Head getHead()
  {
    return head;
  }

  public void setHead(Head head)
  {
    this.head = head;
  }

  public T getBody()
  {
    return body;
  }

  public void setBody(T body)
  {
    this.body = body;
  }

  public String getSign()
  {
    return sign;
  }

  public void setSign(String sign)
  {
    this.sign = sign;
  }

  public static class Head
  {
    private Long   userId;
    private String token;
    private String client;
    private Long   timestamp;

    public Long getUserId()
    {
      return userId;
    }

    public void setUserId(Long userId)
    {
      this.userId = userId;
    }

    public String getToken()
    {
      return token;
    }

    public void setToken(String token)
    {
      this.token = token;
    }

    public Long getTimestamp()
    {
      return timestamp;
    }

    public void setTimestamp(Long timestamp)
    {
      this.timestamp = timestamp;
    }

    public String getClient()
    {
      return client;
    }

    public void setClient(String client)
    {
      this.client = client;
    }
  }
}
