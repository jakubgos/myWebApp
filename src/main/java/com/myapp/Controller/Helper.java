package com.myapp.Controller;

import com.myapp.DB.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * Created by Bos on 2017-01-06.
 */
@RestController
public class Helper {



   // public List<Customer> dupa() {

    //    List<Customer> list =  customerRepository.findByFirstName("a");
  //      return list;
  //  }

    /**
     * GET /create  --> Create a new user and save it in the database.
     */
    @RequestMapping("/create")
    public String create(@NotNull String firstName, String lastName) {
        String userId = "";
        try {
      //      Customer customer = new Customer(firstName, lastName);
     //       customerRepository.save(customer);
          //  userId = String.valueOf(customer.getId());
        }
        catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
        return "User succesfully created with id = " + userId;
    }

    /**
     * GET /delete  --> Delete the user having the passed id.
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(long id) {
        try {
     //       Customer user = new Customer(id);
    //        customerRepository.delete(user);
        }
        catch (Exception ex) {
            return "Error deleting the user:" + ex.toString();
        }
        return "User succesfully deleted!";
    }

    /**
     * GET /get-by-email  --> Return the id for the user having the passed
     * email.
     */
    @RequestMapping("/get-by-email")
    @ResponseBody
    public String getByEmail(String lastName) {
        String userId = "";
        try {
        //   Customer user = customerRepository.findByLastName(lastName);
      //      userId = String.valueOf(user.getId());
        }
        catch (Exception ex) {
            return "User not found";
        }
        return "The user id is: " + userId;
    }


    @Autowired
    private ProducerRepository producerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;


    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        System.out.println(" ");
        System.out.println("dupa");
        Producer producer1 = new Producer("nazwa");
        producerRepository.save(producer1);
        System.out.println("dupa 1");
        Product prod1 = new Product(producer1,"nazwa1", (long) 0);
        productRepository.save(prod1);

        Product prod2 = new Product(producer1,"nazwa2", (long) 1);
        productRepository.save(prod2);
        System.out.println("dupa 2");


        Product prod3 = new Product(producer1,"nazwa3", (long) 1);
        productRepository.save(prod3);
        System.out.println("dupa 2");

        List<Product> list = new LinkedList<Product>();
        list.add(prod1);
        list.add(prod2);


        User use = new User("user1", "ln",list);
        userRepository.save(use);
        System.out.print("dupa 3");

        User userToEdit = userRepository.findOne(use.getId());

        userToEdit.getProducts().add(prod3);

        userRepository.save(userToEdit);


        User userrrr= new User("user3", "ln",null);
        userRepository.save(userrrr);
        System.out.print("dupa 3");





        return "ok";
    }

}