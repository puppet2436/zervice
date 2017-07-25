package name.zeno.zervice.action

import name.zeno.zervice.dao.entity.User
import name.zeno.zervice.exception.LogicException
import name.zeno.zervice.form.DeFaultForm
import name.zeno.zervice.form.SignForm
import name.zeno.zervice.logic.UserLogic
import name.zeno.zervice.model.ApiResult
import org.apache.log4j.Logger
import org.nutz.ioc.aop.Aop
import org.nutz.ioc.loader.annotation.Inject
import org.nutz.ioc.loader.annotation.IocBean
import org.nutz.mvc.annotation.At
import org.nutz.mvc.annotation.GET
import org.nutz.mvc.annotation.POST
import org.nutz.mvc.annotation.Param

@IocBean
@At("/users")
class UserAction {
  @Inject private lateinit var baseAction: BaseAction
  @Inject private lateinit var userLogic: UserLogic

  private val logger = Logger.getLogger(UserAction::class.java)

  val users: ApiResult<User>
    @GET
    @At("")
    get() = ApiResult.success(userLogic.users)

  /**
   * 注册,仅支持用户名注册
   */
  @POST
  @At("")
  fun signUp(@Param("param") form: SignForm,
             @Param("param") origin: String): ApiResult<User> {
    var result: ApiResult<User>
    try {
      baseAction.validApiForm(form, origin)
      val user = form.body ?: throw IllegalArgumentException("body == null")
      result = ApiResult.success(userLogic.signUp(user))
    } catch (e: LogicException) {
      logger.error(e.message, e)

      @Suppress("UNCHECKED_CAST")
      result = ApiResult.error(e.message, e.errCode) as ApiResult<User>
    }

    return result
  }

  /**
   * 登录
   */
  @POST
  @At("/sign-in")
  @Aop("actionLog")
  fun signIn(@Param("param") form: SignForm, @Param("param") origin: String)
      : ApiResult<User> {
    var result: ApiResult<User>
    try {
      baseAction.validClient(form, origin)
      result = ApiResult.success(userLogic!!.signIn(form))
    } catch (e: LogicException) {
      logger.error(e.message, e)
      @Suppress("UNCHECKED_CAST")
      result = ApiResult.error(e.message, e.errCode) as ApiResult<User>
    }

    return result
  }

  @POST
  @At("/sign-out")
  @Aop("actionLog")
  fun signOut(@Param("param") form: DeFaultForm, @Param("param") origin: String): ApiResult<*> {
    var result: ApiResult<*>
    try {
      baseAction.validClient(form, origin)
      userLogic!!.signOut(form)
      result = ApiResult.success<Any>()
    } catch (e: LogicException) {
      e.printStackTrace()
      result = ApiResult.error(e.message, e.errCode)
    }

    return result
  }

}
