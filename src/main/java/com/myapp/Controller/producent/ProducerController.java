package com.myapp.Controller.producent;

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

/**
 * Created by Bos on 2017-01-08.
 */
@Controller
public class ProducerController {

    @Autowired
    private ProducerRepository producerRepository;




    @RequestMapping("/home/producers")
    public ModelAndView userReq(
                    @RequestParam(value="size", required=false, defaultValue="5") int size,
                    @RequestParam(value="page", required=false, defaultValue="0") int page
    ) {
        if (size < 1) size=1;
        if (page < 0) page=0;

        ModelAndView modelAndView = new ModelAndView("home/producers", "command", new commandProducer());
        modelAndView.addObject("producerList", producerRepository.findAll(new PageRequest(page,size)).iterator());
        modelAndView.addObject("size", size);
        modelAndView.addObject("page", page);


        return modelAndView;
    }

    @RequestMapping(value = "/home/addProducer", method = RequestMethod.POST)
    public ModelAndView addEmployee(@ModelAttribute("SpringWeb")commandProducer command,
                              ModelMap model) {

        Producer newUser = new Producer(command.getName());
        producerRepository.save(newUser);

        ModelAndView modelAndView = new ModelAndView("home/result");
        modelAndView.addObject("resultCause", 1);
        modelAndView.addObject("msg", "You successfully added new Producer!");
        modelAndView.addObject("back", "/home/producers");

        return modelAndView;
    }

    @RequestMapping("/home/userActionProducer")
    public ModelAndView actionReq(
            @ModelAttribute("SpringWeb")commandProducer command,
            @RequestParam(value="action", required=false, defaultValue="none") String action,
            @RequestParam(value="userId", required=false, defaultValue="0") long userId
    ) {
        ModelAndView modelAndView = null;
         if( action.equals("editProducer") ){


            modelAndView = new ModelAndView("home/editProducer","command", new commandProducer());
            modelAndView.addObject("producer", producerRepository.findOne(command.getId()));
        }

        else if( action.equals("delProducer") ){
            producerRepository.delete(command.getId());

            modelAndView = new ModelAndView("home/result");
            modelAndView.addObject("resultCause", 1);
            modelAndView.addObject("msg", "You successfully deleted producer!");
            modelAndView.addObject("back", "/home/producers");
        }
        else
            {
                modelAndView = new ModelAndView("home/result");
                modelAndView.addObject("resultCause", 0);
                modelAndView.addObject("msg", "Ups....");
                modelAndView.addObject("back", "/home/producers");
            }

        return modelAndView;
    }

    @RequestMapping(value = "/home/editConfirmProducers", method = RequestMethod.POST)
    public ModelAndView editConfirm(@ModelAttribute("SpringWeb")commandProducer command,
                                    ModelMap model) {

        Producer newUser = new Producer(command.getId(),command.getName());
        producerRepository.save(newUser);

        ModelAndView modelAndView = new ModelAndView("home/result");
        modelAndView.addObject("resultCause", 1);
        modelAndView.addObject("msg", "You successfully edited producer!");
        modelAndView.addObject("back", "/home/producers");

        return modelAndView;
    }


}
