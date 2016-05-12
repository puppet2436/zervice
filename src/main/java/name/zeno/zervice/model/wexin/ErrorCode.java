package name.zeno.zervice.model.wexin;

import org.jetbrains.annotations.Nullable;

/**
 * 微信全局错误码
 *
 * @see <a href="http://mp.weixin.qq.com/wiki/17/fa4e1434e57290788bde25603fa2fcbd.html">
 * 全局返回码 - 微信公众平台开发者文档</link>
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public enum ErrorCode
{
  ERROR_NEGATIVE_1(-1, "系统繁忙，此时请开发者稍候再试"),
  ERROR_00000(0, "请求成功"),
  ERROR_40001(40001, "获取access_token时AppSecret错误，或者access_token无效。请开发者认真比对AppSecret的正确性，或查看是否正在为恰当的公众号调用接口"),
  ERROR_40002(40002, "不合法的凭证类型"),
  ERROR_40003(40003, "不合法的OpenID，请开发者确认OpenID（该用户）是否已关注公众号，或是否是其他公众号的OpenID"),
  ERROR_40004(40004, "不合法的媒体文件类型"),
  ERROR_40005(40005, "不合法的文件类型"),
  ERROR_40006(40006, "不合法的文件大小"),
  ERROR_40007(40007, "不合法的媒体文件id"),
  ERROR_40008(40008, "不合法的消息类型"),
  ERROR_40009(40009, "不合法的图片文件大小"),
  ERROR_40010(40010, "不合法的语音文件大小"),
  ERROR_40011(40011, "不合法的视频文件大小"),
  ERROR_40012(40012, "不合法的缩略图文件大小"),
  ERROR_40013(40013, "不合法的AppID，请开发者检查AppID的正确性，避免异常字符，注意大小写"),
  ERROR_40014(40014, "不合法的access_token，请开发者认真比对access_token的有效性（如是否过期），或查看是否正在为恰当的公众号调用接口"),
  ERROR_40015(40015, "不合法的菜单类型"),
  ERROR_40016(40016, "不合法的按钮个数"),
  ERROR_40017(40017, "不合法的按钮个数"),
  ERROR_40018(40018, "不合法的按钮名字长度"),
  ERROR_40019(40019, "不合法的按钮KEY长度"),
  ERROR_40020(40020, "不合法的按钮URL长度"),
  ERROR_40021(40021, "不合法的菜单版本号"),
  ERROR_40022(40022, "不合法的子菜单级数"),
  ERROR_40023(40023, "不合法的子菜单按钮个数"),
  ERROR_40024(40024, "不合法的子菜单按钮类型"),
  ERROR_40025(40025, "不合法的子菜单按钮名字长度"),
  ERROR_40026(40026, "不合法的子菜单按钮KEY长度"),
  ERROR_40027(40027, "不合法的子菜单按钮URL长度"),
  ERROR_40028(40028, "不合法的自定义菜单使用用户"),
  ERROR_40029(40029, "不合法的oauth_code"),
  ERROR_40030(40030, "不合法的refresh_token"),
  ERROR_40031(40031, "不合法的openid列表"),
  ERROR_40032(40032, "不合法的openid列表长度"),
  ERROR_40033(40033, "不合法的请求字符，不能包含\\uxxxx格式的字符"),
  ERROR_40035(40034, "不合法的参数"),
  ERROR_40038(40038, "不合法的请求格式"),
  ERROR_40039(40039, "不合法的URL长度"),
  ERROR_40050(40050, "不合法的分组id"),
  ERROR_40051(40051, "分组名字不合法"),
  ERROR_40117(40117, "分组名字不合法"),
  ERROR_40118(40118, "media_id大小不合法"),
  ERROR_40119(40119, "button类型错误"),
  ERROR_40120(40120, "button类型错误"),
  ERROR_40121(40121, "不合法的media_id类型"),
  ERROR_40132(40132, "微信号不合法"),
  ERROR_40137(40137, "不支持的图片格式"),
  ERROR_41001(41001, "缺少access_token参数"),
  ERROR_41002(41002, "缺少appid参数"),
  ERROR_41003(41003, "缺少refresh_token参数"),
  ERROR_41004(41004, "缺少secret参数"),
  ERROR_41005(41005, "缺少多媒体文件数据"),
  ERROR_41006(41006, "缺少media_id参数"),
  ERROR_41007(41007, "缺少子菜单数据"),
  ERROR_41008(41008, "缺少oauth code"),
  ERROR_41009(41009, "缺少openid"),
  ERROR_42001(42001, "access_token超时，请检查access_token的有效期，请参考基础支持-获取access_token中，对access_token的详细机制说明"),
  ERROR_42002(42002, "refresh_token超时"),
  ERROR_42003(42003, "oauth_code超时"),
  ERROR_43001(43001, "需要GET请求"),
  ERROR_43002(43002, "需要POST请求"),
  ERROR_43003(43003, "需要HTTPS请求"),
  ERROR_43004(43004, "需要接收者关注"),
  ERROR_43005(43005, "需要好友关系"),
  ERROR_44001(44001, "多媒体文件为空"),
  ERROR_44002(44002, "POST的数据包为空"),
  ERROR_44003(44003, "图文消息内容为空"),
  ERROR_44004(44004, "文本消息内容为空"),
  ERROR_45001(45001, "多媒体文件大小超过限制"),
  ERROR_45002(45002, "消息内容超过限制"),
  ERROR_45003(45003, "标题字段超过限制"),
  ERROR_45004(45004, "描述字段超过限制"),
  ERROR_45005(45005, "链接字段超过限制"),
  ERROR_45006(45006, "图片链接字段超过限制"),
  ERROR_45007(45007, "语音播放时间超过限制"),
  ERROR_45008(45008, "图文消息超过限制"),
  ERROR_45009(45009, "接口调用超过限制"),
  ERROR_45010(45010, "创建菜单个数超过限制"),
  ERROR_45015(45015, "回复时间超过限制"),
  ERROR_45016(45016, "系统分组，不允许修改"),
  ERROR_45017(45017, "分组名字过长"),
  ERROR_45018(45018, "分组数量超过上限"),
  ERROR_46001(46001, "不存在媒体数据"),
  ERROR_46002(46002, "不存在的菜单版本"),
  ERROR_46003(46003, "不存在的菜单数据"),
  ERROR_46004(46004, "不存在的用户"),
  ERROR_47001(47001, "解析JSON/XML内容错误"),
  ERROR_48001(48001, "api功能未授权，请确认公众号已获得该接口，可以在公众平台官网-开发者中心页中查看接口权限"),
  ERROR_50001(50001, "用户未授权该api"),
  ERROR_50002(50002, "用户受限，可能是违规后接口被封禁"),
  ERROR_61450(61450, "系统错误(system error)"),
  ERROR_61451(61451, "参数错误(invalid parameter)"),
  ERROR_61452(61452, "无效客服账号(invalid kf_account)"),
  ERROR_61453(61453, "客服帐号已存在(kf_account exsited)"),
  ERROR_61454(61454, "客服帐号名长度超过限制(仅允许10个英文字符，不包括@及@后的公众号的微信号)(invalid kf_acount length)"),
  ERROR_61455(61455, "客服帐号名包含非法字符(仅允许英文+数字)(illegal character in kf_account)"),
  ERROR_61456(61456, "客服帐号个数超过限制(10个客服账号)(kf_account count exceeded)"),
  ERROR_61457(61457, "无效头像文件类型(invalid file type)"),
  ERROR_61500(61500, "日期格式错误"),
  ERROR_61501(61501, "日期范围错误"),
  ERROR_9001001(9001001, "POST数据参数不合法"),
  ERROR_9001002(9001002, "远端服务不可用"),
  ERROR_9001003(9001003, "Ticket不合法"),
  ERROR_9001004(9001004, "获取摇周边用户信息失败"),
  ERROR_9001005(9001005, "获取商户信息失败"),
  ERROR_9001006(9001006, "获取OpenID失败"),
  ERROR_9001007(9001007, "上传文件缺失"),
  ERROR_9001008(9001008, "上传素材的文件类型不合法"),
  ERROR_9001009(9001009, "上传素材的文件尺寸不合法"),
  ERROR_9001010(9001010, "上传失败"),
  ERROR_9001020(9001020, "帐号不合法"),
  ERROR_9001021(9001021, "已有设备激活率低于50%，不能新增设备"),
  ERROR_9001022(9001022, "设备申请数不合法，必须为大于0的数字"),
  ERROR_9001023(9001023, "已存在审核中的设备ID申请"),
  ERROR_9001024(9001024, "一次查询设备ID数量不能超过50"),
  ERROR_9001025(9001025, "设备ID不合法"),
  ERROR_9001026(9001026, "页面ID不合法"),
  ERROR_9001027(9001027, "页面参数不合法"),
  ERROR_9001028(9001028, "一次删除页面ID数量不能超过10"),
  ERROR_9001029(9001029, "页面已应用在设备中，请先解除应用关系再删除"),
  ERROR_9001030(9001030, "一次查询页面ID数量不能超过50"),
  ERROR_9001031(9001031, "时间区间不合法"),
  ERROR_9001032(9001032, "保存设备与页面的绑定关系参数错误"),
  ERROR_9001033(9001033, "门店ID不合法"),
  ERROR_9001034(9001034, "设备备注信息过长"),
  ERROR_9001035(9001035, "设备申请参数不合法"),
  ERROR_9001036(9001036, "查询起始值begin不合法");

  public final int    code;
  public final String desc;

  ErrorCode(int code, String desc)
  {
    this.code = code;
    this.desc = desc;
  }

  @Nullable
  public static ErrorCode getByCode(int code)
  {
    for (ErrorCode errorCode : ErrorCode.values()) {

      if (errorCode.code == code) {
        return errorCode;
      }
    }

    return null;
  }

  @Nullable
  public static String getDescByCode(int code)
  {
    ErrorCode errorCode = getByCode(code);
    return errorCode == null ? null : errorCode.desc;
  }

}
