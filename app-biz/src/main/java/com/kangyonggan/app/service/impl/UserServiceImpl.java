package com.kangyonggan.app.service.impl;

import com.github.pagehelper.PageHelper;
import com.kangyonggan.app.constants.AppConstants;
import com.kangyonggan.app.constants.YesNo;
import com.kangyonggan.app.dto.UserDto;
import com.kangyonggan.app.exception.BizException;
import com.kangyonggan.app.mapper.UserMapper;
import com.kangyonggan.app.model.User;
import com.kangyonggan.app.model.UserProfile;
import com.kangyonggan.app.service.RoleService;
import com.kangyonggan.app.service.UserProfileService;
import com.kangyonggan.app.service.UserService;
import com.kangyonggan.app.util.Digests;
import com.kangyonggan.app.util.Encodes;
import com.kangyonggan.app.util.ValidUtil;
import com.kangyonggan.common.BaseService;
import com.kangyonggan.common.Params;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;

/**
 * @author kangyonggan
 * @since 12/6/18
 */
@Service
public class UserServiceImpl extends BaseService<User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private RoleService roleService;

    @Override
    public User findUserByEmail(String email) {
        User user = new User();
        user.setEmail(email);
        return myMapper.selectOne(user);
    }

    @Override
    public boolean existsEmail(String email) {
        User user = new User();
        user.setEmail(email);
        return super.exists(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveUser(UserDto userDto) {
        User user = new User();
        UserProfile userProfile = new UserProfile();
        BeanUtils.copyProperties(userDto, user);
        BeanUtils.copyProperties(userDto, userProfile);

        // 基础校验
        ValidUtil.valid(user);
        ValidUtil.valid(userProfile);

        // 保存用户
        entryptPassword(user);
        userMapper.insertSelective(user);

        // 保存用户信息
        userProfile.setUserId(user.getUserId());
        userProfileService.saveUserProfile(userProfile);
    }

    @Override
    public UserDto findUserDtoByUserId(Long userId) {
        return userMapper.selectUserDtoByUserId(userId);
    }

    @Override
    public List<UserDto> searchUsers(Params params) {
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        return userMapper.searchUsers(params.getQuery());
    }

    @Override
    public User findUserByUserId(Long userId) {
        return myMapper.selectByPrimaryKey(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUser(UserDto userDto) {
        if (userDto.getUserId() == null) {
            return;
        }
        User user = new User();
        UserProfile userProfile = new UserProfile();
        BeanUtils.copyProperties(userDto, user);
        user.setPassword(null);
        user.setSalt(null);

        BeanUtils.copyProperties(userDto, userProfile);

        User dbUser = findUserByUserId(user.getUserId());

        // 校验电子邮箱是否已存在
        if (StringUtils.isNotEmpty(user.getEmail())) {
            if (!dbUser.getEmail().equals(user.getEmail()) && existsEmail(user.getEmail())) {
                throw new BizException("电子邮箱已被其他用户注册");
            }
        }

        // 更新用户
        myMapper.updateByPrimaryKeySelective(user);

        // 更新用户信息
        userProfileService.updateUserProfile(userProfile);
    }

    @Override
    public void deleteUsers(String userIds) {
        if (StringUtils.isEmpty(userIds)) {
            return;
        }
        String[] ids = userIds.split(",");
        Example example = new Example(User.class);
        example.createCriteria().andIn("userId", Arrays.asList(ids));

        User user = new User();
        user.setIsDeleted(YesNo.YES.getCode());

        myMapper.updateByExampleSelective(user, example);
    }

    @Override
    public void restoreUsers(String userIds) {
        if (StringUtils.isEmpty(userIds)) {
            return;
        }
        String[] ids = userIds.split(",");
        Example example = new Example(User.class);
        example.createCriteria().andIn("userId", Arrays.asList(ids));

        User user = new User();
        user.setIsDeleted(YesNo.NO.getCode());

        myMapper.updateByExampleSelective(user, example);
    }

    @Override
    public void updateUserPassword(User user) {
        if (user.getUserId() == null) {
            return;
        }
        User u = new User();
        u.setPassword(user.getPassword());
        u.setUserId(user.getUserId());
        entryptPassword(u);

        myMapper.updateByPrimaryKeySelective(u);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUserRoles(Long userId, String roleIds) {
        roleService.deleteAllRolesByUserId(userId);

        if (StringUtils.isNotEmpty(roleIds)) {
            saveUserRoles(userId, roleIds);
        }
    }

    @Override
    public List<User> findUsersByRoleId(Long roleId) {
        return userMapper.selectUsersByRoleId(roleId);
    }

    /**
     * 批量保存授权角色
     *
     * @param userId
     * @param roleIds
     */
    private void saveUserRoles(Long userId, String roleIds) {
        userMapper.insertUserRoles(userId, Arrays.asList(roleIds.split(",")));
    }

    /**
     * 设定安全的密码，生成随机的salt并经过N次 sha-1 hash
     *
     * @param user
     */
    private void entryptPassword(User user) {
        byte[] salt = Digests.generateSalt(AppConstants.SALT_SIZE);
        user.setSalt(Encodes.encodeHex(salt));

        byte[] hashPassword = Digests.sha1(user.getPassword().getBytes(), salt, AppConstants.HASH_INTERATIONS);
        user.setPassword(Encodes.encodeHex(hashPassword));
    }
}
