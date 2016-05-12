package name.zeno.zervice.aop;

import org.apache.log4j.Logger;
import org.nutz.aop.InterceptorChain;
import org.nutz.ioc.loader.annotation.IocBean;

@SuppressWarnings("WeakerAccess")
@IocBean
public class ActionLog extends MethodLog
{
  Logger logger = Logger.getLogger(ActionLog.class);

  @Override
  public void filter(InterceptorChain chain) throws Throwable
  {
    logger.info(super.buildMethodArgsReturn(chain));
  }
}
