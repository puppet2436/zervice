package name.zeno.zervice.form

import name.zeno.zervice.annontations.DataClass

abstract class ApiForm<T : Any> {
  var body: T?=null
  var head: Head? = null
  var sign: String? = null

  @DataClass
  data class Head constructor(
      val userId: Long? = null,
      val token: String? = null,
      val client: String? = null,
      val timestamp: Long? = null
  )
}
