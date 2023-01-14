package org.demo.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "training_sessions")
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "type")
    private String type;
    @Column(name = "date")
    private String date;


    public Training(String type, String date) {
        this.type = type;
        this.date = date;
    }
    public Training(){

    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }
}
