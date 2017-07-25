package name.zeno.zervice.dao.entity

import name.zeno.zervice.annontations.DataClass
import name.zeno.zervice.constants.MySQL
import org.nutz.dao.DB
import org.nutz.dao.entity.annotation.*
import java.sql.Timestamp

@DataClass @Table("t_user_auth")
data class UserAuth constructor(
    @Id @Next(SQL(db = DB.MYSQL, value = MySQL.SQL_GET_NEW_ID))
    @Column("user_auth_id") var userAuthId: Long? = null,
    @Column("user_id") var userId: Long? = null,
    @Column("identity_type") var identityType: String? = null,
    @Column var identifier: String? = null,
    @Column var credential: String? = null,
    @Column("refresh_token") var refreshToken: String? = null,
    @Readonly @Column("created_at") var createdAt: Timestamp? = null,
    @Readonly @Column("updated_at") var updatedAt: Timestamp? = null

) {
  @One(target = User::class, field = "userId")
  var user: User? = null
}
