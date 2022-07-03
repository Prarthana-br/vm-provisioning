package com.ripple.vmprovisioning.mappers;

import com.ripple.vmprovisioning.integration.db.entity.UserEntity;
import com.ripple.vmprovisioning.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserEntityToUserMapper implements UserMapper<UserEntity, User> {

    @Override
    public User map(UserEntity userEntity) {
        if(userEntity==null) return null;
        User user = new User();
        user.setEmail(userEntity.getEmail());
        user.setMobile(userEntity.getMobile());
        user.setPassword(userEntity.getPassword().toCharArray());
        user.setName(userEntity.getName());
        user.setRole(userEntity.getRole());
        user.setUserId(userEntity.getUserId());
        return user;
    }
}
