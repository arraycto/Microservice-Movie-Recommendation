package com.example.service;

import com.example.model.UserRepository;
import com.example.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.springframework.ui.Model;
import org.slf4j.LoggerFactory;
import java.util.List;

@Service
public class UserService {
  private static final Logger log = LoggerFactory.getLogger(UserService.class);
  
  @Autowired
  private UserRepository userRepository;
  
  public Boolean addNewUser(String name, String email, String password,String gender,
          String age,String occupation,String zipcode,User user) {
    List<User> users = userRepository.findByEmail(email);
    if (users.size() != 0) {
      log.warn("用户账号保存失败，邮箱已注册");
      return false;
    } else {
      user.setName(name);
      user.setEmail(email);
      user.setPassword(password);
      user.setGender(gender);
      user.setOccupation(occupation);
      user.setZipcode(zipcode);
      user.setAge(age);
      user.setType("user");
      userRepository.save(user);
      log.info(user.toString() + "保存至数据库");
      return true;
    }
  }

  public Boolean logIn(String email, String password, Model model) {
    List<User> users = userRepository.findByEmail(email);
    // 如果数据库中未查到该账号:
    if (users.size() == 0) {
      log.warn("账号不存在，登陆失败");
      return false;
    } else {
      User user = users.get(0);
      if (user.getPassword().equals(password)) {
        // 如果密码与邮箱配对成功:
        model.addAttribute("name", user.getName());
        model.addAttribute("type", user.getType());
        log.warn(user.toString() + " 登陆成功 ");
        return true;
      } else {
        // 如果密码与邮箱不匹配:
        model.addAttribute("name", "logging failed");
        log.warn(user.toString() + " 登陆失败 ");
        return false;
      }
    }
  }

  public User findOne(Long userId){
    return userRepository.findOne(userId);
  }
}