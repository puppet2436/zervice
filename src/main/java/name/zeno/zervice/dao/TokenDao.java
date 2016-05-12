package name.zeno.zervice.dao;

import name.zeno.zervice.dao.entity.Token;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

@SuppressWarnings("unused")
@IocBean
public class TokenDao
{
  private static final Class<Token> tClass = Token.class;

  @Inject private BaseDao<Token> baseDao;

  public Token insert(Token token)
  {
    return baseDao.insert(token);
  }

  public Token fetch(long userId, String client)
  {
    Cnd cnd = Cnd.where("user_id", "=", userId).and("client", "=", client);
    return baseDao.fetch(tClass, cnd);
  }


  public int delete(long id)
  {
    return baseDao.delete(tClass, id);
  }

  public int delete(long userId, String client)
  {
    Cnd cnd = Cnd.where("user_id", "=", userId).and("client", "=", client);
    return baseDao.clear(tClass, cnd);
  }

}
