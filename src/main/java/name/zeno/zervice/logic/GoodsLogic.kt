package name.zeno.zervice.logic

import name.zeno.zervice.dao.GoodsDao
import name.zeno.zervice.dao.entity.Goods
import org.nutz.dao.pager.Pager
import org.nutz.ioc.loader.annotation.Inject
import org.nutz.ioc.loader.annotation.IocBean

@IocBean
class GoodsLogic {
  @Inject
  lateinit private var goodsDao: GoodsDao

  fun query(pager: Pager?=null): List<Goods> = goodsDao.query()
  fun create(goods: Goods): Goods = goodsDao.insert(goods)
  fun update(goods: Goods): Goods = goodsDao.update(goods)
  fun delete(goodsId: Long): Int = goodsDao.delete(goodsId)
}
