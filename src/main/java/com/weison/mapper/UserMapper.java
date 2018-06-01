package com.weison.mapper;

import com.weison.model.User;
import java.util.List;

public interface UserMapper {

    int insert(User record);

    User selectByPrimaryKey(Integer userId);

    List<User> queryAll();
}