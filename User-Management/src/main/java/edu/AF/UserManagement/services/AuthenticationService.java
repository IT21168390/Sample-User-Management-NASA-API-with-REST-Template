package edu.AF.UserManagement.services;

import edu.AF.UserManagement.dto.JwtAuthenticationResponseDTO;
import edu.AF.UserManagement.dto.SignInRequestDTO;
import edu.AF.UserManagement.dto.SignUpRequestDTO;
import edu.AF.UserManagement.dto.UserDTO;

public interface AuthenticationService {
    //User signUp(SignUpRequestDTO signUpRequestDTO);
    UserDTO signUp(SignUpRequestDTO signUpRequestDTO);

    JwtAuthenticationResponseDTO signIn(SignInRequestDTO signInRequest);

    boolean emailAlreadyExists(String email);

}
