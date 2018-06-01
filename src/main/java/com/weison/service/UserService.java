package com.weison.service;

import com.weison.model.User;
import java.util.List;

/**
 * 用户接口类
 */
public interface UserService {

    /**
     * 添加用户
     * @param user 用户实体
     * @return User
     */
    int addUser(User user);

    /**
     * 查所有用户
     * @return List <User>
     */
    List <User> findAllUser(int pageNum, int pageSize);

    /**
     * 查单个用户
     * @return User
     */
    User findOneUser(int userId);
}
