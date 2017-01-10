package com.myapp.Controller.home;

import com.myapp.DB.ProducerRepository;
import com.myapp.DB.ProductRepository;
import com.myapp.DB.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Bos on 2017-01-08.
 */
@Controller
public class HomeController {

    @Autowired
    private ProducerRepository producerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;


    @RequestMapping(value = {"/","/home/home"})
    public ModelAndView homeReq(
                    @RequestParam(value="size", required=false, defaultValue="5") int size,
                    @RequestParam(value="pageUser", required=false, defaultValue="0") int pageUser,
                    @RequestParam(value="pageProt", required=false, defaultValue="0") int pageProt,
                    @RequestParam(value="pagePror", required=false, defaultValue="0") int pagePror

    ) {
        if (size < 1) size=1;
        if (pageUser < 0) pageUser=0;
        if (pageProt < 0) pageProt=0;
        if (pagePror < 0) pagePror=0;



        ModelAndView modelAndView = new ModelAndView("home/home");
        modelAndView.addObject("userList", userRepository.findAll(new PageRequest(pageUser,size)).iterator());
        modelAndView.addObject("producerList", producerRepository.findAll(new PageRequest(pagePror,size)).iterator());
        modelAndView.addObject("productList", productRepository.findAll(new PageRequest(pageProt,size)).iterator());
        modelAndView.addObject("size", size);
        modelAndView.addObject("pageUser", pageUser);
        modelAndView.addObject("pageProt", pageProt);
        modelAndView.addObject("pagePror", pagePror);


        return modelAndView;
    }

}
