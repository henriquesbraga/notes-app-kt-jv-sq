package com.henriquesbraga.notesbackend.dtos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.henriquesbraga.notesbackend.entities.Note;

public class NoteDTO {

  private Long id;
  private String title;
  private String note;
  private String color;
  private Date date;
  

  public NoteDTO() {
  }

  public NoteDTO(Note obj){
    this.id = obj.getId();
    this.title = obj.getTitle();
    this.note = obj.getNote();
    this.color = obj.getColor();
    this.date = obj.getDate();
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getNote() {
    return this.note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public String getColor() {
    return this.color;
  }

  public void setColor(String color) {
    this.color = color;
  }
  
  @JsonFormat(pattern="dd/MM/yyyy HH:mm", timezone = "GMT-3")
  public Date getDate() {
    return this.date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  


}
