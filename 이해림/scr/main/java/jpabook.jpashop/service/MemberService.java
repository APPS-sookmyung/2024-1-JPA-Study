package jpabook.jpashop.service;

import jpabook.jpashop.DTO.MemberDto;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Member.MemberBuilder;
import jpabook.jpashop.exception.DuplicateMemberException;
import jpabook.jpashop.exception.MemberErrorCode;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     */
    /*
     * @Transactional
     * public Long join(Member member) {
     * 
     * //validateDuplicateMember(member); //중복 회원 검증
     * memberRepository.save(member);
     * return member.getId();
     * }
     */

    @Transactional
    public Long join(MemberDto memberDto) { // MemberDto를 받아와서 Member엔티티 만들기
        Member member = Member.builder()
                .name(memberDto.getName()) // 이름과
                .address(memberDto.getAddress()) // 주소를 저장함 엔티티에
                .build();
        memberRepository.save(member); // 영속화 member엔티티가 DB에 저장
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

}
