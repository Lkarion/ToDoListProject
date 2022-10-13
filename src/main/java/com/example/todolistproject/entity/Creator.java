package com.example.todolistproject.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "creator")

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Creator {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
}
