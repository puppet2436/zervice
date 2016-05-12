package name.zeno.zervice.dao;

import name.zeno.zervice.dao.entity.User;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;

@IocBean
public class UserDao
{
  @Inject
  private BaseDao<User> baseDao;

  public User insert(User user)
  {
    return baseDao.insert(user);
  }

  public List<User> query(Cnd cnd)
  {
    return baseDao.query(User.class, cnd);
  }

  public User querySignedIn(long userId, String client)
  {
    Cnd cnd = Cnd.where("user_id", "=", userId).and("token_client", "=", client);
    return baseDao.fetch(User.class, cnd);
  }

  public User fetch(long userId)
  {
    return baseDao.fetch(User.class, userId);
  }
}
