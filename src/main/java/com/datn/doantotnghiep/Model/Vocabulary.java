package com.datn.doantotnghiep.Model;

import com.datn.datnai.Custom.WordType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "vocabulary")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vocabulary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 255, nullable = false)
    private String word;

    @Column(length = 255, nullable = false)
    private String reading;

    @Column(columnDefinition = "TEXT")
    private String meaning;

    @Enumerated(EnumType.STRING)
    @Column(name = "word_type", nullable = false)
    private WordType wordType;
}
