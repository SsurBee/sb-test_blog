package sb.test.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
// @DynamicInsert : Insert 시에 null 인 필드를 제외
public class User {
    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // 프로젝트에 연결된 DB의 넘버링 전략을 따라간다.
    private int id;     // auto_increment

    @Column(nullable = false, length = 30)
    private String username;

    @Column(nullable = false, length = 100)     // 해쉬(비밀번호 암호화)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    // @ColumnDefault("'user'")    // 기본값 String은 작은 따움표 사용 필요
    @Enumerated(EnumType.STRING)
    private RoleType role;        // Enum을 쓰면 좋다. (USER, ADMIN)

    @CreationTimestamp
    private Timestamp createDate;
}
