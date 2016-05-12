package name.zeno.zervice.model.wexin;

import org.dom4j.Document;
import org.dom4j.Node;

/**
 *
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class VoiceMessage extends Message
{
  private String mediaId;
  private String format;
  private String recognition;

  public VoiceMessage()
  {
    super.setMsgType(MSG_TYPE_VOICE);
  }

  public String getMediaId()
  {
    return mediaId;
  }

  public void setMediaId(String mediaId)
  {
    this.mediaId = mediaId;
  }

  public String getFormat()
  {
    return format;
  }

  public void setFormat(String format)
  {
    this.format = format;
  }

  public String getRecognition()
  {
    return recognition;
  }

  public void setRecognition(String recognition)
  {
    this.recognition = recognition;
  }

  @Override
  public void parseXML(Document document)
  {
    super.parseXML(document);
    if (document != null) {
      Node node = document.selectSingleNode("/xml/MediaId");
      if (node != null) {
        this.mediaId = node.getText();
      }
      node = document.selectSingleNode("/xml/Format");
      if (node != null) {
        this.format = node.getText();
      }
      node = document.selectSingleNode("/xml/Recognition");
      if (node != null) {
        this.recognition = node.getText();
      }
    }
  }

  @Override
  public String toXMLString()
  {
    return null;
  }
}
