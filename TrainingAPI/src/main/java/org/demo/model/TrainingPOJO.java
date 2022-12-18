package org.demo.model;


import jakarta.persistence.*;

@Entity
@Table(name = "training_sessions")
public class TrainingPOJO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "type")
    private String type;
    @Column(name = "date")
    private String date;


    public TrainingPOJO(int id, String type, String date) {
        this.id = id;
        this.type = type;
        this.date = date;
    }
}
