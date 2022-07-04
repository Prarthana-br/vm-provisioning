package com.ripple.vmprovisioning.security;

import com.ripple.vmprovisioning.integration.db.repository.UserEntityRepository;
import com.ripple.vmprovisioning.mappers.UserEntityToUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class VMProvisioningUserDetailsService implements UserDetailsService {

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private UserEntityToUserMapper userEntityToUserMapper;
    @Override
    public UserDetails loadUserByUsername(String vmUsername) throws UsernameNotFoundException {
        final com.ripple.vmprovisioning.model.User user = userEntityToUserMapper.map(userEntityRepository.findByName(vmUsername));
        GrantedAuthority grantedAuthority = new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        };
        ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(grantedAuthority);
        return new User(user.getName(), new String(user.getPassword()), grantedAuthorities);
        //return new User("foo", "foo", new ArrayList<>());
    }
}
