package com.kangyonggan.app.service;

import com.kangyonggan.app.dto.UserDto;
import com.kangyonggan.app.model.User;

/**
 * @author kangyonggan
 * @since 12/6/18
 */
public interface UserService {

    /**
     * 查找用户
     *
     * @param email
     * @return
     */
    User findUserByEmail(String email);

    /**
     * 判断电子邮箱是否存在
     *
     * @param email
     * @return
     */
    boolean existsEmail(String email);

    /**
     * 保存用户
     *
     * @param userDto
     */
    void saveUser(UserDto userDto);

}
