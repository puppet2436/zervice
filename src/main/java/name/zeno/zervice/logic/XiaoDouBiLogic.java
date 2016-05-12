package name.zeno.zervice.logic;

import org.apache.log4j.Logger;
import org.nutz.http.Http;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.HashMap;
import java.util.Map;

@IocBean
public class XiaoDouBiLogic
{
  private static final String XIAODOUBI_API = "http://www.xiaodoubi.com/bot/chat.php";

  private Logger logger = Logger.getLogger(XiaoDouBiLogic.class);

  private Map<String, Object> params = new HashMap<>();

  public String chat(String chat)
  {
    String result = "";
    if (chat.contains("谁") && chat.contains("最漂亮")) {
      result = "万万小凉块~ 快去加她微信哦~ ";
    } else {
      params.clear();
      params.put("chat", chat);
      try {
        result = Http.post(XIAODOUBI_API, params, 2000);
        result = result.replace("<br>", "\r");
        System.out.println(result);
      } catch (Exception e) {
        logger.error(e);
      }
    }
    return result;
  }
}
