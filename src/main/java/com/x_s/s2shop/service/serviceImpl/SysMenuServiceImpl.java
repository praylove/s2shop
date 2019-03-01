package com.x_s.s2shop.service.serviceImpl;

import com.github.wenhao.jpa.PredicateBuilder;
import com.github.wenhao.jpa.Sorts;
import com.github.wenhao.jpa.Specifications;
import com.x_s.s2shop.common.constant.CodeConstant;
import com.x_s.s2shop.common.exception.ParamException;
import com.x_s.s2shop.common.exception.ServiceException;
import com.x_s.s2shop.common.utils.BeanUtilsCustom;
import com.x_s.s2shop.common.utils.HttpUtils;
import com.x_s.s2shop.domain.SysMenu;
import com.x_s.s2shop.domain.SysRole;
import com.x_s.s2shop.domain.SysUser;
import com.x_s.s2shop.repository.SysMenuRepository;
import com.x_s.s2shop.service.SysMenuService;
import com.x_s.s2shop.vo.SysMenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenu> implements SysMenuService {

    @Autowired
    private SysMenuRepository menuRepository;

    public Page<SysMenu> list(SysMenuVo menuVo) {
        Specification<SysMenu> specification = Specifications.<SysMenu>and()
                .like(StringUtils.hasText(menuVo.getMenuName()), "menuName", "%" + menuVo.getMenuName() + "%")
                .build();
        Sort sort = Sorts.builder().asc("order").build();
        PageRequest page = PageRequest.of(menuVo.getPageNo() - 1, menuVo.getPageSize(), sort);
        return menuRepository.findAll(specification, page);
    }

    @Override
    public void save(SysMenu entity) {
        if (StringUtils.hasText(entity.getParentId())) {
            SysMenu sysMenu = findById(entity.getParentId()).orElseThrow(() -> new ServiceException("此父菜单不存在！"));
            entity.setParentName(sysMenu.getMenuName());
        }
        super.save(entity);
    }

    @Override
    public void updateSelective(SysMenu entity) {
        SysMenu oldValue = findById(entity.getId()).orElseThrow(() -> new ServiceException("此菜单不存在！"));
        if (StringUtils.hasText(entity.getParentId()) && !entity.getParentId().equals(oldValue.getParentId())) {
            SysMenu sysMenu = findById(entity.getParentId()).orElseThrow(() -> new ServiceException("此父菜单不存在！"));
            entity.setParentName(sysMenu.getMenuName().trim());
            if (!isRightParentId(entity.getParentId(), entity.getId())) {
                throw new ServiceException("父菜单不正确，不能为当前菜单及其子菜单！");
            }
        }
        BeanUtilsCustom.copyWithNonNull(entity, oldValue);
        setUpdateParam(oldValue);
        menuRepository.saveAndFlush(oldValue);
    }
    
    @Override
    public void delete(String ids) {
        super.delete(ids);
    }
    

    public List<SysMenu> getTree(String... type) {
        List<SysMenu> menus = listAll(type);
        List<SysMenu> roots = menus.stream().filter(m -> !StringUtils.hasText(m.getParentId())).collect(Collectors.toList());
        buildTree(roots, menus);
        return roots;
    }

    public List<SysMenu> getUserTree(String... typeCode) {
        Optional<SysUser> user = Optional.ofNullable(HttpUtils.getCurrentUser());
        Set<SysRole> roles = user.orElseThrow(() -> new ParamException("用户未登录或已失效！")).getRoles();
        Set<SysMenu> menus = new HashSet<>();
        for (SysRole role: roles){
            menus.addAll(role.getMenus());
        }
        List<SysMenu> menuList = menus.stream().filter(m -> m.getStatus().equals(CodeConstant.NORMAL_STATUS) && (Arrays.binarySearch(typeCode, m.getType()) >= 0)).collect(Collectors.toList());
        List<SysMenu> roots = menuList.stream().filter(m -> !StringUtils.hasText(m.getParentId())).collect(Collectors.toList());
        buildTree(roots, menuList);
        return roots;
    }


    @SuppressWarnings("unchecked")
    public List<SysMenu> listAll(String... type) {
        Specification<SysMenu> specification = Specifications.<SysMenu>and()
                .eq("status", CodeConstant.NORMAL_STATUS)
                .in(type.length > 0, "type", type)
                .build();
        return menuRepository.findAll(specification);
    }
    
    
    private void buildTree(List<SysMenu> roots, List<SysMenu> menus) {
        if (roots == null || roots.isEmpty()) {
            return;
        }
        roots.forEach(root -> {
            String rootId = root.getId();
            List<SysMenu> children = menus.stream().filter(m -> rootId.equals(m.getParentId())).collect(Collectors.toList());
            if (children.isEmpty()) {
                root.setChildren(null);
            } else {
                root.setChildren(children);
            }
            buildTree(children, menus);
        });
    }

    private boolean isRightParentId(String parentId, String cuurentId) {
        Assert.notNull(cuurentId, "cuurentId不能为空！");
        while (StringUtils.hasText(parentId)) {
            final String s = parentId;
            if (s.equals(cuurentId)) {
                return false;
            }
            SysMenu sysMenu = findById(s).orElseThrow(() -> new ServiceException(s + " 不存在"));
            parentId = sysMenu.getParentId();
        }
        return true;
    }
}
