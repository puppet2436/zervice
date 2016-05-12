package name.zeno.zervice.action;

import org.nutz.http.Cookie;
import org.nutz.http.Request;
import org.nutz.http.Response;
import org.nutz.http.Sender;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.annotation.Ok;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@IocBean
@At("/wuliu")
public class WuliuAction
{
    /**
     * 查询物流信息
     *
     * @param com 公司
     * @param nu  订单号
     */
    @GET
    @At("/?/?")
    @Ok("json:full")
    public Object wuliu(String com, String nu)
    {
        String url = String.format("http://api.open.baidu.com" +
            "/pae/channel/data/asyncqury?appid=4001&com=%s&nu=%s", com, nu);
        Request request = Request.get(url);
        Cookie  cookie  = request.getCookie();
        cookie.set("BAIDUID", "1AA9368A0C47766490C4813104E2E078:FG=1");
        request.setCookie(cookie);
        Response    response = Sender.create(request).send();
        InputStream is       = response.getStream();
        Scanner     scanner  = new Scanner(is, "UTF-8");
        String      result   = scanner.useDelimiter("\\A").next();
        result = decodeUnicode(result);
        return Json.fromJson(result);
    }

    /**
     * 解码 Unicode \\uXXXX
     */
    public static String decodeUnicode(String str)
    {
        Charset set = Charset.forName("UTF-16");
        Pattern p = Pattern.compile("\\\\u([0-9a-fA-F]{4})");
        Matcher m = p.matcher(str);
        int start = 0;
        int start2 = 0;
        StringBuilder sb = new StringBuilder();
        while (m.find(start)) {
            start2 = m.start();
            if (start2 > start) {
                String seg = str.substring(start, start2);
                sb.append(seg);
            }
            String code = m.group(1);
            int i = Integer.valueOf(code, 16);
            byte[] bb = new byte[4];
            bb[0] = (byte) ((i >> 8) & 0xFF);
            bb[1] = (byte) (i & 0xFF);
            ByteBuffer b = ByteBuffer.wrap(bb);
            sb.append(String.valueOf(set.decode(b)).trim());
            start = m.end();
        }
        start2 = str.length();
        if (start2 > start) {
            String seg = str.substring(start, start2);
            sb.append(seg);
        }
        return sb.toString();
    }
}
