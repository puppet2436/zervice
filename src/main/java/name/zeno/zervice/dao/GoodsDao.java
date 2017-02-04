package name.zeno.zervice.dao;

import name.zeno.zervice.dao.entity.Goods;
import org.jetbrains.annotations.NotNull;
import org.nutz.dao.Cnd;
import org.nutz.dao.impl.NutDao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;

@IocBean
public class GoodsDao
{
  @Inject
  private NutDao dao;

  private final Class<Goods> clazz = Goods.class;

  public Goods insert(Goods goods)
  {
    dao.insert(goods);
    return dao.fetch(goods);
  }

  public Goods update(Goods goods)
  {
    dao.update(goods);
    return dao.fetch(goods);
  }

  public List<Goods> query()
  {
    return dao.query(clazz, null);
  }

  public int delete(long goodsId)
  {
    return dao.delete(clazz, goodsId);
  }


  private @NotNull Cnd buildGoodsIdCnd(long goodsId)
  {
    return Cnd.where("goods_id", "=", goodsId);
  }

  private @NotNull Cnd buildGoodsIdCnd(Goods goods)
  {
    return buildGoodsIdCnd(goods.getGoodsId());
  }

}
