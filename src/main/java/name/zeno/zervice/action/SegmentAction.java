package name.zeno.zervice.action;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.SegToken;
import name.zeno.zervice.model.ApiResult;
import org.nutz.mvc.annotation.At;

import java.util.ArrayList;
import java.util.List;

@At("/segments")
public class SegmentAction
{
  private static JiebaSegmenter segmenter = new JiebaSegmenter();

  @At("/?")
  public ApiResult<String> get(String text)
  {
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
