package com.henriquesbraga.notesbackend.services.seed;

import java.util.Arrays;
import java.util.Date;

import com.henriquesbraga.notesbackend.entities.Note;
import com.henriquesbraga.notesbackend.repositories.NoteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class NoteSeed {
  
  @Autowired
  private NoteRepository noteRepository;

  @Bean
  public void seed(){
    Note note = new Note(null, "Titulo da nota", "Cor Prata", "-2234644", new Date());
    Note note1 = new Note(null, "Titulo da nota", "Cor Azul", "-8469267", new Date());
    Note note2 = new Note(null, "Titulo da nota", "Cor Lima", "-3081091", new Date());
    Note note3 = new Note(null, "Titulo da nota", "Cor Vermelho", "-1222758", new Date());
    Note note4 = new Note(null, "Titulo da nota", "Cor Verde", "-13963914", new Date());

    noteRepository.saveAll(Arrays.asList(note, note1, note2, note3, note4));
  }
}
