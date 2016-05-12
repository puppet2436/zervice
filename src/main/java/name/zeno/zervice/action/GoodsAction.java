package name.zeno.zervice.action;

import name.zeno.zervice.dao.entity.Goods;
import name.zeno.zervice.form.GoodsForm;
import name.zeno.zervice.logic.GoodsLogic;
import name.zeno.zervice.model.ApiResult;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.*;

@IocBean
@At("/goods")
public class GoodsAction
{
    @Inject
    private GoodsLogic goodsLogic;

    @GET
    @At("/")
    public ApiResult<Goods> get()
    {
        ApiResult<Goods> result;

        result = new ApiResult<>();
        result.setData(goodsLogic.query(null));
        return result;
    }

    @POST
    @At("/")
    public ApiResult<Goods> create(@Param("param") GoodsForm form,
                                   @Param("param") String origin)
    {
        ApiResult<Goods> result = new ApiResult<>();
        result.setData(goodsLogic.create(form.getBody()));
        return result;
    }

    @PUT
    @At("/?")
    public ApiResult<Goods> update(long goodsId,
                                   @Param("param") GoodsForm form,
                                   @Param("param") String origin)
    {
        ApiResult<Goods> result = new ApiResult<>();
        Goods goods = form.getBody();
        goods.setGoodsId(goodsId);
        result.setData(goodsLogic.update(goods));
        return result;
    }

    @DELETE
    @At("/?")
    public ApiResult delete(long goodsId)
    {
        ApiResult result = new ApiResult();
        goodsLogic.delete(goodsId);
        return result;
    }
}
