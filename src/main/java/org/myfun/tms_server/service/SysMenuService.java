package org.myfun.tms_server.service;

import org.myfun.tms_server.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Joe
 * @since 2022-05-21
 */
public interface SysMenuService extends IService<SysMenu> {

    List<SysMenuDto> getCurrentUserNav();

    List<SysMenu> tree();
}
