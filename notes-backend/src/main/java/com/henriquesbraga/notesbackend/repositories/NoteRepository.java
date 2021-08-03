package com.henriquesbraga.notesbackend.repositories;

import java.util.List;

import com.henriquesbraga.notesbackend.entities.Note;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NoteRepository extends JpaRepository <Note, Long>{
  
  @Query(nativeQuery = true, value = "SELECT * FROM note")
  List<Note> findAll();
}
