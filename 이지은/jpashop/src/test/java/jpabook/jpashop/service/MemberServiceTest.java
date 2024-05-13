package jpabook.jpashop.service;
import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.exception.DuplicateMemberException;
import jpabook.jpashop.exception.MemberErrorCode;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception {
        //Given
        Member member = new Member();
        member.setName("kim");
        //When
        Long saveId = memberService.join(member);
        //Then
        assertEquals(member, memberRepository.findOne(saveId));
    }

    //@Test(expected = IllegalStateException.class)
    @Test
    public void 중복_회원_예외() throws Exception{
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);
        //memberService.join(member2); // 똑같은 이름을 넣었기 때문에 여기서 예외가 발생해야 한다.
        DuplicateMemberException exception = assertThrows(DuplicateMemberException.class, () -> {
            memberService.join(member2);
        });
        assertThat(MemberErrorCode.DUPLICATE_MEMBER).isEqualTo(exception.getErrorCode());

        //then
        //fail("예외가 발생해야 한다.");

    }

}