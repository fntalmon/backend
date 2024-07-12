package com.example.demo.project.services;
import com.example.demo.project.models.Note;
import com.example.demo.project.models.Tag;
import com.example.demo.project.repositories.NoteRepository;
import com.example.demo.project.repositories.TagRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class NoteService {

    private NoteRepository noteRepository;
    private TagRepository tagRepository;

    @Transactional
    public Note saveNote(Note note) {
        Set<Tag> existingTags = new HashSet<>();
        for (Tag tag : note.getTags()) {
            Tag existingTag = tagRepository.findById(tag.getTagId())
                    .orElseThrow(() -> new IllegalArgumentException("Tag not found: " + tag.getTagId()));
            existingTags.add(existingTag);
        }
        note.setTags(existingTags);
        return noteRepository.save(note);
    }

    public Note getNoteById(Long noteID){
        return noteRepository.findById(noteID).orElseThrow(() -> new IllegalArgumentException("Note not found: " + noteID));
    }

    @Transactional
    public String deleteNote(Long noteId) {
        if (noteRepository.existsById(noteId)) {
            noteRepository.deleteById(noteId);
            return "Note removed successfully: " + noteId;
        } else {
            return "Note not found: " + noteId;
        }
    }

    public Note changeActive(Long noteId){

       Note saved = noteRepository.findById(noteId).orElseThrow(() -> new IllegalArgumentException("Note not found: " + noteId));
       saved.setActive(!saved.isActive());
       return noteRepository.save(saved);
    }


    @Transactional
    public Note updateNote(Note note) {
        Note existingNote = noteRepository.findById(note.getNoteId()).orElseThrow(() -> new IllegalArgumentException("Note not found: " + note.getNoteId()));
        Set<Tag> existingTags = new HashSet<>();
        for (Tag tag : note.getTags()) {
            Tag existingTag = tagRepository.findById(tag.getTagId())
                    .orElseThrow(() -> new IllegalArgumentException("Tag not found: " + tag.getTagId()));
            existingTags.add(existingTag);
        }
        existingNote.setTags(existingTags);
        existingNote.setTitle(note.getTitle());
        existingNote.setContent(note.getContent());
        existingNote.setActive(note.isActive());
        return noteRepository.save(existingNote);
    }


    public List<Note> getNotes(){
        return noteRepository.findAll();
    }
}
