package com.example.todolistproject.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "creator_session")

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreatorSession {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "session_id")
    private String sessionId;

    @Column(name = "expiration_time")
    private LocalDateTime expirationTime;

    @OneToOne
    @JoinColumn(name = "creator_id", referencedColumnName = "id")
    private Creator creator;

    public void setExpirationTime(Long expirationPeriod) {
        this.expirationTime = LocalDateTime.now().plusMinutes(expirationPeriod);
    }
}
