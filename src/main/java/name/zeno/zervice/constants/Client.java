package name.zeno.zervice.constants;

import org.jetbrains.annotations.Nullable;

public enum Client
{
    ANDROID("android", "安卓"),
    WECHAT("wechat", "微信"),
    WEB("web", "浏览器");

    public String key;
    public String value;

    Client(String key, String value)
    {
        this.key = key;
        this.value = value;
    }

    @Nullable
    public static Client getByKey(String key)
    {
        for (Client client : Client.values()) {
            if (client.key.equals(key))
                return client;
        }
        return null;
    }
}
