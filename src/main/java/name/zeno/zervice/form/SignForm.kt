package name.zeno.zervice.form

import name.zeno.zervice.annontations.DataClass

class SignForm : ApiForm<SignForm.Sign>() {
  @DataClass
  data class Sign constructor(
      var identityType: String? = null,
      var identifier: String? = null,
      var credential: String? = null
  )
}
