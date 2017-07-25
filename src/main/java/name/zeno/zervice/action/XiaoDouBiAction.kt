package name.zeno.zervice.action

import name.zeno.zervice.logic.XiaoDouBiLogic
import name.zeno.zervice.service.WeixinService
import org.nutz.ioc.loader.annotation.Inject
import org.nutz.ioc.loader.annotation.IocBean
import org.nutz.mvc.annotation.At

@IocBean
@At("xiaodoubi")
class XiaoDouBiAction {
  @Inject lateinit var xiaoDouBiLogic: XiaoDouBiLogic
  @Inject lateinit var weixinService: WeixinService

  @At("/chat/?")
  fun chat(chat: String): Any {
    return xiaoDouBiLogic.chat(chat)
  }
}
