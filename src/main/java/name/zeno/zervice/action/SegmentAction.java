package name.zeno.zervice.action;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.SegToken;
import name.zeno.zervice.model.ApiResult;
import org.apache.log4j.Logger;
import org.nutz.http.Request;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Param;

import javax.swing.text.Segment;
import java.util.ArrayList;
import java.util.List;

@At("/segments")
public class SegmentAction
{
  private static Logger logger = Logger.getLogger(Segment.class);
  private static JiebaSegmenter segmenter = new JiebaSegmenter();

  @At("/?")
  public ApiResult<String> get(String text)
  {
    return segments(text);
  }

  @At("/")
  public ApiResult<String> getV2(@Param("text") String text)
  {
    return segments(text);
  }

  private ApiResult<String> segments(String text){
    if(text == null) text = "";
    List<SegToken> segTokenList = segmenter.process(text, JiebaSegmenter.SegMode.SEARCH);
    List<String> data = new ArrayList<>();
    for (SegToken segToken : segTokenList) {
      if (!segToken.word.trim().isEmpty()) {
        data.add(segToken.word);
      }
    }

    ApiResult<String> result;
    result = new ApiResult<>();
    result.setData(data);
    return result;
  }
}
