package com.myapp.Controller;

import com.myapp.Controller.producent.commandProducer;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Bos on 2017-01-13.
 */
@Controller
public class ErrorHandler implements ErrorController {

    @RequestMapping(value = "/error")
    public ModelAndView editConfirm() {

        ModelAndView modelAndView = new ModelAndView("home/result");
        modelAndView.addObject("resultCause", 0);
        modelAndView.addObject("msg", "No !!");
        modelAndView.addObject("back", "/home/home");

        return modelAndView;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
