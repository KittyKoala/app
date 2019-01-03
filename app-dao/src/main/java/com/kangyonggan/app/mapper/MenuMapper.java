package com.kangyonggan.app.mapper;

import com.kangyonggan.app.model.Menu;
import com.kangyonggan.common.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author kangyonggan
 * @since 8/8/18
 */
@Mapper
public interface MenuMapper extends MyMapper<Menu> {

    /**
     * 判断用户是否拥有某菜单
     *
     * @param userId
     * @param menuCodes
     * @return
     */
    boolean selectExistsUserMenuCodes(@Param("userId") Long userId, @Param("menuCodes") String[] menuCodes);

    /**
     * 查找用户菜单
     *
     * @param userId
     * @return
     */
    List<Menu> selectMenusByUserId(Long userId);

    /**
     * 查找角色权限
     *
     * @param roleId
     * @return
     */
    List<Menu> selectMenusByRoleId(Long roleId);
}