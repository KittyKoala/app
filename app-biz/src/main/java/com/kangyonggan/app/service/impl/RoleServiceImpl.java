package com.kangyonggan.app.service.impl;

import com.kangyonggan.app.mapper.RoleMapper;
import com.kangyonggan.app.model.Role;
import com.kangyonggan.app.service.RoleService;
import com.kangyonggan.common.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author kangyonggan
 * @since 12/6/18
 */
@Service
public class RoleServiceImpl extends BaseService<Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public boolean hasRole(Long userId, String roleCode) {
        return roleMapper.selectExistsUserRoleCode(userId, roleCode);
    }

}
