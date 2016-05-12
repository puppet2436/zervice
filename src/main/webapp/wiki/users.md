用户相关

## 注册

#### `POST` - `/users`

#### param body

```javascript
{
  "identifier": "",     //用户名/手机号/Email
  "credential": ""      //密码 MD5 值
}
```

## 登录

#### `POST` - `/users/sign-in`

#### param body

```javascript
{
  "identifier": "",     //用户名/手机号/Email
  "credential": ""      //密码 MD5 值
}
```

## 注销

#### `POST` - `/users/sign-out`

公共参数



