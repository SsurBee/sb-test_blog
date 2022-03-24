package sb.test.blog.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import sb.test.blog.model.RoleType;
import sb.test.blog.model.User;
import sb.test.blog.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Supplier;

@RestController
public class DummyControllerTest {

    @Autowired
    UserRepository userRepository;

    /**
     * 등록
     * @param user
     * @return
     */
    @PostMapping("/dummy/join")
    public String join(User user) {
        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "회원가입이 완료됐습니다.";
    }

    /**
     * 읽기
     * @param id
     * @return
     */
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id) {
//        User user = userRepository.findById(id).get();  // id가 반드시 있어서, null이 반환될 일이 절대 없을 경우

//        User user = userRepository.findById(id).orElseGet(new Supplier<User>() {    // id가 없어서, null 이 반환되는 것을 막고, 빈 객체 user를 반환하도록 강제
//            @Override                                                               // 즉, null을 반환하지 않으므로 시스템이 무리가 안감.
//            public User get() {
//                return new User();
//            }
//        });

//        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
//            @Override
//            public IllegalArgumentException get() {
//                return new IllegalArgumentException("해당 유저는 없습니다. ID : " + id);
//            }
//        });

        // 람다식
        User user = userRepository.findById(id).orElseThrow(()->{
           return new IllegalArgumentException("해당 유저는 없습니다. ID : " + id);
        });

        return user;
    }

    /**
     * 읽기 All
     * @return
     */
    @GetMapping("/dummy/users")
    public List<User> list() {
        return userRepository.findAll();
    }

    @GetMapping("/dummy/user")      // http://localhost/dummy/user?page=0 : 0부터 시작
    public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<User> pageingUser = userRepository.findAll(pageable);

        List<User> users = pageingUser.getContent();
        return users;
    }

    /**
     * UPDATE #1 : 일반적인 update
     */
//    @PutMapping("/dummy/user/{id}")
//    public User updateUser(@PathVariable int id, @RequestBody User requestUser) {   // @RequestBody : Json 데이터를 요청
//        System.out.println("id : "+ id);
//        System.out.println("password : "+ requestUser.getPassword());
//        System.out.println("email : "+ requestUser.getEmail());
//
//        User user = userRepository.findById(id).orElseThrow(()->{
//           return new IllegalArgumentException("수정에 실패했습니다.");
//        });
//        user.setPassword(requestUser.getPassword());
//        user.setEmail(requestUser.getEmail());
//        userRepository.save(user);
//        return user;
//    }

    /**
     * UPDATE #2 : 더티체킹 방식
     */
    @Transactional    // 더티 체킹 : 이걸 사용하면, save 함수 사용할 필요없음. 그런데... 고민해 보자. / 함수종료시에 자동으로 Commit 됨.
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User requestUser) {   // @RequestBody : Json 데이터를 요청
        System.out.println("id : "+ id);
        System.out.println("password : "+ requestUser.getPassword());
        System.out.println("email : "+ requestUser.getEmail());

        User user = userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("수정에 실패했습니다.");
        });
        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());
//        userRepository.save(user);
        return user;
    }

    /**
     * DELETE
     */
    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable int id) {

        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            return "삭제에 실패했습니다.";
        }

        return "정상적으로 삭제되었습니다. 삭제 ID : " + id;
    }
}
