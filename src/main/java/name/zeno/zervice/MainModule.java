package name.zeno.zervice;

import name.zeno.zervice.filter.OriginFilter;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

@Ok("json")
@Fail("raw")
@IocBy(type = ComboIocProvider.class, args = {
    //扫描name.zeno.zervice包中所有的注解，作为AnnotationIocLoader的配置，@IocBean标明可以被容器管理的类，@Inject标明可以被注入的字段
    "*org.nutz.ioc.loader.annotation.AnnotationIocLoader", "name.zeno.zervice",
    //扫描ioc文件夹中的*.js或者*.json文件，作为JsonLoader的配置文件
    "*org.nutz.ioc.loader.json.JsonLoader", "/ioc/"
})
@Encoding(input = "UTF-8", output = "UTF-8")
@Modules(packages = "name.zeno.zervice.action", scanPackage = true)
@Filters(@By(type = OriginFilter.class))
@SetupBy(ZerviceSetup.class)
public class MainModule
{
}
