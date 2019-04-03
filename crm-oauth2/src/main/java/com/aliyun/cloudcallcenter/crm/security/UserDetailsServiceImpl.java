package com.aliyun.cloudcallcenter.crm.security;

import java.util.HashSet;
import java.util.Set;

import com.aliyun.cloudcallcenter.crm.model.User;
import com.aliyun.cloudcallcenter.crm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author edward
 * @date 2017/11/12
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("FullAccess"));
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
            grantedAuthorities);
    }
}

