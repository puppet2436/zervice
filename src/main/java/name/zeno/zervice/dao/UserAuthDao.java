package name.zeno.zervice.dao;

import name.zeno.zervice.dao.entity.UserAuth;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

@SuppressWarnings("unused")
@IocBean
public class UserAuthDao
{
  @Inject
  private BaseDao<UserAuth> baseDao;

  public UserAuth insert(UserAuth userAuth)
  {
    return baseDao.insert(userAuth);
  }

  public UserAuth insertWithUser(UserAuth userAuth)
  {
    return baseDao.insertWith(userAuth, "user");
  }

  public int update(UserAuth userAuth)
  {
    return baseDao.update(userAuth);
  }

  public int delete(UserAuth userAuth)
  {
    return baseDao.delete(userAuth);
  }

  /**
   * 查找注册类型和注册标识都匹配的授权
   */
  public UserAuth querySameUserIdentifier(String type, String identifier)
  {
    Cnd cnd = Cnd.where("identity_type", "=", type).and("identifier", "=", identifier);
    return baseDao.fetch(UserAuth.class, cnd);
  }

  /**
   * 授权信息
   */
  public UserAuth authorization(String identifier, String credential)
  {
    Cnd cnd = Cnd.where("identifier", "=", identifier).and("credential", "=", credential);
    return baseDao.fetch(UserAuth.class, cnd);
  }

  public UserAuth fetchLinkUser(UserAuth userAuth)
  {
    return baseDao.fetchLinks(userAuth, "user");
  }
}
