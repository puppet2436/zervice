package name.zeno.zervice.dao.entity

import name.zeno.zervice.annontations.DataClass
import org.nutz.dao.entity.annotation.Column
import org.nutz.dao.entity.annotation.Id
import org.nutz.dao.entity.annotation.Readonly
import org.nutz.dao.entity.annotation.Table
import java.math.BigDecimal
import java.sql.Timestamp

@DataClass @Table("t_goods")
data class Goods constructor(
    @Id @Column("goods_id") var goodsId: Long?,
    @Column var title: String?,
    @Column("picture") var picture: String?,
    @Column var cost: BigDecimal?,
    @Column("sell_price") var sellPrice: BigDecimal?,
    @Column("inventory") var inventory: Short?,
    @Readonly @Column("created_at") var createdAt: Timestamp?,
    @Readonly @Column("updated_at") var updatedAt: Timestamp?
)

