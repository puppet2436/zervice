package name.zeno.zervice.model.wexin;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

/**
 * <h1>微信公众号文本消息</h1>
 *
 * @author 陈治谋 (chenzhimou@tele-sing.com)
 * @version V1.0, 2014-12-21
 */
public class TextMessage extends Message
{
  private String content;

  public TextMessage()
  {
    super.setMsgType(MSG_TYPE_TEXT);
  }

  public String getContent()
  {
    return content;
  }

  public void setContent(String content)
  {
    this.content = content;
  }

  @Override
  public void parseXML(Document document)
  {
    super.parseXML(document);

    if (null != document) {
      Node content = document.selectSingleNode("/xml/Content");
      if (null != content) {
        this.content = content.getText();
      }
    }
  }

  @Override
  public String toXMLString()
  {
    Document r    = DocumentHelper.createDocument();
    Element  root = r.addElement("xml");
    root.addElement("FromUserName").addCDATA(getFromUserName());
    root.addElement("ToUserName").addCDATA(getToUserName());
    root.addElement("CreateTime").setText(getCreateTime().toString());
    root.addElement("MsgType").addCDATA(getMsgType());
    root.addElement("Content").addCDATA(content);
    return r.asXML();
  }
}
