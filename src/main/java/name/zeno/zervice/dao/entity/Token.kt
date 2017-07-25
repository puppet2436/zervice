package name.zeno.zervice.dao.entity

import name.zeno.zervice.annontations.DataClass
import org.nutz.dao.entity.annotation.Column
import org.nutz.dao.entity.annotation.Id
import org.nutz.dao.entity.annotation.Readonly
import org.nutz.dao.entity.annotation.Table
import java.sql.Timestamp

@DataClass @Table("t_token")
data class Token constructor(
    @Id @Column("token_id") var tokenId: Long = 0,
    @Column("user_id") var userId: Long = 0,
    @Column var token: String? = null,
    @Column var client: String? = null,
    @Readonly @Column("created_at")
    var createdAt: Timestamp? = null
)
