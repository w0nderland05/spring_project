package org.study.models.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.study.entities.User;
import org.study.repositories.UserRepository;

import java.util.Arrays;
import java.util.List;

public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUserId(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        
        // 사용자 권한 
        List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(user.getRole().toString()));

        return UserInfo.builder()
                .userNo(user.getUserNo())
                .userId(user.getUserId())
                .userNm(user.getUserNm())
                .userPw(user.getUserPw())
                .email(user.getEmail())
                .cellPhone(user.getCellPhone())
                .authorities(authorities) // 사용자 권한 설정
                .build();
    }
}
