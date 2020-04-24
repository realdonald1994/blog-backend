package com.donald.web.admin;

import com.donald.pojo.Tag;
import com.donald.pojo.Type;
import com.donald.service.TagService;
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
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public String types(@PageableDefault(size = 3,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable, Model model){
        model.addAttribute("page",tagService.listTag(pageable));
        return "admin/tags";
    }

    @PostMapping("/tags")
    public String post(@Valid Tag tag, BindingResult result, RedirectAttributes attributes){
        Tag tag1 = tagService.getTagByName(tag.getName());
        if(tag1!=null){
            result.rejectValue("name","nameError","This tag already exists");
        }

        if (result.hasErrors()){
            return "admin/tag-input";
        }

        Tag t = tagService.saveTag(tag);
        if(t == null){
            attributes.addFlashAttribute("message","adding error");
        }else{
            attributes.addFlashAttribute("message","adding successfully");
        }
        return "redirect:/admin/tags";
    }
//
    @GetMapping("/tags/input")
    public String input(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/tag-input";
    }

    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable("id") Long id,Model model){
        model.addAttribute("tag",tagService.getTag(id));
        return "admin/tag-input";
    }

    @GetMapping("tags/{id}/delete")
    public String delete(@PathVariable("id") Long id,RedirectAttributes attributes){
        this.tagService.deleteTag(id);
        attributes.addFlashAttribute("message","deleting successfully");
        return "redirect:/admin/tags";
    }

    @PostMapping("tags/{id}")
    public String update(@Valid Tag tag,BindingResult bindingResult,@PathVariable("id") Long id,RedirectAttributes redirectAttributes){
        Tag tag1 = tagService.getTagByName(tag.getName());
        if(tag1!=null){
            bindingResult.rejectValue("name","nameError","This tag already exist");
        }
        if(bindingResult.hasErrors()){
            return "admin/tag-input";
        }

        Tag t = tagService.updateTag(id, tag);
        if(t==null){
            redirectAttributes.addFlashAttribute("message","updating error");
        }else {
            redirectAttributes.addFlashAttribute("message","updating successfully");
        }
        return "redirect:/admin/tags";

    }

}
