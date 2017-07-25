package name.zeno.zervice.constants

import org.nutz.json.Json

class WeixinConf private constructor(val TOKEN: String, val APP_ID: String, val APP_SECRET: String) {

  private var accessTokenExpired: Long = 0

  var accessToken: String? = null
    set(accessToken) {
      field = accessToken
      this.accessTokenExpired = System.currentTimeMillis() + 432000000
    }

  fun isAccessTokenExpired(): Boolean = accessTokenExpired < System.currentTimeMillis() - 900000L

  override fun toString(): String {
    return Json.toJson(this)
  }

  companion object {
    @JvmStatic
    var instance: WeixinConf? = null
      private set

    @Synchronized fun init(token: String, appId: String, appSecret: String) {
      synchronized(WeixinConf::class.java) {
        instance = WeixinConf(token, appId, appSecret)
      }
    }
  }

}
