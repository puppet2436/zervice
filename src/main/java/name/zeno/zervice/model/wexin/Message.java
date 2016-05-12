package name.zeno.zervice.model.wexin;

import name.zeno.zervice.model.BaseModel;
import org.dom4j.Document;
import org.dom4j.Node;

/**
 * 微信公众号消息
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public abstract class Message extends BaseModel
{
  public static final String MSG_TYPE_TEXT     = "text";
  public static final String MSG_TYPE_EVENT    = "event";
  public static final String MSG_TYPE_IMG      = "image";
  public static final String MSG_TYPE_VOICE    = "voice";
  public static final String MSG_TYPE_VIDEO    = "video";
  public static final String MSG_TYPE_LOCATION = "location";
  public static final String MSG_TYPE_LINK     = "link";

  public static final String MSG_TYPE_NEWS = "news";

  private Long   msgId;
  private String msgType;
  private Long   createTime;

  private String toUserName;
  private String fromUserName;

  public Message()
  {
    createTime = System.currentTimeMillis();
  }

  //<editor-fold desc="getter and setter">
  public Long getMsgId()
  {
    return msgId;
  }

  public void setMsgId(Long msgId)
  {
    this.msgId = msgId;
  }

  public Long getCreateTime()
  {
    return createTime;
  }

  public void setCreateTime(Long createTime)
  {
    this.createTime = createTime;
  }

  public String getToUserName()
  {
    return toUserName;
  }

  public void setToUserName(String toUserName)
  {
    this.toUserName = toUserName;
  }

  public String getFromUserName()
  {
    return fromUserName;
  }

  public void setFromUserName(String fromUserName)
  {
    this.fromUserName = fromUserName;
  }

  public String getMsgType()
  {
    return msgType;
  }

  public void setMsgType(String msgType)
  {
    this.msgType = msgType;
  }
  //</editor-fold>

  public static Message createMessage(String msgType)
  {
    if (null == msgType) {
      return null;
    }

    if (MSG_TYPE_TEXT.equals(msgType)) {
      return new TextMessage();
    } else if (MSG_TYPE_EVENT.equals(msgType)) {
      return new EventMessage();
    } else if (MSG_TYPE_VOICE.equals(msgType)) {
      return new VoiceMessage();
    }
    return null;
  }

  /**
   * 解析xml填充消息
   */
  public void parseXML(Document document)
  {
    {
      if (null != document) {
        Node msgId = document.selectSingleNode("xml/MsgId");
        if (null != msgId)
          this.msgId = Long.parseLong(msgId.getText());

        Node toUserName = document.selectSingleNode("/xml/ToUserName");
        if (null != toUserName)
          this.toUserName = toUserName.getText();

        Node fromUserName = document.selectSingleNode("/xml/FromUserName");
        if (null != fromUserName)
          this.fromUserName = fromUserName.getText();

        Node createTime = document.selectSingleNode("/xml/CreateTime");
        if (null != createTime)
          this.createTime = Long.parseLong(createTime.getText());
      }
    }
  }

  public abstract String toXMLString();

}
