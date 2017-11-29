package com.xiaoshu.dao;

import com.xiaoshu.base.dao.BaseMapper;
import com.xiaoshu.entity.User;
import com.xiaoshu.entity.UserExample;
import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    List<User> selectUserAndRoleByExample(UserExample example);

}