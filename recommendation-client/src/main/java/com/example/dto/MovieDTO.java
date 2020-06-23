/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.dto;

/**
 *
 * @author ljz
 */
public class MovieDTO {
    
    private String movieId;
    
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

    public MovieDTO(String movieId, String title) {
        this.movieId = movieId;
        this.title = title;
    }

    public MovieDTO(String title, String movieId, String genres) {
        this.movieId = movieId;
        this.title = title;
        this.genres = genres;
    }
//    public MovieDTO(String movieId, String title, Integer likes) {
//        this.movieId = movieId;
//        this.title = title;
//        this.likes = likes;
//    }

    public String getMovieId() {
        return movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
}
