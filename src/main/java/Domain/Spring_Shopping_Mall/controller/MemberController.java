package Domain.Spring_Shopping_Mall.controller;

import Domain.Spring_Shopping_Mall.domain.Member;
import Domain.Spring_Shopping_Mall.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("/join")
    public String joinPage(){
        return "join";
    }

    @PostMapping("/join")
    public String join(MemberForm form, Model model) {

        if (!form.getPassword().equals(form.getPasswordCheck())) {
            model.addAttribute("errorMessage", "비밀번호가 일치하지 않습니다.");
            return "join";
        }

        Member member = new Member();
        member.setName(form.getName());
        member.setEmail(form.getEmail());
        member.setPassword(form.getPassword());

        memberService.join(member);


        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

}
