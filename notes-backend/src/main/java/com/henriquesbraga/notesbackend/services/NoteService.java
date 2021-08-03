package com.henriquesbraga.notesbackend.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.henriquesbraga.notesbackend.dtos.NewNoteDTO;
import com.henriquesbraga.notesbackend.dtos.NoteDTO;
import com.henriquesbraga.notesbackend.entities.Note;
import com.henriquesbraga.notesbackend.repositories.NoteRepository;
import com.henriquesbraga.notesbackend.services.exceptions.DataIntegrityException;
import com.henriquesbraga.notesbackend.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class NoteService {
  
  @Autowired
  private NoteRepository noteRepository;

  // create
  public Note create(Note note){
    note.setId(null);
    note.setDate(new Date());
    note = noteRepository.save(note);
    return note;
  }

  // read
  public Note findById(Long id){
    Optional<Note> note = noteRepository.findById(id);
    return note.orElseThrow(() -> new ObjectNotFoundException("Nota não encontrada!"));
  }

  // read all
  public List<Note> findAll(){
    List<Note> list = noteRepository.findAll();
    return list;
  }

  // update
  public Note update(Note note){
    Note newNote = findById(note.getId());
    updateData(newNote, note);
    return noteRepository.save(newNote);
  }

  // delete
  public void delete(Long id) {
		findById(id);
		try {
			noteRepository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há pedidos relacionados");
		}
	}


  //Update data
  private void updateData(Note newNote, Note note) {
    newNote.setTitle(note.getTitle());
    newNote.setNote(note.getNote());
    newNote.setColor(note.getColor());
  }


  //From dto
  public Note fromDTO(NoteDTO objDto){
    return new Note(objDto.getId(), objDto.getTitle(), objDto.getNote(), objDto.getColor(), objDto.getDate());
  }

  public Note fromDTO(NewNoteDTO objDto){
    return new Note(objDto.getId(), objDto.getTitle(), objDto.getNote(), objDto.getColor(), objDto.getDate());
  }


}
