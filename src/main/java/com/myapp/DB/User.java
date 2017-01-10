package com.myapp.DB;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by Bos on 2017-01-08.
 */
@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    @OneToMany
    private List<Product> products;

    public User(long id, String firstName, String lastName) {
        this.id=id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(String firstName, String lastName, List<Product> products) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.products = products;
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
