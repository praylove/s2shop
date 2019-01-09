package com.x_s.s2shop.service.serviceImpl;

import com.github.wenhao.jpa.Sorts;
import com.github.wenhao.jpa.Specifications;
import com.x_s.s2shop.common.constant.ContextConstant;
import com.x_s.s2shop.common.exception.ParamException;
import com.x_s.s2shop.common.utils.Encoders;
import com.x_s.s2shop.domain.SysRole;
import com.x_s.s2shop.domain.SysUser;
import com.x_s.s2shop.repository.SysUserRepository;
import com.x_s.s2shop.service.BusinessFileService;
import com.x_s.s2shop.service.SysUserService;
import com.x_s.s2shop.vo.SysUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.Set;

@Service("userService")
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements SysUserService {

    @Autowired
    private SysUserRepository userRepository;

    @Autowired
    private BusinessFileService fileService;


    @Override
    public Optional<SysUser> findByName(String username) {
        return userRepository.findByName(username);
    }

    @Override
    public Optional<SysUser> findByLoginId(String loginId) {
        return userRepository.findByLoginId(loginId);
    }

    @Override
    public Page<SysUser> list(SysUserVo userVo) {
        Specification<SysUser> specification = Specifications.<SysUser>and()
                .like(StringUtils.hasText(userVo.getLoginId()), "loginId", "%" + userVo.getLoginId() + "%")
                .like(StringUtils.hasText(userVo.getName()), "name", "%" + userVo.getName() + "%")
                .build();
        Sort sort = Sorts.builder().desc("createTime").build();
        PageRequest page = PageRequest.of(userVo.getPageNo() - 1, userVo.getPageSize(), sort);
        return userRepository.findAll(specification, page);
    }

    @Override
    public void save(SysUser user) {
        String password = !StringUtils.hasText(user.getPassword()) ? ContextConstant.DEFUALT_PASSWORD : user.getPassword().trim();
        user.setPassword(Encoders.bcrypt(password));
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        super.save(user);
        fileService.use(user.getAvatar().substring(0, user.getAvatar().indexOf(".")));
    }


    public Set<SysRole> getRole(String uid) {
        SysUser user = userRepository.findById(uid).orElse(null);
        return user == null ? null : user.getRoles();
    }

    @Override
    public void resetPassword(String id) {
//        SysUser user = userRepository.findById(id).orElseThrow(() -> new ParamException("当前用户不存在！"));
        SysUser user = new SysUser();
        user.setId(id);
        user.setPassword(Encoders.bcrypt(ContextConstant.DEFUALT_PASSWORD));
        updateSelective(user);
    }

    public void addRole(String uid, String... roleIds) {
        SysUser user = userRepository.findById(uid).orElseThrow(() -> new ParamException("当前用户不存在！"));
        Set<SysRole> roles = user.getRoles();
        for (String roleId: roleIds){
            SysRole role = new SysRole();
            role.setId(roleId);
            if (roles.contains(role))
                throw new ParamException("该用户已含有此角色！");
            roles.add(role);
        }
        try {
            userRepository.saveAndFlush(user);
        } catch (RuntimeException e){
            throw new ParamException("当前角色不存在！");
        }
    }

    public void removeRole(String uid, String... roleIds) {
        SysUser user = userRepository.findById(uid).orElseThrow(() -> new ParamException("当前用户不存在！"));
        Set<SysRole> roles = user.getRoles();
        for (String roleId: roleIds){
            SysRole role = new SysRole();
            role.setId(roleId);
            if (!roles.contains(role))
                throw new ParamException("该用户没有有此角色！");
            roles.remove(role);
        }
        userRepository.saveAndFlush(user);
    }

//    @Override
//    public void updateSelective(SysUser user) {
//        String password = user.getPassword();
//        if (password != null) {
//            user.setPassword(Encoders.bcrypt(password));
//        } else {
//            user.setPassword(Encoders.bcrypt(ContextConstant.DEFUALT_PASSWORD));
//        }
//        super.updateSelective(user);
//    }

}
