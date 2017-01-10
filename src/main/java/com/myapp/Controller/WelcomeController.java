package com.myapp.Controller;


import com.myapp.model.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Bos on 2017-01-07.
 */


@Controller
public class WelcomeController {




    @RequestMapping("/home/equipment")
    public String hello3(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
        return "home/equipment";
    }

    @RequestMapping("/home/producers")
    public String hello4(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
        return "home/producers";
    }


    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public ModelAndView student() {
        return new ModelAndView("student", "command", new Student());
    }

    @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
    public String addStudent(Student student,
                             ModelMap model) {
        model.addAttribute("name", student.getName());
        model.addAttribute("age", student.getAge());
        model.addAttribute("id", student.getId());

        return "result";
    }



}

