/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

/**
 *
 * @author milandeket
 */
public class MovieDTO {
    
    private Long movieId;
    private String title;
    private String genres;//电影类别

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public MovieDTO() {
    }
    
    public MovieDTO(Movie movie){
        this.movieId = movie.getMovieId();
        this.title = movie.getTitle();
        this.genres = movie.getGenres();
    }
    
    public Long getMovieId() {
        return movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
}
