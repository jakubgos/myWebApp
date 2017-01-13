package com.myapp.Controller.user;

import com.myapp.DB.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Bos on 2017-01-08.
 */
@Controller
public class UserController {

    @Autowired
    private ProducerRepository producerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;



    @RequestMapping("/home/users")
    public ModelAndView userReq(
                    @RequestParam(value="size", required=false, defaultValue="5") int size,
                    @RequestParam(value="pageUser", required=false, defaultValue="0") int pageUser
    ) {
        if (size < 1) size=1;
        if (pageUser < 0) pageUser=0;

        ModelAndView modelAndView = new ModelAndView("home/users", "command", new command());
        modelAndView.addObject("userList", userRepository.findAll(new PageRequest(pageUser,size)).iterator());
        modelAndView.addObject("size", size);
        modelAndView.addObject("pageUser", pageUser);


        return modelAndView;
    }

    @RequestMapping(value = "/home/addUser", method = RequestMethod.POST)
    public ModelAndView addEmployee(@ModelAttribute("SpringWeb")command command,
                              ModelMap model) {

        User newUser = new User(command.getFirstName(),command.getLastName());
        userRepository.save(newUser);

        ModelAndView modelAndView = new ModelAndView("home/result");
        modelAndView.addObject("resultCause", 1);
        modelAndView.addObject("msg", "You successfully added new user!");
        modelAndView.addObject("back", "/home/users");

        return modelAndView;
    }

    @RequestMapping("/home/userAction")
    public ModelAndView actionReq(
            @ModelAttribute("SpringWeb")command command,
            @RequestParam(value="action", required=false, defaultValue="none") String action,
            @RequestParam(value="userId", required=false, defaultValue="0") long userId
    ) {
        ModelAndView modelAndView = null;
        if( action.equals("editItem") ){
            modelAndView = new ModelAndView("home/editUserItems", "command", new commandEditItems());
            modelAndView.addObject("user", userRepository.findOne(command.getId()));
            modelAndView.addObject("availItem", productRepository.findByIsBorrowed(new Long(0)));


        }
        else if( action.equals("editUser") ){
            modelAndView = new ModelAndView("home/editUser","command", new command());
            modelAndView.addObject("user", userRepository.findOne(command.getId()));
        }
        else if( action.equals("delUser") ){
            if(userRepository.findOne(command.getId()).getProducts().isEmpty()) {
                userRepository.delete(command.getId());
                modelAndView = new ModelAndView("home/result");
                modelAndView.addObject("resultCause", 1);
                modelAndView.addObject("msg", "You successfully deleted user!");
                modelAndView.addObject("back", "/home/users");
            } else {

                modelAndView = new ModelAndView("home/result");
                modelAndView.addObject("resultCause", 0);
                modelAndView.addObject("msg", "No! This user have some products!!");
                modelAndView.addObject("back", "/home/users");
            }


        }
        else
            {
                modelAndView = new ModelAndView("home/result");
                modelAndView.addObject("resultCause", 0);
                modelAndView.addObject("msg", "Ups....");
                modelAndView.addObject("back", "/home/users");
            }



        return modelAndView;
    }

    @RequestMapping(value = "/home/editConfirm", method = RequestMethod.POST)
    public ModelAndView editConfirm(@ModelAttribute("SpringWeb")command command,
                                    ModelMap model) {

        User newUser = userRepository.findOne(command.getId());
        newUser.setFirstName(command.getFirstName());
        newUser.setLastName(command.getLastName());
        userRepository.save(newUser);

        ModelAndView modelAndView = new ModelAndView("home/result");
        modelAndView.addObject("resultCause", 1);
        modelAndView.addObject("msg", "You successfully edited user!");
        modelAndView.addObject("back", "/home/users");

        return modelAndView;
    }


    @RequestMapping(value = "/home/editConfirmItem", method = RequestMethod.POST)
    public ModelAndView editConfirm(@ModelAttribute("SpringWeb")commandEditItems command,
                                    ModelMap model) {



        User newUser = userRepository.findOne(command.getUserId());

        for (Product pord:newUser.getProducts()) {
            pord.setIsBorrowed(new Long(0));
            productRepository.save(pord);
        }

        List<Product> list = new LinkedList<Product>();
        for (Long id:command.getUserItems()) {
            Product item = productRepository.findOne(id);
            list.add(item);
            item.setIsBorrowed(new Long(1));
            productRepository.save(item);
        }
        newUser.setProducts(list);
        userRepository.save(newUser);

        ModelAndView modelAndView = new ModelAndView("home/result");
        modelAndView.addObject("resultCause", 1);
        modelAndView.addObject("msg", "You successfully edited user items!");
        modelAndView.addObject("back", "/home/users");

        return modelAndView;
    }


}
