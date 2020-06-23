/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.service;

import com.example.dto.UserDTO;
import java.util.Collection;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author ljz
 */
@FeignClient("user-service") //项目使用了Ribbon，name属性会作为微服务的名称，用于服务发现
public interface UserService {
    /**
     * 用户注册
     */
    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    @ResponseBody Boolean addNewUser(@RequestParam(name = "name") String name, @RequestParam(name = "email") String email,
                       @RequestParam(name = "password") String password,@RequestParam(name = "gender") String gender, @RequestParam(name = "age") String age,
                                     @RequestParam(name = "occupation") String occupation, @RequestParam(name = "zipcode") String zipcode, UserDTO user);

    /**
     * 用户登陆：验证用户名是否存在、密码是否正确
     *
     * @param email    用户邮箱
     * @param password 用户密码
     * @param model    Model层存储处理结果，再由View层渲染。
     * @return @ResponseBody直接把结果字符串作为响应体
     */
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public @ResponseBody Boolean logIn(@RequestParam(name = "email") String email,
                                      @RequestParam(name = "password") String password, Model model);

    /**
     * 用户查询：返回指定ID的用户的实例
     *
     * @param userId 用户ID
     * @return 指定ID的实例（JSON格式）
     */
    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public UserDTO getUsers(@PathVariable(name = "userId") Long userId);

}
