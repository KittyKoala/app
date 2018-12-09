package com.kangyonggan.app.service.impl;

import com.kangyonggan.app.constants.AppConstants;
import com.kangyonggan.app.dto.UserDto;
import com.kangyonggan.app.mapper.UserMapper;
import com.kangyonggan.app.mapper.UserProfileMapper;
import com.kangyonggan.app.model.User;
import com.kangyonggan.app.model.UserProfile;
import com.kangyonggan.app.service.UserService;
import com.kangyonggan.app.util.Digests;
import com.kangyonggan.app.util.Encodes;
import com.kangyonggan.app.util.ValidUtil;
import com.kangyonggan.common.BaseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author kangyonggan
 * @since 12/6/18
 */
@Service
public class UserServiceImpl extends BaseService<User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserProfileMapper userProfileMapper;

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

        // 保存用户
        entryptPassword(user);
        userMapper.insertSelective(user);

        // 保存用户信息
        userProfile.setUserId(user.getUserId());
        userProfileMapper.insertSelective(userProfile);
    }

    @Override
    public UserDto findUserDtoByUserId(Long userId) {
        return userMapper.selectUserDtoByUserId(userId);
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
