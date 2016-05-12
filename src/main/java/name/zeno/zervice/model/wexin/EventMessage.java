package name.zeno.zervice.model.wexin;

import org.dom4j.Document;
import org.dom4j.Node;

/**
 * <h1>微信公众号事件消息</h1>
 *
 * @author 陈治谋 (chenzhimou@tele-sing.com)
 */
@SuppressWarnings("unused")
public class EventMessage extends Message
{
  public static final String EVENT_SUBSCRIBE   = "subscribe";     //关注
  public static final String EVENT_UNSUBSCRIBE = "unsubscribe";   //取关
  public static final String EVENT_SCAN        = "SCAN";          //扫描二维码

  private String event;

  private String eventKey;

  private String ticket;

  @SuppressWarnings("WeakerAccess")
  public EventMessage()
  {
    super.setMsgType(MSG_TYPE_EVENT);
  }

  //<editor-fold desc="getter and setter">
  public String getEvent()
  {
    return event;
  }

  public void setEvent(String event)
  {
    this.event = event;
  }

  public String getEventKey()
  {
    return eventKey;
  }

  public void setEventKey(String eventKey)
  {
    this.eventKey = eventKey;
  }

  public String getTicket()
  {
    return ticket;
  }

  public void setTicket(String ticket)
  {
    this.ticket = ticket;
  }
  //</editor-fold>

  @Override
  public void parseXML(Document document)
  {
    super.parseXML(document);

    if (null != document) {
      Node event = document.selectSingleNode("/xml/Event");
      if (null != event)
        this.event = event.getText();

      Node eventKey = document.selectSingleNode("/xml/EventKey");
      if (null != eventKey)
        this.eventKey = eventKey.getText();

      Node ticket = document.selectSingleNode("/xml/Ticket");
      if (null != ticket)
        this.ticket = ticket.getText();
    }
  }

  @Override
  public String toXMLString()
  {
    return null;
  }
}
