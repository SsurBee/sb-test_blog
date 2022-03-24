package sb.test.blog.zzcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import sb.test.blog.zzservice.MemberService;

@Controller
public class MemberController {
//    @Autowired private MemberService memberService;         // DI의 3가지 방법중에 필드 주입 (필드 주입 별로 안좋음)

//    private MemberService memberService;
//    @Autowired
//    public void setMemberService(MemberService memberService) {     // DI의 3가지 방법중에 Setter 주입 (퍼블릭 하게 노출이 됨)
//        this.memberService = memberService;
//    }

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {      // DI의 3가지 방법중에 생성자 주입
        this.memberService = memberService;
    }

}
