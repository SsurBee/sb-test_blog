package sb.test.blog.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sb.test.blog.zzdomain.Member;
import sb.test.blog.zzrepository.MemoryMemberRepository;
import sb.test.blog.zzservice.MemberService;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

//    MemberService memberService = new MemberService();
//    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach  // @Test 하나가 끝날때마다 실행
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
//    void join() {
    void 회원가입() {
        // give(기반데이터 주고)
        Member member = new Member();
        member.setName("spring");

        // Do(동작시키고)
        // (단축키) 반환변수 자동 완성 : alt + enter
        Long savedId = memberService.join(member);

        // Then(동작시켰을때)
        Member findMember = memberService.findOne(savedId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    // 테스트는 정상플로우 테스트도 중요하지만,
    // 예외가 터지는 것도 중요하다.
    @Test
    void 중복_회원_예외() {
        // Given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // Do
        memberService.join(member1);
//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }
        // 상기코드 간략화 : (설명) 실행하면 예외가 터져야해!
//        assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        // 더 나아가....
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        // Then
    }

    @Test
    void findMemebers() {
    }

    @Test
    void findOne() {
    }
}