package org.myfun.tms_server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.myfun.tms_server.entity.SysRole;
import org.myfun.tms_server.mapper.SysRoleMapper;
import org.myfun.tms_server.service.SysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Joe
 * @since 2022-05-21
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Override
    public List<SysRole> listRolesByUserId(Long userId) {

        List<SysRole> sysRoles = this.list(new QueryWrapper<SysRole>()
                .inSql("id", "select role_id from sys_user_role where user_id = " + userId));

        return sysRoles;
    }
}
