package com.donald.web.admin;

import com.donald.pojo.Type;
import com.donald.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @author Donald
 * @data 23/04/2020 11:27
 */
@Controller
@RequestMapping("/admin")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String types(@PageableDefault(size = 3,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable, Model model){
        model.addAttribute("page",typeService.listType(pageable));
        return "admin/types";
    }

    @PostMapping("/types")
    public String post(@Valid Type type, BindingResult result,RedirectAttributes attributes){
        Type type1 = typeService.getTypeByName(type.getName());
        if(type1!=null){
            result.rejectValue("name","nameError","This category already exists");
        }

        if (result.hasErrors()){
            return "admin/type-input";
        }

        Type t = typeService.saveType(type);
        if(t == null){
            attributes.addFlashAttribute("message","adding error");
        }else{
            attributes.addFlashAttribute("message","adding successfully");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type",new Type());
        return "admin/type-input";
    }


    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable("id") Long id, Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/type-input";
    }

    @PostMapping("/types/{id}")
    public String editPost(@Valid Type type, BindingResult result,@PathVariable("id") Long id,RedirectAttributes attributes){
        Type type1 = typeService.getTypeByName(type.getName());
        if(type1!=null){
            result.rejectValue("name","nameError","This category already exists");
        }

        if (result.hasErrors()){
            return "admin/type-input";
        }

        Type t = typeService.updateType(id,type);
        if(t == null){
            attributes.addFlashAttribute("message","updating error");
        }else{
            attributes.addFlashAttribute("message","updating successfully");
        }
        return "redirect:/admin/types";
    }


    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable("id") Long id,RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message","deleting error");
        return "redirect:/admin/types";
    }
}
