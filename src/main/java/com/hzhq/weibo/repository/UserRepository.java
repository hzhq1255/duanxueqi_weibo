package com.hzhq.weibo.repository;

import com.hzhq.weibo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/7/6 20:03
 * @desc:
 */
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    /**
     * findUser
     * @param id id
     * @return User
     */
    User findUserById(Integer id);

    /**
     * findUser
     * @param name name
     * @return User
     */
    User findUserByName(String name);

    /**
     * 是否已存在
     * @param name name
     * @return true or false
     */
    Boolean existsByName(String name);


    /**
     * 删除
     * @param id userId
     */
    @Modifying
    @Transactional(rollbackFor=Exception.class)
    void deleteUserById(Integer id);
}
