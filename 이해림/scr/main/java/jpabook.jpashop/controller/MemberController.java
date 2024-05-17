package jpabook.jpashop.controller;

import jpabook.jpashop.DTO.MemberDto;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@Valid @ModelAttribute MemberDto memberDto, BindingResult result) { // Form사용하지 않고 MemberDto를
                                                                                             // 통해 정보 받아오기
        Address address = memberDto.getAddress();

        if (result.hasErrors()) {
            return "/members/createMemberForm";
        }

        // Member member = new Member();
        // member.setName(form.getName());
        // member.setAddress(address);

        memberService.join(memberDto);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
