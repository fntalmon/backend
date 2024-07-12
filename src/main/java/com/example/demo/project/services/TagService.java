package com.example.demo.project.services;

import com.example.demo.project.models.Tag;
import com.example.demo.project.repositories.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TagService {


    TagRepository tagRepository;
    public List<Tag> getTags(){
        return tagRepository.findAll();
    }

    public Tag setTag(Tag tag){
        return tagRepository.save(tag);
    }
}
