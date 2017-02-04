package name.zeno.zervice.form;

import name.zeno.zervice.dao.entity.Goods;

public class GoodsForm extends ApiForm<Goods>
{
  private Goods body;

  @Override
  public Goods getBody()
  {
    return body;
  }

  @Override
  public void setBody(Goods body)
  {
    this.body = body;
  }
}
