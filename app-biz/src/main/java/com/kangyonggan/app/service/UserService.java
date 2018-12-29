package com.kangyonggan.app.service;

import com.kangyonggan.app.dto.UserDto;
import com.kangyonggan.app.model.User;
import com.kangyonggan.common.Params;

import java.util.List;

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

    /**
     * 查找用户信息
     *
     * @param userId
     * @return
     */
    UserDto findUserDtoByUserId(Long userId);

    /**
     * 搜索用户
     *
     * @param params
     * @return
     */
    List<UserDto> searchUsers(Params params);

    /**
     * 查找用户
     *
     * @param userId
     * @return
     */
    User findUserByUserId(Long userId);

    /**
     * 更新用户信息
     *
     * @param userDto
     */
    void updateUser(UserDto userDto);

    /**
     * 批量删除用户
     *
     * @param userIds
     */
    void deleteUsers(String userIds);

    /**
     * 批量恢复用户
     *
     * @param userIds
     */
    void restoreUsers(String userIds);

    /**
     * 更新密码
     *
     * @param user
     */
    void updateUserPassword(User user);
}
