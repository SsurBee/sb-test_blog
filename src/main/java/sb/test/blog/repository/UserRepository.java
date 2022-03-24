package sb.test.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sb.test.blog.model.User;

// DAO 역할
// 자동으로 IoC를 통해서, Bean으로 등록된다. : @Repository 생략 가능
public interface UserRepository extends JpaRepository<User, Integer> {
}
