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
    public ModelAndView startup(
                    @RequestParam(value="size", required=false, defaultValue="10") int size,
                    @RequestParam(value="pageUser", required=false, defaultValue="0") int pageUser,
                    @RequestParam(value="pageProt", required=false, defaultValue="0") int pageProt,
                    @RequestParam(value="pagePror", required=false, defaultValue="0") int pagePror

    ) {

        //return back to index.jsp
        ModelAndView modelAndView = new ModelAndView("home/home");
        modelAndView.addObject("userList", userRepository.findAll(new PageRequest(pageUser,size)).iterator());
        modelAndView.addObject("producerList", producerRepository.findAll(new PageRequest(pagePror,size)).iterator());
        modelAndView.addObject("productList", productRepository.findAll(new PageRequest(pageProt,size)).iterator());

        return modelAndView;
    }

}
