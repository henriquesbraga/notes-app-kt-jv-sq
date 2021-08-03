package com.henriquesbraga.notesbackend.controllers;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import com.henriquesbraga.notesbackend.dtos.NewNoteDTO;
import com.henriquesbraga.notesbackend.dtos.NoteDTO;
import com.henriquesbraga.notesbackend.entities.Note;
import com.henriquesbraga.notesbackend.services.NoteService;
import com.henriquesbraga.notesbackend.services.RequestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping("/")
public class NoteController {
  
  @Autowired
  NoteService noteService;

  @Autowired
  RequestService requestService;

  @PostMapping(value = "test")
  public void process(@RequestBody Map<String, Object> payload) throws Exception {
    System.out.println(payload);  
  }



  // create
  @PostMapping(value = "/insert")
  public ResponseEntity<HashMap<String, Object>> insert(@RequestBody NewNoteDTO objDto, HttpServletRequest request) {
    
    String clientIp = requestService.getClientIp(request);
    System.out.println("POST - Client IP: " + clientIp);

    Note note = noteService.fromDTO(objDto);
    note = noteService.create(note);
    
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
    .path("/{id}").buildAndExpand(note.getId()).toUri();
    
    HashMap<String, Object> map = new HashMap<>();
    map.put("success", true);
    map.put("message", "Successfully");

    return ResponseEntity.created(uri).body(map);
  }

  // read by id
  @GetMapping(value = "/get/{id}")
  public ResponseEntity<Note> findById(@PathVariable(value = "id") Long id, HttpServletRequest request){
    String clientIp = requestService.getClientIp(request);
    System.out.println("GET - Client IP: " + clientIp);
    Note note = noteService.findById(id);
    return ResponseEntity.ok().body(note);
  }

  // read all
  @GetMapping(value = "/get/all")
  public ResponseEntity<List<NoteDTO>> findAll(HttpServletRequest request){
    String clientIp = requestService.getClientIp(request);
    System.out.println("GET - Client IP: " + clientIp);
    List<Note> list = noteService.findAll();
    List<NoteDTO> listDto = list.stream().map((obj) -> new NoteDTO(obj)).collect(Collectors.toList());
    return ResponseEntity.ok().body(listDto);
  }

  // update
  @PutMapping(value = "/update")
  public ResponseEntity<HashMap<String, Object>> update(@RequestBody NoteDTO objDto, HttpServletRequest request){
    String clientIp = requestService.getClientIp(request);
    System.out.println("PUT - Client IP: " + clientIp);
    Note note = noteService.fromDTO(objDto);
    note.setId(objDto.getId());
    note = noteService.update(note);

    HashMap<String, Object> map = new HashMap<>();
    map.put("success", true);
    map.put("message", "Successfully Updated");

    return ResponseEntity.status(HttpStatus.OK).body(map);
  }

  // delete
  @DeleteMapping(value = "/delete/{id}")
  public ResponseEntity<HashMap<String, Object>> delete(@PathVariable(name = "id") Long id, HttpServletRequest request){
    String clientIp = requestService.getClientIp(request);
    System.out.println("DELETE - Client IP: " + clientIp);
    noteService.delete(id);

    HashMap<String, Object> map = new HashMap<>();
    map.put("success", true);
    map.put("message", "Successfully");

    return ResponseEntity.status(HttpStatus.OK).body(map);
  }
}
