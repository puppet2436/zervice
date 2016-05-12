package name.zeno.zervice.form;

@SuppressWarnings("unused")
public class SignForm extends ApiForm<SignForm.Sign>
{
  protected Sign body;

  public static class Sign
  {
    private String identityType;
    private String identifier;
    private String credential;

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
  }
}
