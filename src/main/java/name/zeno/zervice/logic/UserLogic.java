package name.zeno.zervice.logic;

import com.sun.istack.internal.NotNull;
import name.zeno.zervice.constants.IdentityType;
import name.zeno.zervice.dao.TokenDao;
import name.zeno.zervice.dao.UserAuthDao;
import name.zeno.zervice.dao.UserDao;
import name.zeno.zervice.dao.entity.Token;
import name.zeno.zervice.dao.entity.User;
import name.zeno.zervice.dao.entity.UserAuth;
import name.zeno.zervice.exception.LogicException;
import name.zeno.zervice.form.ApiForm;
import name.zeno.zervice.form.SignForm;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;

import java.util.List;
import java.util.UUID;

@SuppressWarnings({"WeakerAccess", "unused"})
@IocBean
public class UserLogic
{
  @Inject private UserDao     userDao;
  @Inject private UserAuthDao userAuthDao;
  @Inject private TokenDao    tokenDao;

  public User signUp(@NotNull SignForm.Sign form) throws LogicException
  {
    IdentityType type = IdentityType.getByIdentifier(form.getIdentifier());
    form.setIdentityType(type.code);

    //检查用户是否存在
    UserAuth dbAuth = userAuthDao.querySameUserIdentifier(form.getIdentityType(), form.getIdentifier());
    if (dbAuth != null) {
      String errorMsg = "";
      if (type == IdentityType.PHONE) {
        errorMsg = "手机号码已被注册";
      } else if (type == IdentityType.EMAIL) {
        errorMsg = "邮箱已被注册";
      } else if (type == IdentityType.USER_NAME) {
        errorMsg = "用户名已被注册";
      }
      throw new LogicException(errorMsg, LogicException.CODE_USER_NAME_EXIST);
    }


    final User user = new User();

    Trans.exec((Atom) () -> {
      user.setAvatar("");
      user.setNickName("");
      userDao.insert(user);

      UserAuth auth = new UserAuth();
      auth.setCredential(form.getCredential());
      auth.setIdentifier(form.getIdentifier());
      auth.setIdentityType(form.getIdentityType());
      auth.setUser(user);
      auth.setUserId(user.getUserId());
      userAuthDao.insert(auth);
    });

    return user;
  }

  public User signIn(@NotNull SignForm form) throws LogicException
  {
    SignForm.Sign body = form.getBody();
    IdentityType  type = IdentityType.getByIdentifier(body.getIdentifier());
    body.setIdentityType(type.code);

    UserAuth auth = userAuthDao.authorization(body.getIdentifier(), body.getCredential());
    if (auth == null) {
      throw new LogicException("帐号或密码不正确", LogicException.CODE_INVALID_IDENTIFIER_OR_CREDENTIAL);
    }

    String client = form.getHead().getClient();
    tokenDao.delete(auth.getUserId(), client);
    Token token = new Token()
        .setUserId(auth.getUserId())
        .setClient(client)
        .setToken(UUID.randomUUID().toString());
    tokenDao.insert(token);
    return userDao.querySignedIn(auth.getUserId(), client);
  }

  public void signOut(@NotNull ApiForm form) throws LogicException
  {
    ApiForm.Head head    = form.getHead();
    Token        dbToken = tokenDao.fetch(head.getUserId(), head.getClient());
    if (dbToken == null) {
      throw new LogicException("无效的TOKEN", LogicException.CODE_INVALID_TOKEN);
    } else {
      tokenDao.delete(dbToken.getTokenId());
    }
  }

  public User getUserById(long userId)
  {
    return userDao.fetch(userId);
  }

  public List<User> getUsers()
  {
    return userDao.query(Cnd.NEW());
  }

  public User createUser(User user)
  {
    return userDao.insert(user);
  }

}
