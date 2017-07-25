package name.zeno.zervice.logic

import name.zeno.zervice.constants.IdentityType
import name.zeno.zervice.dao.UserAuthDao
import name.zeno.zervice.dao.entity.UserAuth
import name.zeno.zervice.model.wexin.AccessToken
import org.nutz.ioc.loader.annotation.Inject
import org.nutz.ioc.loader.annotation.IocBean

@IocBean
class UserAuthLogic {

  @Inject private lateinit var userAuthDao: UserAuthDao

  fun create(userAuth: UserAuth): UserAuth = userAuthDao.insert(userAuth)
  fun update(userAuth: UserAuth): Int = userAuthDao.update(userAuth)

  fun getWechatAuth(openId: String): UserAuth
      = userAuthDao.querySameUserIdentifier(IdentityType.WEIXIN.code, openId)

  fun fetchLinkUser(userAuth: UserAuth): UserAuth = userAuthDao.fetchLinkUser(userAuth)

  fun saveWechatAuth(token: AccessToken): UserAuth {
    var auth: UserAuth? = getWechatAuth(token.openId)
    if (auth == null) {
      auth = UserAuth(credential = token.accessToken, refreshToken = token.refreshToken)
      userAuthDao.insert(auth)
    } else {
      auth.credential = token.accessToken
      auth.refreshToken = token.refreshToken
      userAuthDao.update(auth)
    }
    return auth
  }

}
