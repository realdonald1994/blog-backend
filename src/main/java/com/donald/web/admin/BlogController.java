package com.donald.web.admin;

import com.donald.pojo.Blog;
import com.donald.pojo.User;
import com.donald.service.BlogService;
import com.donald.service.TagService;
import com.donald.service.TypeService;
import com.donald.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author Donald
 * @data 22/04/2020 23:27
 */
@Controller
@RequestMapping("/admin")
public class BlogController {
    private static final String INPUT = "admin/blog-input";
    private static final String LIST = "admin/blogs";
    private static final String REDIRECT_LIST = "redirect:/admin/blogs";



    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size = 2,sort = "updateTime",direction = Sort.Direction.DESC) Pageable pageable, BlogQuery blog, Model model){
        model.addAttribute("page",blogService.listBlog(pageable,blog));
        model.addAttribute("types",typeService.listType());
        return LIST;
    }

    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 2,sort = "updateTime",direction = Sort.Direction.DESC) Pageable pageable, BlogQuery blog, Model model){
        model.addAttribute("page",blogService.listBlog(pageable,blog));
        return "admin/blogs :: blogList";
    }

    @GetMapping("/blogs/input")
    public String input(Model model){
        setTypeAndTag(model);
        model.addAttribute("blog",new Blog());
        return INPUT;
    }

    private void setTypeAndTag(Model model){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTag());

    }

    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable("id") Long id, Model model){
        setTypeAndTag(model);
        Blog blog = blogService.getBlog(id);
        blog.init();
        model.addAttribute("blog",blog);
        return INPUT;
    }

    @PostMapping("/blogs")
    public String post(Blog blog, HttpSession session, RedirectAttributes attributes){
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTags(tagService.listTag(blog.getTagIds()));
        Blog b;
        if(blog.getId()==null){
            b= blogService.saveBlog(blog);
        }else{
            b=blogService.updateBlog(blog.getId(),blog);
        }
        if(b == null){
            attributes.addFlashAttribute("message","operating error");
        }else{
            attributes.addFlashAttribute("message","operating successfully");
        }
        return REDIRECT_LIST;
    }

    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable("id")Long id,RedirectAttributes attributes){
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message","deleting successfully");
        return REDIRECT_LIST;
    }

}
