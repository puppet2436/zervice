package name.zeno.zervice;

import name.zeno.zervice.constants.WeixinConf;
import org.apache.log4j.Logger;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;

import java.io.IOException;
import java.util.Properties;

@SuppressWarnings("WeakerAccess")
public class ZerviceSetup implements Setup
{
  private static Logger logger = Logger.getLogger(ZerviceSetup.class);

  @Override
  public void init(NutConfig nc)
  {
    loadWechatConf();
  }

  @Override
  public void destroy(NutConfig nc)
  {

  }

  private void loadWechatConf()
  {
    try {
      Properties properties = new Properties();
      properties.load(this.getClass().getResourceAsStream("/wx.properties"));
      String token     = (String) properties.get("token");
      String appId     = (String) properties.get("app_id");
      String appSecret = (String) properties.get("app_secret");
      WeixinConf.Companion.init(token, appId, appSecret);
      logger.info(WeixinConf.getInstance());
    } catch (IOException e) {
      throw new RuntimeException("初始化微信公众号配置异常", e);
    }
  }
}
