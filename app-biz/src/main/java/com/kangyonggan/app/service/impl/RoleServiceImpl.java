package com.kangyonggan.app.service.impl;

import com.github.pagehelper.PageHelper;
import com.kangyonggan.app.constants.YesNo;
import com.kangyonggan.app.mapper.RoleMapper;
import com.kangyonggan.app.model.Role;
import com.kangyonggan.app.service.RoleService;
import com.kangyonggan.app.util.StringUtil;
import com.kangyonggan.common.BaseService;
import com.kangyonggan.common.Params;
import com.kangyonggan.common.Query;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;

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

    @Override
    public List<Role> findRolesByUserId(Long userId) {
        return roleMapper.selectRolesByUserId(userId);
    }

    @Override
    public List<Role> findAllRoles() {
        Role role = new Role();
        role.setIsDeleted(YesNo.NO.getCode());

        return myMapper.select(role);
    }

    @Override
    public void deleteAllRolesByUserId(Long userId) {
        roleMapper.deleteAllRolesByUserId(userId);
    }

    @Override
    public List<Role> searchRoles(Params params) {
        Example example = new Example(Role.class);
        Query query = params.getQuery();

        Example.Criteria criteria = example.createCriteria();
        String roleCode = query.getString("roleCode");
        if (StringUtils.isNotEmpty(roleCode)) {
            criteria.andEqualTo("roleCode", roleCode);
        }
        String roleName = query.getString("roleName");
        if (StringUtils.isNotEmpty(roleName)) {
            criteria.andEqualTo("roleName", roleName);
        }

        String sort = params.getSort();
        String order = params.getOrder();
        if (!StringUtil.hasEmpty(sort, order)) {
            example.setOrderByClause(sort + " " + order.toUpperCase());
        } else {
            example.setOrderByClause("role_id desc");
        }

        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        return myMapper.selectByExample(example);
    }

    @Override
    public void saveRole(Role role) {
        myMapper.insertSelective(role);
    }

    @Override
    public Role findRoleByRoleId(Long roleId) {
        return myMapper.selectByPrimaryKey(roleId);
    }

    @Override
    public void updateRole(Role role) {
        if (role.getRoleId() == null) {
            return;
        }
        myMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public void deleteRoles(String roleIds) {
        if (StringUtils.isEmpty(roleIds)) {
            return;
        }
        String[] ids = roleIds.split(",");
        Example example = new Example(Role.class);
        example.createCriteria().andIn("roleId", Arrays.asList(ids));

        Role role = new Role();
        role.setIsDeleted(YesNo.YES.getCode());

        myMapper.updateByExampleSelective(role, example);
    }

    @Override
    public void restoreRoles(String roleIds) {
        if (StringUtils.isEmpty(roleIds)) {
            return;
        }
        String[] ids = roleIds.split(",");
        Example example = new Example(Role.class);
        example.createCriteria().andIn("roleId", Arrays.asList(ids));

        Role role = new Role();
        role.setIsDeleted(YesNo.NO.getCode());

        myMapper.updateByExampleSelective(role, example);
    }

    @Override
    public boolean existsRoleCode(String roleCode) {
        Role role = new Role();
        role.setRoleCode(roleCode);

        return super.exists(role);
    }

}
