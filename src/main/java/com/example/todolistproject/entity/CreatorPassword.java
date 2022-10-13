package com.example.todolistproject.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "creator_password")

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreatorPassword {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "salt")
    private String salt;

    @Column(name = "password_hash")
    private String passwordHash;

    @OneToOne
    @JoinColumn(name = "creator_id", referencedColumnName = "id", unique = true, nullable = false)
    private Creator creator;
}
