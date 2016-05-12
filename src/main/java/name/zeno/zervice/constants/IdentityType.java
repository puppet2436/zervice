package name.zeno.zervice.constants;


import name.zeno.zervice.exception.LogicException;
import org.jetbrains.annotations.Contract;

import java.util.regex.Pattern;

public enum IdentityType
{
  WEIXIN("weixin", "微信"),
  PHONE("phone", "手机号"),
  EMAIL("email", "邮箱"),
  USER_NAME("username", "用户名");

  private static final Pattern PHONE_PATTERN     = Pattern.compile("(^1(3|4|5|7|8)[0-9]\\d{8}$)");
  private static final Pattern EMAIL_PATTERN     = Pattern.compile(
      "^\\w+((-w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$");
  private static final Pattern USER_NAME_PATTERN = Pattern.compile("^[\\u4e00-\\u9fa5a-zA-Z0-9_-]{3,16}$");

  public final String code;
  public final String value;

  IdentityType(String code, String value)
  {
    this.code = code;
    this.value = value;
  }

  public static IdentityType getByCode(String code)
  {
    IdentityType r = null;
    for (IdentityType identityType : IdentityType.values()) {
      if (identityType.code.equals(code)) {
        r = identityType;
        break;
      }
    }
    return r;
  }

  @Contract("null -> fail")
  public static IdentityType getByIdentifier(String identifier) throws LogicException
  {
    IdentityType type;
    if (identifier == null) {
      throw new LogicException("identity can not be null", LogicException.CODE_INVALID_IDENTIFIER);
    }
    if (PHONE_PATTERN.matcher(identifier).matches()) {
      type = PHONE;
    } else if (EMAIL_PATTERN.matcher(identifier).matches()) {
      type = EMAIL;
    } else if (USER_NAME_PATTERN.matcher(identifier).matches()) {
      type = USER_NAME;
    } else {
      throw new LogicException("invalid identity", LogicException.CODE_INVALID_IDENTIFIER);
    }

    return type;
  }
}
