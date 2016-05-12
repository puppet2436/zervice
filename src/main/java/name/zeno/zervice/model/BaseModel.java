package name.zeno.zervice.model;

import org.nutz.json.Json;

public class BaseModel
{
  @Override
  public String toString()
  {
    return Json.toJson(this);
  }
}
