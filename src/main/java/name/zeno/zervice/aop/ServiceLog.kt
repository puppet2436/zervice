package name.zeno.zervice.aop

import org.apache.log4j.Logger
import org.nutz.aop.InterceptorChain
import org.nutz.ioc.loader.annotation.IocBean

@IocBean
class ServiceLog : MethodLog() {
  internal var logger = Logger.getLogger(ServiceLog::class.java)

  @Throws(Throwable::class)
  override fun filter(chain: InterceptorChain) {
    logger.debug(super.buildMethodArgsReturn(chain))
  }
}
