package name.zeno.zervice.constants

class MySQL {
  companion object {
    const val SQL_GET_NEW_ID = "SELECT LAST_INSERT_ID()"
    const val SQL_SET_NAMES = "SET NAMES utf8mb4;"
  }
}
