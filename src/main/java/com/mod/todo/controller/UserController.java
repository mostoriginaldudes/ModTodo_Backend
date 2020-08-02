package com.mod.todo.controller;

import com.mod.todo.domain.user.User;
import com.mod.todo.dtos.LoginRequestDto;
import com.mod.todo.dtos.UserDto;
import com.mod.todo.service.JwtService;
import com.mod.todo.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "*")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    JwtService jwtService;

    //로그인
    @ApiOperation(value = "로그인")
    @PostMapping("/users/signin")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response){
        try{
            System.out.println(loginRequestDto.getUserPhoneNumber());
            User user = userService.getUser(loginRequestDto.getUserPhoneNumber());
            if(user==null){
                System.out.println("user is null==================");
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            String token = jwtService.create(user);
            response.setHeader("Authorization",token);
            return new ResponseEntity<>(user.getUserName(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    // 중복확인
    @ApiOperation(value = "중복확인")
    @GetMapping("/users/valid/{userPhone}")
    public ResponseEntity<String> isValid(@PathVariable String userPhone){
        try{
            User user = userService.getUser(userPhone);
            System.out.println(user);
            if(user==null) return new ResponseEntity<>("true",HttpStatus.OK);
            else return new ResponseEntity<>("false",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //회원가입
    @ApiOperation(value = "회원가입")
    @PostMapping("/users/signup")
    public ResponseEntity<String> signUp(@RequestBody UserDto userRequest){
        System.out.println("여기여기");
        String userName = userService.signUp(userRequest);
        if(userName == null) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(userName, HttpStatus.OK);
    }
}
