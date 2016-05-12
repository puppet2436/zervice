package name.zeno.zervice.dao;

import name.zeno.zervice.constants.MySQL;
import org.nutz.dao.Cnd;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.impl.sql.NutSql;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;

@SuppressWarnings({"WeakerAccess", "unused"})
@IocBean
public class BaseDao<T>
{
  private static Sql sql = new NutSql(MySQL.SQL_SET_NAMES);

  @Inject private NutDao dao;

  public T insert(T t)
  {
    dao.execute(sql);
    return dao.insert(t);
  }

  public T insertWith(T t, String regex)
  {
    dao.execute(sql);
    return dao.insertWith(t, regex);
  }

  public int update(T t)
  {
    dao.execute(sql);
    return dao.update(t);
  }

  public T fetch(Class<T> tClass, long id)
  {
    return dao.fetch(tClass, id);
  }

  public T fetch(Class<T> clazz, Cnd cnd)
  {
    return dao.fetch(clazz, cnd);
  }

  public List<T> query(Class<T> clazz, Cnd cnd)
  {
    return dao.query(clazz, cnd);
  }

  public int delete(Class<T> tClass, long id)
  {
    return dao.delete(tClass, id);
  }

  public int delete(T t)
  {
    return dao.delete(t);
  }

  public T fetchLinks(T t, String regex)
  {
    return dao.fetchLinks(t, regex);
  }

  public int clear(Class<T> tClass, Cnd cnd)
  {
    return dao.clear(tClass,cnd);
  }
}

