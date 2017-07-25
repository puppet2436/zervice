package name.zeno.zervice.constants

enum class Client private constructor(var key: String, var value: String) {
  ANDROID("android", "安卓"),
  WECHAT("wechat", "微信"),
  WEB("web", "浏览器");

  companion object {
    fun getByKey(key: String?): Client? {
      if (key == null) return null;

      for (client in Client.values()) {
        if (client.key == key)
          return client
      }
      return null
    }
  }
}
