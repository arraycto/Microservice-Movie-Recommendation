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
public class UserDTO {
    private Long Id;
    private String name;
    private String email;
    private String password;
    private String type;
    private String gender;
    private String age;
    private String occupation;
    private String zipcode;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public UserDTO(String name, String email, String password, String type, String gender, String age, String occupation, String zipcode) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.type = type;
        this.gender = gender;
        this.age = age;
        this.occupation = occupation;
        this.zipcode = zipcode;
    }

    public UserDTO(Long id, String name, String email, String password, String type) {
        Id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.type = type;
    }

    public UserDTO() {
    }

    public UserDTO(Long Id, String name) {
        this.Id = Id;
        this.name = name;
    }


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
