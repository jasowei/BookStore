package com.lanou.bookstore.user.service.impl;

import com.lanou.bookstore.user.dao.UserDao;
import com.lanou.bookstore.user.dao.impl.UserDaoImpl;
import com.lanou.bookstore.user.domain.User;
import com.lanou.bookstore.user.service.UserException;
import com.lanou.bookstore.user.service.UserService;

/**
 * 业务层 > 实现类
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public void regist(User form) throws UserException {
        /*1.校验form的username是否已被注册*/
        User dbUser = userDao.findByUsername(form.getUsername());
        if (dbUser != null){
            throw new UserException("*用户:"+form.getUsername()+"已被注册!");
        }
        /*2.校验form的email是否已被注册*/
        User dbUser2 = userDao.findByEmail(form.getEmail());
        if (dbUser2 != null){
            throw new UserException("*邮箱:"+form.getEmail()+"已被注册!");
        }
        /*3.把form保存到数据库*/
        userDao.add(form);

    }

    @Override
    public void active(String code) throws UserException {
        /*1.使用code查询数据库,得到User*/
        User user = userDao.findByCode(code);
        if (user == null){
            throw new UserException("*未找到此用户,请确保已注册!");
        }
        /*2.查看用户状态  true:抛   false: 修改为true*/
        boolean b = user.isState();
        if (b){
            throw new UserException("账户"+user.getUsername()+"已激活!");
        }else {
            userDao.updateState(user.getUid(),user.isState());
        }
    }

    @Override
    public User login(User form) throws UserException {
        /*1.使用username查询数据库,得到user  如果null 抛*/
        User user = userDao.findByUsername(form.getUsername());
        if (user == null){
            throw new UserException("*用户不存在!");
        }
        /*2.比较form与user密码  不同 抛*/
        String password1 = form.getPassword();
        String password = user.getPassword();
        if (!password.equals(password1)){
            throw new UserException("*密码错误!");
        }
        /*3.查看用户状态    未激活 抛*/
        boolean b = user.isState();
        if (!b){
            throw new UserException("*用户未激活!您可以前往个人邮箱进行激活操作!");
        }
        /*4.返回user*/
        return user;
    }

    @Override
    public void edit(String uid, User form) {
        userDao.edit(uid,form);
    }
}
