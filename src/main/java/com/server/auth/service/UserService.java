package com.server.auth.service;

import com.server.auth.domain.authority.Authority;
import com.server.auth.domain.role.Role;
import com.server.auth.domain.user.CreateUserDTO;
import com.server.auth.domain.user.User;
import com.server.auth.domain.user.exceptions.UserNotFoundException;
import com.server.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final RoleService roleService;


    private final AuthorityService authorityService;

    @Autowired
    public UserService(
            UserRepository userRepository,
            RoleService roleService,
            AuthorityService authorityService
    ) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.authorityService = authorityService;
    }

    public User getUserByEmail(String email) throws UserNotFoundException{
        Optional<User> userOptional = userRepository.findByEmail(email);

        return userOptional.orElseThrow(UserNotFoundException::new);
    }

    public User save(User user){
        return userRepository.save(user);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User buildUser(CreateUserDTO createUser){
        User user = new User();

        user.setUsername(createUser.getUsername());
        user.setEmail(createUser.getEmail());

        user.setPassword(createUser.getPassword());

        Set<Role> roles = roleService.getRolesByName(createUser.getRoles());
        user.setRoles(roles);

        Set<Authority> authorities = authorityService.getAuthoritiesByName(createUser.getAuthorities());
        user.setAuthorities(authorities);

        return user;
    }
}
