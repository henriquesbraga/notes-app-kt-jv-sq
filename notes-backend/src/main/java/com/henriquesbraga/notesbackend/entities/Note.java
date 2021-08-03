package com.henriquesbraga.notesbackend.entities;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "note")
public class Note {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "note_seq")
  @SequenceGenerator(name = "note_seq", sequenceName = "note_seq", allocationSize = 1, initialValue = 1)
  private Long id;

  @Column(length = 50)
  private String title;
  
  @Lob
  private String note;
  private String color;

  @JsonFormat(pattern="dd/MM/yyyy HH:mm")
  private Date date;

  public Note() {
  }

  public Note(Long id, String title, String note, String color, Date date) {
    this.id = id;
    this.title = title;
    this.note = note;
    this.color = color;
    this.date = date;
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

  public Date getDate() {
    return this.date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Note)) {
      return false;
    }
    Note note = (Note) o;
    return Objects.equals(id, note.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  @Override
  public String toString() {
    return "{" + " id='" + getId() + "'" + ", title='" + getTitle() + "'" + ", note='" + getNote() + "'" + ", color='"
    + getColor() + "'" + ", date='" + getDate() + "'" + "}";
  }

}
