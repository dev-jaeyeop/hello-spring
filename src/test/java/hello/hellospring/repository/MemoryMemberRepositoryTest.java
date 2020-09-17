package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    void save() {
        // given
        Member member = new Member();
        member.setName("spring");

        // when
        repository.save(member);

        // then
        Member result = repository.findByName(member.getName()).get();
        assertThat(member).isEqualTo(result);
    }

    @Test
    void findByName() {
        // given
        Member member = new Member();
        member.setName("spring1");
        repository.save(member);

        Member member1 = new Member();
        member1.setName("spring2");
        repository.save(member1);

        // when
        Member result = repository.findByName("spring2").get();

        // then
        assertThat(member1).isEqualTo(result);
    }

    @Test
    void findAll() {
        // given
        Member member = new Member();
        member.setName("spring1");
        repository.save(member);

        Member member1 = new Member();
        member1.setName("spring2");
        repository.save(member1);

        // when
        List<Member> result = repository.findAll();

        // then
        assertThat(result.size()).isEqualTo(2);
    }
}