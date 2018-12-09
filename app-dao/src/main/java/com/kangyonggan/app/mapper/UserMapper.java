package com.kangyonggan.app.mapper;

import com.kangyonggan.app.dto.UserDto;
import com.kangyonggan.app.model.User;
import com.kangyonggan.common.Query;
import com.kangyonggan.common.mybatis.MyMapper;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.provider.SqlServerProvider;

import java.util.List;
import java.util.Map;

/**
 * @author kangyonggan
 * @since 8/8/18
 */
@Mapper
public interface UserMapper extends MyMapper<User> {

    /**
     * 重新指定主键
     *
     * @param user
     * @return
     */
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    @InsertProvider(type = SqlServerProvider.class, method = "dynamicSQL")
    @Override
    int insertSelective(User user);

    /**
     * 查找用户信息
     *
     * @param userId
     * @return
     */
    UserDto selectUserDtoByUserId(Long userId);

    /**
     * 搜索用户
     *
     * @param query
     * @return
     */
    List<UserDto> searchUsers(Map<String, Object> query);

    /**
     * 批量插入用户角色
     *
     * @param userId
     * @param roleIds
     */
    void insertUserRoles(@Param("userId") Long userId, @Param("roleIds") List<String> roleIds);
}