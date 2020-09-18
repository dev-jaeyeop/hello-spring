package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

//    @BeforeEach
//    void beforeEach() {
//        memberRepository = new MemoryMemberRepository();
//        memberService = new MemberService(memberRepository);
//    }

//    @AfterEach
//    void afterEach() {
//        memberRepository.clearStore();
//    }

    @Test
    void join() {
        // given
        Member member = new Member();
        member.setName("spring");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberRepository.findById(saveId).get();
        assertEquals(member.getName(), findMember.getName());
    }

    @Test
    public void duplicateMemberException() {
        // given
        Member member = new Member();
        member.setName("spring");

        Member member1 = new Member();
        member1.setName("spring1");

        // when
        memberService.join(member);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

//    @Test
//    void findMembers() {
//    }

//    @Test
//    void findOne() {
//    }
}