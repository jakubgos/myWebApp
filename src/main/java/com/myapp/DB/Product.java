package com.myapp.DB;

import javax.persistence.*;

/**
 * Created by Bos on 2017-01-08.
 */

@Entity
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Producer producer;

    private Long isBorrowed;

    private String name;



    public Product(Producer producer, String name, Long isBorrowed) {
        this.producer = producer;
        this.name = name;
        this.isBorrowed = isBorrowed;
    }

    public Product(Producer one, String name) {
        this.producer = one;
        this.name = name;
    }

    public Product() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public Long getIsBorrowed() {
        return isBorrowed;
    }

    public void setIsBorrowed(Long isBorrowed) {
        this.isBorrowed = isBorrowed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
