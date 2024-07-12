package com.example.demo.project.controller;

import com.example.demo.project.models.Note;
import com.example.demo.project.models.Tag;
import com.example.demo.project.services.NoteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/notes")
public class NoteController {

    private NoteService noteService;

    @PostMapping("/saveNote")
    public Note saveNote(@RequestBody Note note){
        return noteService.saveNote(note);
    }

    @PutMapping("/changeActive")
    public Note changeActive(@RequestBody Long noteId){
        return noteService.changeActive(noteId);
    }

    @GetMapping("/getNotes")
    public List<Note> getNotes(){
        return noteService.getNotes();
    }

    @GetMapping(value = "/notes/{noteId}")
    public Note getNotes(@PathVariable Long noteId) {
        return noteService.getNoteById(noteId);
    }
    @DeleteMapping("/delete/{id}")
    public String deleteNote(@PathVariable Long id) {
        return noteService.deleteNote(id);
    }

    @PutMapping("/updateNote")
    public Note updateNote(@RequestBody Note note){
        return noteService.updateNote(note);
    }
}
