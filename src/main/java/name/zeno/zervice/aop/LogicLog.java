package name.zeno.zervice.aop;

import org.apache.log4j.Logger;
import org.nutz.aop.InterceptorChain;
import org.nutz.ioc.loader.annotation.IocBean;

@SuppressWarnings({"WeakerAccess", "unused"})
@IocBean
public class LogicLog extends MethodLog
{
  Logger logger = Logger.getLogger(LogicLog.class);

  @Override
  public void filter(InterceptorChain chain) throws Throwable
  {
    logger.debug(super.buildMethodArgsReturn(chain));
  }
}
