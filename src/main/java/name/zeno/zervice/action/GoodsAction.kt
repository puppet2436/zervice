package name.zeno.zervice.action

import name.zeno.zervice.dao.entity.Goods
import name.zeno.zervice.form.GoodsForm
import name.zeno.zervice.logic.GoodsLogic
import name.zeno.zervice.model.ApiResult
import org.nutz.ioc.loader.annotation.Inject
import org.nutz.ioc.loader.annotation.IocBean
import org.nutz.mvc.annotation.*

@IocBean
@At("/goods")
class GoodsAction {
  @Inject
  lateinit private var goodsLogic: GoodsLogic

  @GET @At("/")
  fun get(): ApiResult<Goods> {
    val result: ApiResult<Goods>

    result = ApiResult<Goods>()
    result.setData(goodsLogic.query())
    return result
  }

  @POST @At("/")
  fun create(@Param("param") form: GoodsForm, @Param("param") origin: String)
      : ApiResult<Goods> {
    val result = ApiResult<Goods>()
    val goods = form.body ?: throw IllegalArgumentException("body == null")
    result.setData(goodsLogic.create(goods))
    return result
  }

  @PUT @At("/?")
  fun update(goodsId: Long,
             @Param("param") form: GoodsForm,
             @Param("param") origin: String): ApiResult<Goods> {
    val result = ApiResult<Goods>()
    val goods = form.body ?: throw IllegalArgumentException("body == null")
    goods.goodsId = goodsId
    result.setData(goodsLogic.update(goods))
    return result
  }

  @DELETE @At("/?")
  fun delete(goodsId: Long): ApiResult<*> {
    val result = ApiResult<Unit>()
    goodsLogic.delete(goodsId)
    return result
  }
}
