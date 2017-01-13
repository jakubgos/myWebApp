package com.myapp;

import com.myapp.Controller.user.command;
import com.myapp.DB.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedList;

/**
 * Created by Bos on 2017-01-13.
 */
@RestController
public class HelpController {

    static public int id = 0;

    @Autowired
    private ProducerRepository producerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(value = "/test")
    public String addEmployeeasdads() {

        Producer producer = new Producer("producer_" + id);
        Product item = new Product(producer, "Item_" + id, new Long(1));
        LinkedList<Product> list = new LinkedList<Product>();
        list.add(item);
        User user = new User("first_name_"+id, "last_name_"+id, list);


        Product item2 = new Product(producer, "Item_free_" + id, new Long(0));
        id++;

        producerRepository.save(producer);
        productRepository.save(item);
        productRepository.save(item2);
        userRepository.save(user);



        return "ok";
    }
}
