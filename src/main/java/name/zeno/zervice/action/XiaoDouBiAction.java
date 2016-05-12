package name.zeno.zervice.action;

import name.zeno.zervice.logic.XiaoDouBiLogic;
import name.zeno.zervice.service.WeixinService;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;

@SuppressWarnings("unused")
@IocBean
@At("xiaodoubi")
public class XiaoDouBiAction
{
  @Inject
  private XiaoDouBiLogic xiaoDouBiLogic;

  @Inject
  private WeixinService weixinService;

  @At("/chat/?")
  public Object chat(String chat)
  {
    return xiaoDouBiLogic.chat(chat);
  }
}
