/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.service;

import java.util.Collection;
import java.util.stream.Collectors;

import com.example.dto.MovieDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author ljz
 */
@FeignClient("movie-service") //movie微服务
public interface MovieService {
    /*根据movieId获取电影信息*/
    @RequestMapping(method = RequestMethod.GET, value = "/movie/{movieId}")
    MovieDTO getMovies(@PathVariable(value = "movieId") Long movieId);

    /*获取所有电影*/
    @RequestMapping(method = RequestMethod.GET, value = "/movie")
    public Collection<MovieDTO> getAllMovies();

    @RequestMapping(method = RequestMethod.GET, value = "/movie/dummyData")
    void insertDummyData();
    
}
