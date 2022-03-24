package sb.test.blog.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import sb.test.blog.zzdomain.Member;
import sb.test.blog.zzrepository.MemoryMemberRepository;

import java.util.List;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();

    @AfterEach  // @Test 하나가 끝날때마다 실행
    public void afterEach() {
        memoryMemberRepository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        memoryMemberRepository.save(member);

        Member result = memoryMemberRepository.findById(member.getId()).get();

        Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("Spring1");
        memoryMemberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        memoryMemberRepository.save(member2);

        Member result = memoryMemberRepository.findByName("Spring1").get();
        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findByAll() {
        Member member1 = new Member();
        member1.setName("Spring1");
        memoryMemberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        memoryMemberRepository.save(member2);

        List<Member> results = memoryMemberRepository.findAll();
        Assertions.assertThat(results.size()).isEqualTo(2);
    }
}
