
package com.cafe_kiosk.kiosk_admin.service;

import com.cafe_kiosk.kiosk_admin.dto.user.Admin;
import com.cafe_kiosk.kiosk_admin.mapper.AdminMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class AdminDetailsService implements UserDetailsService {

    private final AdminUserService adminUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminUserService.login(username);
        if (admin == null) {
            throw new UsernameNotFoundException("관리자 정보를 찾을 수 없습니다.");
        }
        UserDetails user = User.builder()
                .username(admin.getAdminId())
                .password(admin.getAdminPw())
                .roles(admin.getAdminRole().name())
                .build();
        log.info("로그인 유저: {}",user);
        return user;
    }
}
