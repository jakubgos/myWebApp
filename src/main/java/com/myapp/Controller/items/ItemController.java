package com.myapp.Controller.items;

import com.myapp.DB.Producer;
import com.myapp.DB.ProducerRepository;
import com.myapp.DB.Product;
import com.myapp.DB.ProductRepository;
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
public class ItemController {

    @Autowired
    private ProductRepository equipmentRepository;

    @Autowired
    private ProducerRepository producerRepository;




    @RequestMapping("/home/equipment")
    public ModelAndView userReq(
                    @RequestParam(value="size", required=false, defaultValue="5") int size,
                    @RequestParam(value="page", required=false, defaultValue="0") int page
    ) {
        if (size < 1) size=1;
        if (page < 0) page=0;

        ModelAndView modelAndView = new ModelAndView("home/equipment", "command", new commandEq());
        modelAndView.addObject("equipmentList", equipmentRepository.findAll(new PageRequest(page,size)).iterator());
        modelAndView.addObject("producentList", producerRepository.findAll());

        modelAndView.addObject("size", size);
        modelAndView.addObject("page", page);


        return modelAndView;
    }

    @RequestMapping(value = "/home/addEquipment", method = RequestMethod.POST)
    public ModelAndView addEmployee(@ModelAttribute("SpringWeb")commandEq command,
                              ModelMap model) {

        Product newProduct = new Product(producerRepository.findOne(command.getIdEqSelected()), command.getName(), new Long(0));
        equipmentRepository.save(newProduct);

        ModelAndView modelAndView = new ModelAndView("home/result");
        modelAndView.addObject("resultCause", 1);
        modelAndView.addObject("msg", "You successfully added new Product!");
        modelAndView.addObject("back", "/home/equipment");

        return modelAndView;
    }

    @RequestMapping("/home/userActionEquipment")
    public ModelAndView actionReq(
            @ModelAttribute("SpringWeb")commandEq command,
            @RequestParam(value="action", required=false, defaultValue="none") String action,
            @RequestParam(value="userId", required=false, defaultValue="0") long userId
    ) {
        ModelAndView modelAndView = null;
         if( action.equals("editEq") ){


            modelAndView = new ModelAndView("home/editEquipment","command", new commandEq());
             modelAndView.addObject("eqToEdit", equipmentRepository.findOne(command.getId()));
             modelAndView.addObject("producerList", producerRepository.findAll());

         }

        else if( action.equals("delEq") ){
             equipmentRepository.delete(command.getId());

            modelAndView = new ModelAndView("home/result");
            modelAndView.addObject("resultCause", 1);
            modelAndView.addObject("msg", "You successfully deleted item!");
            modelAndView.addObject("back", "/home/equipment");
        }
        else
            {
                modelAndView = new ModelAndView("home/result");
                modelAndView.addObject("resultCause", 0);
                modelAndView.addObject("msg", "Ups....");
                modelAndView.addObject("back", "/home/equipment");
            }

        return modelAndView;
    }

    @RequestMapping(value = "/home/editConfirmEq", method = RequestMethod.POST)
    public ModelAndView editConfirm(@ModelAttribute("SpringWeb")commandEq command,
                                    ModelMap model) {

        System.out.println(command.getId()+ " " +  command.getName() + " " + command.getIdEqSelected());

        Product newItem = equipmentRepository.findOne(command.getId());
        newItem.setName(command.getName());
        newItem.setProducer(producerRepository.findOne(command.getIdEqSelected()));
        equipmentRepository.save(newItem);

        ModelAndView modelAndView = new ModelAndView("home/result");
        modelAndView.addObject("resultCause", 1);
        modelAndView.addObject("msg", "You successfully edited Item!");
        modelAndView.addObject("back", "/home/equipment");

        return modelAndView;
    }


}
