package name.zeno.zervice.logic;

import name.zeno.zervice.dao.GoodsDao;
import name.zeno.zervice.dao.entity.Goods;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;

@IocBean
public class GoodsLogic
{
    @Inject
    private GoodsDao goodsDao;

    public List<Goods> query(Pager pager)
    {
        return goodsDao.query();
    }

    public Goods create(Goods goods)
    {
        return goodsDao.insert(goods);
    }

    public Goods update(Goods goods)
    {
        return goodsDao.update(goods);
    }

    public int delete(long goodsId)
    {
        return goodsDao.delete(goodsId);
    }
}
