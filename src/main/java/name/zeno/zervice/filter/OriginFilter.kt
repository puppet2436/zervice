package name.zeno.zervice.filter

import org.nutz.mvc.ActionContext
import org.nutz.mvc.View
import org.nutz.mvc.filter.CrossOriginFilter

class OriginFilter : CrossOriginFilter() {
  override fun match(ac: ActionContext): View {
    ac.response.addHeader("Access-Control-Allow-Origin", "http://mjtown.cn")
    return super.match(ac)
  }
}
