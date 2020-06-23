/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import com.example.dto.MovieDTO;
import com.example.dto.RecommendationDTO;
import com.example.dto.UserDTO;
import com.example.service.MovieService;
import com.example.service.RecommendationClientService;
import com.example.service.RecommendationService;
import com.example.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Http;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author ljz
 */
@RestController
@RequestMapping("/api")
public class MainController {

    @Autowired
    private RecommendationClientService recommendationClientService;
    @Autowired
    private UserService userService;
    @Autowired
    private MovieService movieService;

    //1.给用户获取推荐的电影
    @HystrixCommand(fallbackMethod = "recoveryRecommendation")
    @RequestMapping(value = "/recommendation/user/{userId}", method = RequestMethod.GET)
    public List<MovieDTO> getRecommendation(@PathVariable(name = "userId") Long userId) throws InterruptedException{
        //根据登录用户的userId获取到登录用户的相关信息
        UserDTO user= userService.getUsers(userId);

        RecommendationDTO dto = null;
        try {
            dto = this.recommendationClientService.getRecommendationData(userId
            ,user.getGender(),user.getAge(),user.getOccupation(),user.getZipcode()).get();
        } catch (ExecutionException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dto.getMovies(); // if null, hystrix will call fallback method
    }
    //推荐服务出问题时调用
    public List<MovieDTO> recoveryRecommendation(Long userId){
        long id=1;//获取当前登录用户的useId;

        List<MovieDTO> movies = new ArrayList();
        movies.add(new MovieDTO("Toy Story (1995)", "1","Animation|Children’s|Comedy"));
        movies.add(new MovieDTO("Jumanji (1995)", "2","Adventure|Children’s|Fantasy"));
        movies.add(new MovieDTO("Grumpier Old Men (1995)", "3","Comedy|Romance"));
        movies.add(new MovieDTO("Waiting to Exhale (1995)", "4","Comedy|Drama"));
        movies.add(new MovieDTO("Father of the Bride Part II (1995)", "5","Comedy"));
        movies.add(new MovieDTO("Heat (1995)", "6","Action|Crime|Thriller"));
        movies.add(new MovieDTO("Sabrina (1995)", "7","Comedy|Romance"));
        movies.add(new MovieDTO("Tom and Huck (1995)", "8","Adventure|Children’s"));
        movies.add(new MovieDTO("Fight Club(1995)", "9","Comedy|Drama"));
        movies.add(new MovieDTO("The Lord of the Rings: The Fellowship of the Ring(1999)", "10","Comedy|Romance"));

        Random r=new Random();
        for(int i=0;i<5;i++){
            movies.remove(r.nextInt(movies.size()-1));
        }
        return movies;
    }

    //2.根据用户Id获取用户详细信息
    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public UserDTO getUsers(@PathVariable(name = "userId") Long userId){
        return userService.getUsers(userId);
    }

    //3.用户注册
    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    @ResponseBody Boolean addNewUser(@RequestParam(name = "name") String name, @RequestParam(name = "email") String email,
                                     @RequestParam(name = "password") String password,@RequestParam(name = "gender") String gender, @RequestParam(name = "age") String age,
                                     @RequestParam(name = "occupation") String occupation, @RequestParam(name = "zipcode") String zipcode, UserDTO user){
        return userService.addNewUser(name,email,password,gender,age,occupation,zipcode,user);
    }

    //4.用户登录
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public @ResponseBody Boolean logIn(@RequestParam(name = "email") String email,
                                       @RequestParam(name = "password") String password, Model model){
        return userService.logIn(email,password,model);
    }

    //5.获取电影热门前十
    @RequestMapping(method = RequestMethod.GET,value = "/movie/top")
    public Collection<MovieDTO> getMoviesTopTen(){
        //取出前十个
        ArrayList<MovieDTO> tenList=new ArrayList<>();
        tenList.addAll(movieService.getAllMovies());
        if(tenList.size()>10){
            int size=tenList.size();
            for(int i=10;i<size;i++){
                if(!tenList.remove(tenList.get(tenList.size()-1))){
                    System.out.println("删除失败");
                }
            }
        }
        System.out.println("return垃圾");
        return tenList;
    }

    //6.获取电影评分前十
    @RequestMapping(method = RequestMethod.GET,value = "/movie/rate")
    public Collection<MovieDTO> getMoviesRateTen(){
        //取出前十个
        ArrayList<MovieDTO> tenList=new ArrayList<>();
        tenList.addAll(movieService.getAllMovies());
        if(tenList.size()>10){
            int size=tenList.size();
            for(int i=10;i<size;i++){
                if(!tenList.remove(tenList.get(tenList.size()-1))){
                    System.out.println("删除失败");
                }
            }
        }
        return tenList;
    }

    //7.根据movieId获取电影信息
    @GetMapping(value = "/Movie/MovieDetails/{movieId}")
    public MovieDTO getMovieDetails(@PathVariable(name = "movieId") Long movieId){
        return movieService.getMovies(movieId);
    }

    //8.管理员添加电影
    //9.管理员下架电影
}
