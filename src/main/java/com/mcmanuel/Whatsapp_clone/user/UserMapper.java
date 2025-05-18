package com.mcmanuel.Whatsapp_clone.user;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserMapper {
    public User fromTokenAttributes(Map<String, Object> attributes) {
        User user = new User();

        if (attributes.containsKey("sub")){
            user.setId(attributes.get("sub").toString());
        }

        if (attributes.containsKey("given_name")){
            user.setFirstName(attributes.get("given_name").toString());
        } else if (attributes.containsKey("nickname")) {
            user.setFirstName(attributes.get("nickname").toString());
        }

        return user;
    }
}
