package name.zeno.zervice.action

import org.nutz.http.Request
import org.nutz.http.Sender
import org.nutz.ioc.loader.annotation.IocBean
import org.nutz.json.Json
import org.nutz.mvc.annotation.At
import org.nutz.mvc.annotation.GET
import org.nutz.mvc.annotation.Ok
import java.nio.ByteBuffer
import java.nio.charset.Charset
import java.util.*
import java.util.regex.Pattern

@IocBean
@At("/wuliu")
class WuliuAction {
  /**
   * 查询物流信息
   * @param com 公司 { ems | shentong | yuantong | shunfeng | yunda | }
   * @param nu  订单号
   * @see
   * <a href="https://www.kuaidi100.com/openapi/api_post.shtml"></a>
   */
  @GET @At("/?/?") @Ok("json:full")
  fun wuliu(com: String, nu: String): Any {
    val url = "http://api.open.baidu.com/pae/channel/data/asyncqury?appid=4001&com=$com&nu=$nu"
    val request = Request.get(url)
    val cookie = request.cookie
    cookie.set("BAIDUID", "1AA9368A0C47766490C4813104E2E078:FG=1")
    request.cookie = cookie
    val response = Sender.create(request).send()
    val inputStream = response.stream
    val scanner = Scanner(inputStream, "UTF-8")
    var result = scanner.useDelimiter("\\A").next()
    result = decodeUnicode(result)
    return Json.fromJson(result)
  }

  /**
   * 解码 Unicode \\uXXXX
   */
  private fun decodeUnicode(str: String): String {
    val set = Charset.forName("UTF-16")
    val p = Pattern.compile("\\\\u([0-9a-fA-F]{4})")
    val m = p.matcher(str)
    var start = 0
    var start2 = 0
    val sb = StringBuilder()
    while (m.find(start)) {
      start2 = m.start()
      if (start2 > start) {
        val seg = str.substring(start, start2)
        sb.append(seg)
      }
      val code = m.group(1)
      val i = Integer.valueOf(code, 16)!!
      val bb = ByteArray(4)
      bb[0] = (i shr 8 and 0xFF).toByte()
      bb[1] = (i and 0xFF).toByte()
      val b = ByteBuffer.wrap(bb)
      sb.append(set.decode(b).toString().trim { it <= ' ' })
      start = m.end()
    }
    start2 = str.length
    if (start2 > start) {
      val seg = str.substring(start, start2)
      sb.append(seg)
    }
    return sb.toString()
  }

}

