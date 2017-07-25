package name.zeno.zervice.action

import com.huaban.analysis.jieba.JiebaSegmenter
import name.zeno.zervice.model.ApiResult
import org.apache.log4j.Logger
import org.nutz.mvc.annotation.At
import org.nutz.mvc.annotation.Param

@At("/segments")
class SegmentAction {
  companion object {
    val segmenter: JiebaSegmenter = JiebaSegmenter()
  }


  @At("/?")
  fun get(text: String): ApiResult<String> = segments(text)

  @At("/")
  fun getV2(@Param("text") text: String): ApiResult<String> = segments(text)

  private fun segments(text: String): ApiResult<String> {
    val segTokenList: List<String> = segmenter.process(text, JiebaSegmenter.SegMode.SEARCH)
        .map { it.word }.filter { it != null && it.isNotEmpty() }

    val result: ApiResult<String> = ApiResult <String>()
    result.setData(segTokenList);

    return result;
  }
}

