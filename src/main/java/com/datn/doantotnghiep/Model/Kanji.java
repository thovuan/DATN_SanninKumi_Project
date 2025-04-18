package com.datn.doantotnghiep.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Entity
@Table(name = "kanji")
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
public class Kanji {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kanji_id")
    private Integer kanjiId;

    @Column(name = "kanji", nullable = false, unique = true, length = 10)
    private String kanji;

    @Column(name = "han_viet", nullable = false, length = 50)
    private String hanViet;

    @OneToMany(mappedBy = "kanji", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Onyomi> onyomiList;

    @OneToMany(mappedBy = "kanji", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Kunyomi> kunyomiList;

    @Column(name = "imi")
    private String imi;

    // Getters and Setters
}
