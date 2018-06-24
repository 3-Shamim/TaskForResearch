package com.learningstuff.stockexchange_application.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class User {

    @Id
    @Column(unique = true)
    private String email;
    private String password;
    private Name name;
    private Gender gender;
    private String number;
    private Address address;
    private LocalDate birthDay;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable
    private List<Role> roles;

    public User() {
    }

    public User(String email, String password, Name name, Gender gender, String number, Address address, LocalDate birthDay, List<Role> roles) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.number = number;
        this.address = address;
        this.birthDay = birthDay;
        this.roles = roles;
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

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name=" + name +
                ", gender=" + gender +
                ", number='" + number + '\'' +
                ", address=" + address +
                ", birthDay=" + birthDay +
                ", roles=" + roles +
                '}';
    }
}
