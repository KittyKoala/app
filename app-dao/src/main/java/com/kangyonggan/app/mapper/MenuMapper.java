package com.kangyonggan.app.mapper;

import com.kangyonggan.app.model.Menu;
import com.kangyonggan.common.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
     * @param menuCode
     * @return
     */
    boolean selectExistsUserMenuCode(@Param("userId") Long userId, @Param("menuCode") String menuCode);
}