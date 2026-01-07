package com.playlife.backend.modules.venue.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "venues")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Venue {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "venue", cascade = CascadeType.ALL)
    private List<Court> courts;
}
