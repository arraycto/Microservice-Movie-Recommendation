package com.example.controller;

import com.example.service.UserService;
import com.example.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;


  /**
   * 用户注册：验证用户名是否已注册，未注册时注册
   * 
   * @param email    用户邮箱
   * @param password 用户密码
   * @param name    用户名
   * @param user    用户实例
   * @return @ResponseBody直接把结果字符串作为响应体
   */
  @RequestMapping(value="/add", method = RequestMethod.POST)
  public @ResponseBody Boolean addNewUser(@RequestParam String name, @RequestParam String email,
      @RequestParam String password, @RequestParam String gender, @RequestParam String age, @RequestParam String occupation, @RequestParam String zipcode, User user) {
    return userService.addNewUser(name, email, password, gender, age, occupation, zipcode, user);
  }


  /**
   * 用户登陆：验证用户名是否存在、密码是否正确
   * 
   * @param email    用户邮箱
   * @param password 用户密码
   * @param model    Model层存储处理结果，再由View层渲染。
   * @return @ResponseBody直接把结果字符串作为响应体
   */
  @RequestMapping(value="/login", method = RequestMethod.POST)
  public @ResponseBody Boolean logIn(@RequestParam String email, @RequestParam String password, Model model) {
    return userService.logIn(email, password, model);
  } 

  /**
   * 用户查询：返回指定ID的用户的实例
   * 
   * @param userId    用户ID
   * @return 指定ID的实例（JSON格式）
   */
  @RequestMapping(value="/{userId}", method = RequestMethod.GET)
  public User getUsers(@PathVariable(name = "userId") Long userId){
    return userService.findOne(userId);
  }

}