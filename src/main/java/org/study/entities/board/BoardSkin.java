package org.study.entities.board;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.study.commons.constants.board.SkinType;
import org.study.entities.BaseEntity;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardSkin extends BaseEntity {
    @Id
    private String id; // 스킨 아이디

    @Column(length=60, nullable = false)
    private String skinNm; // 스킨명


    @Enumerated(EnumType.STRING)
    @Column(length=30, nullable = false)
    private SkinType skinType = SkinType.DEFAULT;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="bId")
    private Board board;
}
