package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.exception.DuplicateMemberException;
import jpabook.jpashop.exception.MemberErrorCode;
import jpabook.jpashop.repository.MemberRepository;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.DuplicateFormatFlagsException;

import static com.jayway.jsonpath.internal.path.PathCompiler.fail;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager em;

    @Test
    @Rollback(false)
    public void 회원가입() throws Exception {
        // given
        Member member = new Member();
        member.setName("kim");

        // when
        Long savedId = memberService.join(member);

        // then
        em.flush();
        assertEquals(member, memberRepository.findOne(savedId));
    }

    /*
     * @Test
     * public void 중복_회원_예외() throws Exception {
     * //given
     * Member member1 = new Member();
     * member1.setName("kim");
     * 
     * Member member2 = new Member();
     * member2.setName("kim");
     * 
     * //when
     * memberService.join(member1);
     * try {
     * memberService.join(member2); // 예외가 발생
     * } catch (IllegalStateException e) {
     * return;
     * }
     * 
     * //then
     * fail("예외가 발생해야 한다.");
     */
    @Test
    public void 중복_회원_예외() throws Exception {
        // given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        // when
        memberService.join(member1);
        try {
            memberService.join(member2); // 예외가 발생
        } catch (DuplicateMemberException e) {
            assertThat(e).isInstanceOf(DuplicateMemberException.class);
            Object MemberError;
            assertThat(e.getErrorCode()).isEqualTo(MemberErrorCode.DUPLICATE_MEMBER);
        }
        // then
    }
}
