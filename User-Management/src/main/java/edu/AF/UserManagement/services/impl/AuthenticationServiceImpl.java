package edu.AF.UserManagement.services.impl;

import edu.AF.UserManagement.dto.JwtAuthenticationResponseDTO;
import edu.AF.UserManagement.dto.SignInRequestDTO;
import edu.AF.UserManagement.dto.SignUpRequestDTO;
import edu.AF.UserManagement.dto.UserDTO;
import edu.AF.UserManagement.models.User;
import edu.AF.UserManagement.models.UserRoles;
import edu.AF.UserManagement.repositories.UserRepository;
import edu.AF.UserManagement.services.AuthenticationService;
import edu.AF.UserManagement.services.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public UserDTO signUp(SignUpRequestDTO signUpRequestDTO) {
        User user = new User();

        if (signUpRequestDTO.getEmail() == null) {
            throw new IllegalArgumentException("Email is required!");
        } else {
            user.setEmail(signUpRequestDTO.getEmail());
        }
        user.setFirstName(signUpRequestDTO.getFirstName());
        user.setLastName(signUpRequestDTO.getLastName());
        user.setUserRole(UserRoles.USER);
        user.setPassword(passwordEncoder.encode(signUpRequestDTO.getPassword()));

        //return userRepository.save(user);
        User createdUser = userRepository.save(user);
        UserDTO userDTO = new UserDTO();
        userDTO.setId(createdUser.getId());
        userDTO.setLastName(createdUser.getLastName());
        userDTO.setEmail(createdUser.getEmail());
        userDTO.setFirstName(createdUser.getFirstName());
        userDTO.setUserRole(createdUser.getUserRole());

        return userDTO;
    }

    public boolean emailAlreadyExists(String email) {
        return userRepository.existsByEmail(email);
    }

    public JwtAuthenticationResponseDTO signIn(SignInRequestDTO signInRequest) throws IllegalArgumentException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));
        var user = userRepository.findByEmail(signInRequest.getEmail());
        //UserDetails userDetails = userRepository.findByEmail(signInRequest.getEmail());
        //UserDTO userDTO = new UserDTO();
        var jwt = jwtService.generateToken(user);

        JwtAuthenticationResponseDTO jwtAuthenticationResponseDTO = new JwtAuthenticationResponseDTO();
        jwtAuthenticationResponseDTO.setToken(jwt);
        jwtAuthenticationResponseDTO.setEmail(user.getUsername());

        return jwtAuthenticationResponseDTO;
    }

}
