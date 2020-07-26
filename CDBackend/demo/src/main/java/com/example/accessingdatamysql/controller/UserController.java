package com.example.accessingdatamysql.controller;

import com.example.accessingdatamysql.Security.JwtUtil;
import com.example.accessingdatamysql.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

// import javax.validation.constraints.Null;

import com.example.accessingdatamysql.service.UserService;

@CrossOrigin(origins = "*")
@RestController() // This means that this class is a Controller
@RequestMapping(value = "/user") // This means URL's start with /demo (after Application path)
@EnableAutoConfiguration
public class UserController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtUtil jwtUtil;

  @Autowired
  private UserService userService;
  // private BCryptPasswordEncoder bCryptPasswordEncoder;

  // 获取一个用户信息
  @GetMapping(value = "/getUser")
  public @ResponseBody User findUserByUserId(@RequestParam("userId") Integer userId) {
    System.out.println("Class: UserController Method:finduserByUserId Param:" + userId);
    return userService.getOneUser(userId);
  }

  @RequestMapping(value = "/getUserByUserName")
  public @ResponseBody User findUserByUserName(@RequestParam("userName") String userName) {
    return userService.getOneUserByUserName(userName);
  }

  @PostMapping("/register")
  public @ResponseBody User register(@RequestBody User registerUser) {
    return userService.addNewUser(registerUser);
  }

  // 更新一个用户信息
  @RequestMapping(value = "/updateUser")
  public @ResponseBody User updateUser(@RequestBody User updateUser) {
    // 加密密码
    // password = bCryptPasswordEncoder.encode(password);
    return userService.updateUser(updateUser);
  }

  // 获取所有用户信息
  @RequestMapping(value = "/getAllUsers", method = RequestMethod.POST, consumes = "Application/json")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public List<User> getAllUsers() {
    return userService.getAllUsers();
  }

  // 删除部分用户
  @RequestMapping(value = "/deleteUsers")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public String deleteUsers(@RequestParam("userIds") List<Integer> userIds) {
    return userService.deleteUsers(userIds);
  }

  // 删除所有用户
  @RequestMapping(value = "/deleteAllUsers")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public String deleteAll() {
    return userService.deleteAll();
  }

  // 确认用户身份
  @RequestMapping(value = "/confirmDelete")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public Integer identifyUser(@RequestParam("userName") String userName, @RequestParam("password") String password) {
    return userService.identifyUser(userName, password);
  }

  // 登录逻辑
  @PostMapping("/login")
  public String identifyUser(@RequestBody AuthRequest authRequest) {
    try {
      authenticationManager
          .authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
    } catch (Exception ex) {
      throw ex;
    }
    return jwtUtil.generateToken(authRequest.getUserName());
  }

  // 删除一个指定用户
  @RequestMapping(value = "/deleteUser")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public List<User> deleteUser(@RequestParam("userId") Integer userId) {
    return userService.deleteUser(userId);
  }

  // 增加用户经验值(如果累计经验值超过升级所需经验值则升级后再返回user)
  @RequestMapping(value = "/addExp")
  public @ResponseBody User addExp(@RequestParam("userId") Integer userId, @RequestParam("exp") Integer exp) {
    System.out.println("Class: UserController Method: addExp Param: userId = " + userId + " exp = " + exp);
    return userService.addExp(userId, exp);
  }
}
