package com.example.demo.project.controller;

import com.example.demo.project.models.Note;
import com.example.demo.project.models.Tag;
import com.example.demo.project.services.TagService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/tags")
@CrossOrigin(origins = "*")
public class TagController {

    TagService tagService;

    @GetMapping("/getTags")
    public List<Tag> getTags(){
        return tagService.getTags();
    }

    @PostMapping("/setTag")
    public Tag setTag(@RequestBody Tag tag){
        return tagService.setTag(tag);
    }
}
