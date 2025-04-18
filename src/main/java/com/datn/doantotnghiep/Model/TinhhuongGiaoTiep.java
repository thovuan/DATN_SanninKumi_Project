package com.datn.doantotnghiep.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "ddulieuhocmay") // 🔁 Đổi thành tên bảng thực tế trong MySQL
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TinhhuongGiaoTiep {
    @Id
    @Column(name = "iddl", nullable = false, length = 50)
    private String iddl;

    @Column(name = "user_sentence", length = 255)
    private String userSentence;

    @Column(name = "computer_centence", length = 255)
    private String computerCentence;

    @Column(name = "url", length = 255)
    private String url;

    @Column(name = "idthgt", length = 50)
    private String idthgt;
}
