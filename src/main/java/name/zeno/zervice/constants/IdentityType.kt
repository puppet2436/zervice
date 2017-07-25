package name.zeno.zervice.constants


import name.zeno.zervice.exception.LogicException
import java.util.regex.Pattern

enum class IdentityType private constructor(val code: String, val value: String) {
  WEIXIN("weixin", "微信"),
  PHONE("phone", "手机号"),
  EMAIL("email", "邮箱"),
  USER_NAME("username", "用户名");


  companion object {

    private val PHONE_PATTERN = Pattern.compile("(^1([34578])[0-9]\\d{8}$)")
    private val EMAIL_PATTERN = Pattern.compile("^\\w+((-w+)|(\\.\\w+))*\\@[A-Za-z0-9]+(([.-])[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$")
    private val USER_NAME_PATTERN = Pattern.compile("^[\\u4e00-\\u9fa5a-zA-Z0-9_-]{3,16}$")

    fun getByCode(code: String): IdentityType? {
      var r: IdentityType? = null
      for (identityType in IdentityType.values()) {
        if (identityType.code == code) {
          r = identityType
          break
        }
      }
      return r
    }

    @JvmStatic
    fun getByIdentifier(identifier: String): IdentityType {
      val type: IdentityType
      if (PHONE_PATTERN.matcher(identifier).matches()) {
        type = PHONE
      } else if (EMAIL_PATTERN.matcher(identifier).matches()) {
        type = EMAIL
      } else if (USER_NAME_PATTERN.matcher(identifier).matches()) {
        type = USER_NAME
      } else {
        throw LogicException("invalid identity", LogicException.CODE_INVALID_IDENTIFIER)
      }

      return type
    }
  }
}
