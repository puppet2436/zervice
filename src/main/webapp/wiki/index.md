## 请求参数

```javascript
paramBody =
{
  "head": {
    "userId": 124324,
    "token": "2234234234",
    //"android":"安卓","wechat":"微信","web": "浏览器"
    "client": "wechat",
    "timestamp": 1231231231231
  },
  "body": {
    //...
  },
  "sign": "~MJ*$tOWn?"
}
```

## sign 签名算法

1. 设置 sign 值为 `~MJ*$tOWn?` MD5值
2. 将参数 json 串做 MD5 处理
3. 将 sign 的值设为上一步中的结果

## 返回结果

```javascript
```
