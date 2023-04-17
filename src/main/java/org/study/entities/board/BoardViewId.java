package org.study.entities.board;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
public class BoardViewId implements Serializable {
    private Long idBoardData; // 게시글 번호
    private String uid; // Unique ID

    @Override
    public int hashCode() {
        return Objects.hash(idBoardData, uid);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BoardViewId) {
            BoardViewId viewId = (BoardViewId) obj;
            if (idBoardData == viewId.idBoardData && uid.equals(viewId.uid)) {
                return true;
            }
        }

        return false;
    }
}
