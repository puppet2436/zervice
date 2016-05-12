package name.zeno.zervice.logic;

import name.zeno.zervice.constants.IdentityType;
import name.zeno.zervice.dao.UserAuthDao;
import name.zeno.zervice.dao.entity.UserAuth;
import name.zeno.zervice.model.wexin.AccessToken;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

@SuppressWarnings({"WeakerAccess", "unused"})
@IocBean
public class UserAuthLogic
{

  @Inject private UserAuthDao userAuthDao;

  public UserAuth create(UserAuth userAuth)
  {
    return userAuthDao.insert(userAuth);
  }

  public int update(UserAuth userAuth)
  {
    return userAuthDao.update(userAuth);
  }

  public UserAuth getWechatAuth(String openId)
  {
    return userAuthDao.querySameUserIdentifier(IdentityType.WEIXIN.code, openId);
  }

  public UserAuth saveWechatAuth(AccessToken token)
  {
    UserAuth auth = getWechatAuth(token.getOpenId());
    if (auth == null) {
      auth = new UserAuth();
    } else {
      auth.setCredential(token.getAccessToken());
      auth.setRefreshToken(token.getRefreshToken());
    }
    return auth;
  }

  public UserAuth fetchLinkUser(UserAuth userAuth)
  {
    return userAuthDao.fetchLinkUser(userAuth);
  }

}
