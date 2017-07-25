package name.zeno.zervice.logic

import com.sun.istack.internal.NotNull
import name.zeno.zervice.constants.IdentityType
import name.zeno.zervice.dao.TokenDao
import name.zeno.zervice.dao.UserAuthDao
import name.zeno.zervice.dao.UserDao
import name.zeno.zervice.dao.entity.Token
import name.zeno.zervice.dao.entity.User
import name.zeno.zervice.dao.entity.UserAuth
import name.zeno.zervice.exception.LogicException
import name.zeno.zervice.form.ApiForm
import name.zeno.zervice.form.SignForm
import org.nutz.dao.Cnd
import org.nutz.ioc.loader.annotation.Inject
import org.nutz.ioc.loader.annotation.IocBean
import org.nutz.trans.Atom
import org.nutz.trans.Trans
import java.util.UUID

@IocBean
class UserLogic {
  @Inject private lateinit var userDao: UserDao
  @Inject private lateinit var userAuthDao: UserAuthDao
  @Inject private lateinit var tokenDao: TokenDao

  @Throws(LogicException::class)
  fun signUp(@NotNull form: SignForm.Sign): User {
    val type = IdentityType.getByIdentifier(form.identifier!!)
    form.identityType = type.code

    //检查用户是否存在
    val dbAuth = userAuthDao.querySameUserIdentifier(form.identityType, form.identifier)
    if (dbAuth != null) {
      var errorMsg = ""
      if (type === IdentityType.PHONE) {
        errorMsg = "手机号码已被注册"
      } else if (type === IdentityType.EMAIL) {
        errorMsg = "邮箱已被注册"
      } else if (type === IdentityType.USER_NAME) {
        errorMsg = "用户名已被注册"
      }
      throw LogicException(errorMsg, LogicException.CODE_USER_NAME_EXIST)
    }


    val user = User()

    Trans.exec({
      user.avatar = ""
      user.nickName = ""
      userDao!!.insert(user)

      val auth = UserAuth()
      auth.credential = form.credential
      auth.identifier = form.identifier
      auth.identityType = form.identityType
      auth.user = user
      auth.userId = user.userId
      userAuthDao.insert(auth)
    } as Atom)

    return user
  }

  @Throws(LogicException::class)
  fun signIn(@NotNull form: SignForm): User {
    val body = form.body
    val type = IdentityType.getByIdentifier(body!!.identifier!!)
    body.identityType = type.code

    val (_, userId) = userAuthDao.authorization(body.identifier, body.credential) ?: throw LogicException("帐号或密码不正确", LogicException.CODE_INVALID_IDENTIFIER_OR_CREDENTIAL)

    val client = form.head!!.client
    tokenDao.delete(userId!!, client)
    val token = Token()
    token.userId = userId
    token.client = client
    token.token = UUID.randomUUID().toString()

    tokenDao.insert(token)
    return userDao.querySignedIn(userId, client)
  }

  @Throws(LogicException::class)
  fun signOut(@NotNull form: ApiForm<*>) {
    val head = form.head
    val dbToken = tokenDao.fetch(head!!.userId!!, head.client)
    if (dbToken == null) {
      throw LogicException("无效的TOKEN", LogicException.CODE_INVALID_TOKEN)
    } else {
      tokenDao.delete(dbToken.tokenId)
    }
  }

  fun createUser(user: User): User = userDao.insert(user)
  fun getUserById(userId: Long): User = userDao.fetch(userId)

  val users: List<User>
    get() = userDao.query(Cnd.NEW())

}
