package name.zeno.zervice.filter;

import org.nutz.mvc.ActionContext;
import org.nutz.mvc.View;
import org.nutz.mvc.filter.CrossOriginFilter;

import javax.servlet.http.HttpServletResponse;

public class OriginFilter extends CrossOriginFilter
{
  @Override
  public View match(ActionContext ac)
  {
    HttpServletResponse resp = ac.getResponse();
    resp.addHeader("Access-Control-Allow-Origin", "http://mjtown.cn");
    return super.match(ac);
  }
}
