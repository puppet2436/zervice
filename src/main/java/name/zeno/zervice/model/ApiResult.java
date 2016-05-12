package name.zeno.zervice.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 调通接口返回结果
 *
 * @param <T>
 */
public class ApiResult<T> extends BaseModel
{
  private int     errCode;
  private String  errMsg;
  private List<T> data;

  public static <T> ApiResult<T> success()
  {
    return new ApiResult<>();
  }

  public static <T> ApiResult<T> success(T t)
  {
    ApiResult<T> result = new ApiResult<>();
    result.setData(t);

    return result;
  }


  public static <T> ApiResult<T> success(List<T> data)
  {
    ApiResult<T> result = new ApiResult<>();
    result.setData(data);

    return result;
  }

  public static ApiResult error(String errMsg, int errCode)
  {
    ApiResult result = new ApiResult();
    result.setErrCode(errCode);
    result.setErrMsg(errMsg);

    return result;
  }

  public ApiResult()
  {
    errCode = 0;
    errMsg = "success";
  }

  public int getErrCode()
  {
    return errCode;
  }

  public void setErrCode(int errCode)
  {
    this.errCode = errCode;
  }

  public String getErrMsg()
  {
    return errMsg;
  }

  public void setErrMsg(String errMsg)
  {
    this.errMsg = errMsg;
  }

  public Object getData()
  {
    return data;
  }

  public void setData(List<T> data)
  {
    this.data = data;
  }

  public void setData(T t)
  {
    if (data == null) {
      data = new ArrayList<>();
    }
    data.clear();
    data.add(t);
  }
}
