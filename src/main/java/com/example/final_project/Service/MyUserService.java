package com.example.final_project.Service;

import com.example.final_project.Entity.Permission;
import com.example.final_project.Entity.User;
import com.example.final_project.Repository.PermissionRepository;
import com.example.final_project.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MyUserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PermissionRepository permissionRep;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if(Objects.nonNull(user)) {
            return user;
        }

        throw new UsernameNotFoundException("User Not Found with email: " + email);
    }

    public void registr(User model){
        User check = userRepository.findByEmail(model.getEmail());

        if (check == null){
            model.setPassword(passwordEncoder.encode(model.getPassword()));

            Permission userPermission = permissionRep.findByName("ROLE_USER");
            if (Objects.isNull(userPermission)) {
                userPermission = new Permission(null, "ROLE_USER");
                permissionRep.save(userPermission);
            }

            List<Permission> permissions = List.of(userPermission);
            model.setPermissions(permissions);
            userRepository.save(model);
        }
    }
}
