package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemberRepositoryTest {

    //sigleton이라 new를 쓸 필요가 없음.
    //spring을 쓰면 spring이 sigleton자체를 보장해줘서 쓸 필요가 없음.
    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach(){
        memberRepository.clearStore(); //테스트 순서가 달라질 수 있기때문에 꼭 해줘야함
    }

    @Test
    void save(){
        //given (이런거 주어졌을 떄)
        Member member = new Member("hello", 20);

        //when(이런거 실행했을 때)
        Member savedMember = memberRepository.save(member);

        //then(결과는 이래야함)
        Member findMember = memberRepository.findById(savedMember.getId());
        Assertions.assertThat(findMember).isEqualTo(savedMember);
    }

    @Test
    void findAll(){
        //given
        Member member1 = new Member("member1", 20);
        Member member2 = new Member("member2", 30);

        memberRepository.save(member1);
        memberRepository.save(member2);

        //when
        List<Member> result = memberRepository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(member1, member2);
    }

}
