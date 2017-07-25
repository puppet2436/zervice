package name.zeno.zervice.aop

import org.nutz.aop.InterceptorChain
import org.nutz.aop.MethodInterceptor

abstract class MethodLog : MethodInterceptor {
  @Throws(Throwable::class)
  fun buildMethodArgsReturn(chain: InterceptorChain): String {
    val builder = StringBuilder()
    builder.append("\r\n\tmethod: ").append(chain.callingMethod)
    builder.append("\r\n\targs  : ")
    val args = chain.args
    for (arg in args) {
      builder.append(arg).append(",")
    }
    chain.doChain()// 继续执行其他拦截器
    builder.append("\r\n\treturn: ")
        .append(chain.`return`)
        .append("\r\n------------------------------------------------------\r\n")
    return builder.toString()
  }
}
