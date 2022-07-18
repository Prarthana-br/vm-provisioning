package com.ripple.vmprovisioning.mappers;

import com.ripple.vmprovisioning.integration.db.entity.UserEntity;
import com.ripple.vmprovisioning.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserToUserEntityMapper implements UserMapper<User, UserEntity> {

    @Override
    public UserEntity map(User user) {
        if(user==null) return null;
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(user.getEmail());
        userEntity.setMobile(user.getMobile());
        if(user.getPassword() != null){
            userEntity.setPassword(new String(user.getPassword()));
        }
        userEntity.setName(user.getName());
        userEntity.setRole(user.getRole());
        return userEntity;
    }
}
