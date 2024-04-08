package com.example.testeditions.Services;

import com.example.testeditions.DTO.LoginDTO;
import com.example.testeditions.DTO.UserDTO;
import com.example.testeditions.Entites.Preferences;
import com.example.testeditions.Entites.User;
import com.example.testeditions.Exception.UsernameAlreadyUsedException;
import com.example.testeditions.Payload.Response.LoginMesage;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

public interface UserService {

    User getUserById(Long userId);

    User save(UserDTO userDTO);

    User update(Long userId, User userDTO);

    UserDTO loginUser(LoginDTO loginDTO);

    User addPreferencesToUser(Long userId, List<String> selectedTypes);



    User disconnect(User user);

    void banUserByEmail(String email);


    public long countBannedUsers() ;

    public String generateVerificationCode();



}
