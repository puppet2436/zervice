package name.zeno.zervice.exception

class LogicException : Exception {


  var errCode: Int = 0
    private set

  constructor(errCode: Int) {
    this.errCode = errCode
  }

  constructor(message: String, errCode: Int) : super(message) {
    this.errCode = errCode
  }

  constructor(message: String, cause: Throwable, errCode: Int) : super(message, cause) {
    this.errCode = errCode
  }

  constructor(cause: Throwable, errCode: Int) : super(cause) {
    this.errCode = errCode
  }

  constructor(message: String, cause: Throwable, enableSuppression: Boolean, writableStackTrace: Boolean, errCode: Int) : super(message, cause, enableSuppression, writableStackTrace) {
    this.errCode = errCode
  }

  companion object {
    @JvmField val CODE_INVALID_IDENTITY_TYPE = 1
    @JvmField val CODE_INVALID_IDENTIFIER = 2
    @JvmField val CODE_USER_NAME_EXIST = 3
    @JvmField val CODE_INVALID_IDENTIFIER_OR_CREDENTIAL = 4
    @JvmField val CODE_INVALID_PARAM = 5
    @JvmField val CODE_INVALID_TOKEN = 6
  }
}
