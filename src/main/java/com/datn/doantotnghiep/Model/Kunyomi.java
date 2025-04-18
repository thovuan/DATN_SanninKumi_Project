package com.datn.doantotnghiep.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "kunyomi")
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
public class Kunyomi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kunyomi_id")
    private Integer kunyomiId;

    @ManyToOne
    @JoinColumn(name = "kanji_id", nullable = false)
    private Kanji kanji;

    @Column(name = "am_kun", length = 50)
    private String amKun;
}
