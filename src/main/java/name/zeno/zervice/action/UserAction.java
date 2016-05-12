package name.zeno.zervice.action;

import name.zeno.zervice.dao.entity.User;
import name.zeno.zervice.exception.LogicException;
import name.zeno.zervice.form.DeFaultForm;
import name.zeno.zervice.form.SignForm;
import name.zeno.zervice.logic.UserLogic;
import name.zeno.zervice.model.ApiResult;
import org.apache.log4j.Logger;
import org.nutz.ioc.aop.Aop;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;

@SuppressWarnings("WeakerAccess")
@IocBean
@At("/users")
public class UserAction
{
  @Inject
  private BaseAction baseAction;
  @Inject
  private UserLogic  userLogic;

  private final Logger logger = Logger.getLogger(UserAction.class);

  @GET
  @At("")
  public ApiResult<User> getUsers()
  {
    return ApiResult.success(userLogic.getUsers());
  }

  /**
   * 注册,仅支持用户名注册
   */
  @POST
  @At("")
  public ApiResult<User> signUp(@Param("param") SignForm form,
                                @Param("param") String origin)
  {
    ApiResult<User> result;
    try {
      baseAction.validApiForm(form, origin);
      result = ApiResult.success(userLogic.signUp(form.getBody()));
    } catch (LogicException e) {
      logger.error(e.getMessage(), e);
      //noinspection unchecked
      result = ApiResult.error(e.getMessage(), e.getErrCode());
    }
    return result;
  }

  /**
   * 登录
   */
  @POST
  @At("/sign-in")
  @Aop("actionLog")
  public ApiResult<User> signIn(@Param("param") SignForm form,
                                @Param("param") String origin)
  {
    ApiResult<User> result;
    try {
      baseAction.validClient(form, origin);
      result = ApiResult.success(userLogic.signIn(form));
    } catch (LogicException e) {
      logger.error(e.getMessage(), e);
      //noinspection unchecked
      result = ApiResult.error(e.getMessage(), e.getErrCode());
    }

    return result;
  }

  @POST
  @At("/sign-out")
  @Aop("actionLog")
  public ApiResult signOut(@Param("param") DeFaultForm form, @Param("param") String origin)
  {
    ApiResult result;
    try {
      baseAction.validClient(form, origin);
      userLogic.signOut(form);
      result = ApiResult.success();
    } catch (LogicException e) {
      e.printStackTrace();
      result = ApiResult.error(e.getMessage(), e.getErrCode());
    }
    return result;
  }

}
