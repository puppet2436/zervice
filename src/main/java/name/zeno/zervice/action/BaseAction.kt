package name.zeno.zervice.action

import com.sun.istack.internal.NotNull
import name.zeno.zervice.constants.Client
import name.zeno.zervice.constants.Common
import name.zeno.zervice.exception.LogicException
import name.zeno.zervice.form.ApiForm
import org.nutz.ioc.loader.annotation.IocBean

@IocBean
class BaseAction {
  @Throws(LogicException::class)
  fun validApiForm(form: ApiForm<*>?, origin: String) {
    if (form == null) {
      throw LogicException("param == null", LogicException.CODE_INVALID_PARAM)
    }
    if (Common.SIGN != form.sign) {
      throw LogicException("param sign error", LogicException.CODE_INVALID_PARAM)
    }
    if (form.body == null) {
      throw LogicException("param body == null", LogicException.CODE_INVALID_PARAM)
    }
  }

  @Throws(LogicException::class)
  fun validClient(@NotNull form: ApiForm<*>, origin: String) {
    validApiForm(form, origin)
    if (form.head == null) {
      throw LogicException("param head == null", LogicException.CODE_INVALID_PARAM)
    }

    if (Client.getByKey(form.head?.client) == null) {
      throw LogicException("param client invalid", LogicException.CODE_INVALID_PARAM)
    }
  }
}
