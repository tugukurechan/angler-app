package angler_app.tugu.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class blogController {
    @GetMapping("/top")
    public String getPosts(){
        return "react-running";
    }

    @GetMapping("/test")
    public String getTest(){
        return "this is test";
    }
    @PostMapping("/posts")
    public String createPost(@RequestBody String post){
        return "Post created: " + post;
    }
}
