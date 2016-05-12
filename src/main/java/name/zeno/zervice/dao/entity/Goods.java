package name.zeno.zervice.dao.entity;

import org.nutz.dao.entity.annotation.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Table("t_goods")
public class Goods
{
  @Id(auto = true) @Column("goods_id") private Long       goodsId;
  @Column private                              String     title;
  @Column("picture") private                   String     picture;
  @Column private                              BigDecimal cost;
  @Column("sell_price") private                BigDecimal sellPrice;
  @Column("inventory") private                 Short      inventory;
  @Readonly @Column("created_at") private      Timestamp  createdAt;
  @Readonly @Column("updated_at") private      Timestamp  updatedAt;


  public Long getGoodsId()
  {
    return goodsId;
  }

  public void setGoodsId(Long goodsId)
  {
    this.goodsId = goodsId;
  }

  public String getTitle()
  {
    return title;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  public String getPicture()
  {
    return picture;
  }

  public void setPicture(String picture)
  {
    this.picture = picture;
  }

  public BigDecimal getCost()
  {
    return cost;
  }

  public void setCost(BigDecimal cost)
  {
    this.cost = cost;
  }

  public BigDecimal getSellPrice()
  {
    return sellPrice;
  }

  public void setSellPrice(BigDecimal sellPrice)
  {
    this.sellPrice = sellPrice;
  }

  public Short getInventory()
  {
    return inventory;
  }

  public void setInventory(Short inventory)
  {
    this.inventory = inventory;
  }

  public Timestamp getCreatedAt()
  {
    return createdAt;
  }

  public void setCreatedAt(Timestamp createdAt)
  {
    this.createdAt = createdAt;
  }

  public Timestamp getUpdatedAt()
  {
    return updatedAt;
  }

  public void setUpdatedAt(Timestamp updatedAt)
  {
    this.updatedAt = updatedAt;
  }
}
