package sb.test.blog.zzservice;

import org.springframework.stereotype.Service;
import sb.test.blog.zzdomain.Member;
import sb.test.blog.zzrepository.MemberRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    // (단축키) Junit 테스트 간편 생성 : ctrl + shift + t

    // interface와 실구현 class 혼용
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {
        // 같은 이름의 멤버는 등록 X
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });

        // 상기 코드 축약
//        memberRepository.findByName(member.getName())
//                .ifPresent(m -> {
//                    throw new IllegalStateException("이미 존재하는 회원입니다.");
//                });

        // 상기 코드를 별도의 메스드로 Extract
        // (단축키) CTRL + alt + shift + t > method 검색
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMemebers() {
        return memberRepository.findAll();
    }

    /**
     * 개별 회원 조회
     */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
