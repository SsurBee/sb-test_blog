package sb.test.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // auto_increment
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob    // 대용량 데이터
    private String content;     // 섬머노트 라이브러리 사용, <html> 태그가 섞여서 디자인 됨

    @ColumnDefault("0")
    private int count;          // 조회수

    // @ManyToOne은 기본 defalut 전략이 fetch = FetchType.EAGER(무조건 들고와) 임, 따라서, @ManyToOne만 적어도 됨
    @ManyToOne(fetch = FetchType.EAGER)  // Many=Board, One=User
    @JoinColumn(name = "userId")
    private User user;          // DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다.

    // mappedBy 연관관계의 주인이 아니다.(난 FK가 아니다.) DB에 컬럼을 만들지 마세요.
    // (ref) "fetch = FetchType.LAZY" : (전략) 필요할 때 들고와 (ex. "펼치기" 버튼)
    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER)
    // @JoinColumn(name = "replyId") : 불가
    private List<Reply> reply;

    @CreationTimestamp
    private Timestamp createDate;
}
