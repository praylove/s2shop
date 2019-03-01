package com.x_s.s2shop.service.serviceImpl;

import com.github.wenhao.jpa.Sorts;
import com.github.wenhao.jpa.Specifications;
import com.x_s.s2shop.common.exception.ParamException;
import com.x_s.s2shop.domain.SysMenu;
import com.x_s.s2shop.domain.SysRole;
import com.x_s.s2shop.repository.SysRoleRepository;
import com.x_s.s2shop.service.SysRoleService;
import com.x_s.s2shop.vo.SysRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service("roleService")
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole> implements SysRoleService {

    @Autowired
    private SysRoleRepository roleRepository;
    
    @Override
    public Page<SysRole> list(SysRoleVo roleVo) {
        Specification<SysRole> specification = Specifications.<SysRole>and()
                .like(StringUtils.hasText(roleVo.getRoleName()), "roleName", "%" + roleVo.getRoleName() + "%")
                .like(StringUtils.hasText(roleVo.getRemark()), "remark", "%" + roleVo.getRemark() + "%")
                .build();
        Sort sort = Sorts.builder().asc("createTime").build();
        PageRequest page = PageRequest.of(roleVo.getPageNo() - 1, roleVo.getPageSize(), sort);
        return roleRepository.findAll(specification, page);
    }

    public List<SysRole> listWithoutPage(SysRoleVo roleVo){
        Specification<SysRole> specification = Specifications.<SysRole>and()
                .like(StringUtils.hasText(roleVo.getRoleName()), "roleName", "%" + roleVo.getRoleName() + "%")
                .build();
        return roleRepository.findAll(specification);
    }

    @Override
    public void save(String name) {
        SysRole role = new SysRole();
        role.setRoleName(name);
        super.save(role);
    }

    @Override
    public void update(String id, String name) {
        SysRole role = new SysRole();
        role.setId(id);
        role.setRoleName(name);
        super.updateSelective(role);
    }


    public void updateMeanu(String rid, String... meauIds) {
        SysRole role = roleRepository.findById(rid).orElseThrow(() -> new ParamException("当前角色不存在！"));
        Set<SysMenu> menus = Arrays.stream(meauIds).map(SysMenu::new).collect(Collectors.toSet());
        role.setMenus(menus);
        try {
            roleRepository.saveAndFlush(role);
        } catch (RuntimeException e) {
            throw new ParamException("当前菜单或功能不存在！");
        }
    }

    public void removeMeau(String uid, String... meauIds) {
        SysRole role = roleRepository.findById(uid).orElseThrow(() -> new ParamException("当前角色不存在！"));
        Set<SysMenu> menus = role.getMenus();
        for (String menuId : meauIds) {
            SysMenu menu = new SysMenu();
            menu.setId(menuId);
            if (menus.contains(menu))
                menus.remove(menu);
        }
        roleRepository.saveAndFlush(role);
    }
}
