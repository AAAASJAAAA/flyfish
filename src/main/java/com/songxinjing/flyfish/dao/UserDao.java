package com.songxinjing.flyfish.dao;

import org.springframework.stereotype.Repository;

import com.songxinjing.flyfish.dao.base.BaseDao;
import com.songxinjing.flyfish.domain.User;

/**
 * 用户信息表Dao类
 * @author songxinjing
 *
 */
@Repository
public class UserDao extends BaseDao<User, String> {

}
