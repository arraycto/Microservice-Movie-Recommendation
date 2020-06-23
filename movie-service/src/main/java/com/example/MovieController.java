/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author
 */
@RestController
@RequestMapping("/movie")
public class MovieController {
    
    @Autowired
    private MovieRepo movieRepo;

    //2.管理员根据movieId删除电影
    @RequestMapping(method = RequestMethod.GET, value = "/delete/{movieId}")
    public Movie deleteMovie(@PathVariable(value = "movieId") Long movieId){
        return this.movieRepo.findByMovieId(movieId);
    }

    //1.管理员添加电影
    @RequestMapping(method = RequestMethod.GET, value = "/add")
    public Movie addMovie(@RequestParam Long movieId, @RequestParam String title,
                          @RequestParam String genres){
        return this.movieRepo.findByMovieId(movieId);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/{movieId}")
    public Movie getMovie(@PathVariable(value = "movieId") Long movieId){
        return this.movieRepo.findByMovieId(movieId);
    }

    //获取所有电影
    @RequestMapping(method = RequestMethod.GET)
    public Collection<MovieDTO> getAllMovies(){
        return this.movieRepo.findAll()
                .stream()
                .map(Movie::toDTO)
                .collect(Collectors.toList());
    }

    //怎么用？
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Collection<MovieDTO> getListMovies(@RequestParam String ids){
        System.out.println("Somebody found me!");
        Gson gson = new Gson();
        List<Double> idsList = (ArrayList<Double>)gson.fromJson(ids, ArrayList.class);
        List<MovieDTO> retVal = new ArrayList();
        idsList.stream()
                .forEach(id -> {
                    MovieDTO dto = new MovieDTO(this.movieRepo.findByMovieId(id.longValue()));
                    retVal.add(dto);
                });
        return retVal;
    }



    //插入电影
    @RequestMapping(method = RequestMethod.GET, value = "/dummyData")
    public void insertDummyData(){
        this.movieRepo.deleteAll();
        this.movieRepo.save(new Movie("Toy Story (1995)", 1l,"Animation|Children’s|Comedy"));
        this.movieRepo.save(new Movie("Jumanji (1995)", 2l,"Adventure|Children’s|Fantasy"));
        this.movieRepo.save(new Movie("Grumpier Old Men (1995)", 3l,"Comedy|Romance"));
        this.movieRepo.save(new Movie("Waiting to Exhale (1995)", 4l,"Comedy|Drama"));
        this.movieRepo.save(new Movie("Father of the Bride Part II (1995)", 5l,"Comedy"));
        this.movieRepo.save(new Movie("Heat (1995)", 6l,"Action|Crime|Thriller"));
        this.movieRepo.save(new Movie("Sabrina (1995)", 7l,"Comedy|Romance"));
        this.movieRepo.save(new Movie("Tom and Huck (1995)", 8l,"Adventure|Children’s"));
        this.movieRepo.save(new Movie("Fight Club(1995)", 9l,"Comedy|Drama"));
        this.movieRepo.save(new Movie("The Lord of the Rings: The Fellowship of the Ring(1999)", 10l,"Comedy|Romance"));
        this.movieRepo.save(new Movie("Star Wars: Episode V - The Empire Strikes Back(1997)", 11l,"Comedy|Drama"));
        this.movieRepo.save(new Movie("Forrest Gump(1995)", 12l,"Animation|Children’s|Comedy"));
        this.movieRepo.save(new Movie("The Lord of the Rings: The Two Towers(1998)", 13l,"Comedy|Romance"));
        this.movieRepo.save(new Movie("One Flew Over the Cuckoo's Nest(1995)", 14l,"Animation|Children’s|Comedy"));
        this.movieRepo.save(new Movie("Goodfellas(1999)", 15l,"Comedy|Drama"));
        this.movieRepo.save(new Movie("The Matrix(1995)", 16l,"Comedy|Romance"));
        this.movieRepo.save(new Movie("Goodfellas(1996)", 15l,"Animation|Children’s|Comedy"));
        this.movieRepo.save(new Movie("The Matrix(1995)", 16l,"Comedy|Drama"));
        this.movieRepo.save(new Movie("Rocky(1997)", 17l,"Comedy|Romance"));
        this.movieRepo.save(new Movie("Old boy(1998)", 18l,"Animation|Children’s|Comedy"));

//        1::Toy Story (1995)::Animation|Children’s|Comedy
//        2::Jumanji (1995)::Adventure|Children’s|Fantasy
//        3::Grumpier Old Men (1995)::Comedy|Romance
//        4::Waiting to Exhale (1995)::Comedy|Drama
//        5::Father of the Bride Part II (1995)::Comedy
//        6::Heat (1995)::Action|Crime|Thriller
//        7::Sabrina (1995)::Comedy|Romance
//        8::Tom and Huck (1995)::Adventure|Children’s
    }
    
}
