package name.zeno.zervice.action;

import com.sun.istack.internal.NotNull;
import name.zeno.zervice.constants.Client;
import name.zeno.zervice.constants.Common;
import name.zeno.zervice.exception.LogicException;
import name.zeno.zervice.form.ApiForm;
import org.nutz.ioc.loader.annotation.IocBean;

@SuppressWarnings("WeakerAccess")
@IocBean
public class BaseAction
{
    public void validApiForm(ApiForm form, String origin) throws LogicException
    {
        if (form == null) {
            throw new LogicException("param == null", LogicException.CODE_INVALID_PARAM);
        }
        if (!Common.SIGN.equals(form.getSign())) {
            throw new LogicException("param sign error", LogicException.CODE_INVALID_PARAM);
        }
        if (form.getBody() == null) {
            throw new LogicException("param body == null", LogicException.CODE_INVALID_PARAM);
        }
    }

    public void validClient(@NotNull ApiForm form, String origin) throws LogicException
    {
        validApiForm(form, origin);
        if (form.getHead() == null) {
            throw new LogicException("param head == null", LogicException.CODE_INVALID_PARAM);
        }

        if (Client.getByKey(form.getHead().getClient()) == null) {
            throw new LogicException("param client invalid", LogicException.CODE_INVALID_PARAM);
        }
    }
}
