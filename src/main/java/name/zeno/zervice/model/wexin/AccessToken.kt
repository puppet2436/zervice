package name.zeno.zervice.model.wexin

import name.zeno.zervice.annontations.DataClass
import org.nutz.json.JsonField

/**
 * @since 2016-05-04 11:31:03
 */

@DataClass
data class AccessToken constructor(
    //只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
    @JsonField("unionid") val unionId: String? = null,

    @JsonField("openid") val openId: String = "",
    @JsonField("access_token") val accessToken: String? = null,

    private val scope: String? = null,
    @JsonField("expires_in") val expiresIn: String? = null,
    @JsonField("refresh_token") val refreshToken: String? = null,
    @JsonField("errcode") val errCode: Int = 0,
    @JsonField("errmsg") val errMsg: String? = null
) {

  companion object {
    const val SCOPE_BASE = "snsapi_base"
    const val SCOPE_USER_INFO = "snsapi_userinfo"
  }
}
