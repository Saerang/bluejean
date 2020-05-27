package com.jean.blue.web;

import com.jean.blue.config.MyProfile;
import com.jean.blue.service.FeesService;
import com.jean.blue.service.PostsService;
import com.jean.blue.web.dto.posts.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final FeesService feesService;


    @GetMapping(value = {"/main"})
    public String main(Model model){
        model.addAttribute("posts", postsService.findAllDesc());
        return "main.html";
    }

    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute("posts", postsService.findAllDesc());
        return "index.html";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save.html";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update.html";
    }

}
