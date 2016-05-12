package name.zeno.zervice.aop;

import org.nutz.aop.InterceptorChain;
import org.nutz.aop.MethodInterceptor;

@SuppressWarnings("WeakerAccess")
public abstract class MethodLog implements MethodInterceptor
{
  public String buildMethodArgsReturn(InterceptorChain chain) throws Throwable
  {
    StringBuilder builder = new StringBuilder();
    builder.append("\r\n\tmethod: ").append(chain.getCallingMethod());
    builder.append("\r\n\targs  : ");
    Object[] args = chain.getArgs();
    for (Object arg : args) {
      builder.append(arg).append(",");
    }
    chain.doChain();// 继续执行其他拦截器
    builder.append("\r\n\treturn: ")
        .append(chain.getReturn())
        .append("\r\n------------------------------------------------------\r\n");
    return builder.toString();
  }
}
