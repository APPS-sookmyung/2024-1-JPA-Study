package jpabook.jpashop.service;

import jpabook.jpashop.DTO.MemberDto;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    /**
     *
     회원가입
     */
    @Transactional
    public Long join(MemberDto memberDto) {
        Member member = dtoToEntity(memberDto);
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
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

    private Member dtoToEntity(MemberDto memberDto) {
        Address address = new Address(memberDto.getCity(), memberDto.getStreet(), memberDto.getZipcode());
        Member member = new Member();
        member.setName(memberDto.getName());
        member.setAddress(address);
        return member;
    }
}
