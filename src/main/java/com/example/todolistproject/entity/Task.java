package com.example.todolistproject.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "task")

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Task {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "is_done")
    private Boolean isDone;

    @ManyToOne
    @JoinColumn(name = "creator_id", referencedColumnName = "id")
    private Creator creator;
}
