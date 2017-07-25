package name.zeno.zervice.dao.entity

import name.zeno.zervice.annontations.DataClass
import name.zeno.zervice.constants.MySQL
import org.nutz.dao.DB
import org.nutz.dao.entity.annotation.*
import java.sql.Timestamp

@DataClass @Table("t_user") @View("v_user")
data class User constructor(
    @Id @Next(SQL(db = DB.MYSQL, value = MySQL.SQL_GET_NEW_ID))
    @Column("user_id") var userId: Long = 0,
    @Column("nick_name") var nickName: String? = null,
    @Column var avatar: String? = null,

    //from view
    @Readonly @Column val token: String? = null,
    @Readonly @Column("token_client") val tokenClient: String? = null,
    @Readonly @Column("token_created_at") val tokenCreatedAt: Timestamp? = null
)
