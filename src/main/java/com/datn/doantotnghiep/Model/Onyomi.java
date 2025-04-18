package com.datn.doantotnghiep.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "onyomi")
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
public class Onyomi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "onyomi_id")
    private Integer onyomiId;

    @ManyToOne
    @JoinColumn(name = "kanji_id", nullable = false)
    private Kanji kanji;

    @Column(name = "am_on", length = 50)
    private String amOn;
}
