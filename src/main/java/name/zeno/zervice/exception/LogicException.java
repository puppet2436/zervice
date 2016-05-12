package name.zeno.zervice.exception;

public class LogicException extends Exception
{
  public static final int CODE_INVALID_IDENTITY_TYPE            = 1;
  public static final int CODE_INVALID_IDENTIFIER               = 2;
  public static final int CODE_USER_NAME_EXIST                  = 3;
  public static final int CODE_INVALID_IDENTIFIER_OR_CREDENTIAL = 4;
  public static final int CODE_INVALID_PARAM                    = 5;
  public static final int CODE_INVALID_TOKEN                    = 6;


  private int errCode;

  public int getErrCode()
  {
    return errCode;
  }

  public LogicException(int errCode)
  {
    this.errCode = errCode;
  }

  public LogicException(String message, int errCode)
  {
    super(message);
    this.errCode = errCode;
  }

  public LogicException(String message, Throwable cause, int errCode)
  {
    super(message, cause);
    this.errCode = errCode;
  }

  public LogicException(Throwable cause, int errCode)
  {
    super(cause);
    this.errCode = errCode;
  }

  public LogicException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int errCode)
  {
    super(message, cause, enableSuppression, writableStackTrace);
    this.errCode = errCode;
  }
}
