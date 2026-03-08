package Domain.Spring_Shopping_Mall.controller;

import Domain.Spring_Shopping_Mall.domain.Member;
import Domain.Spring_Shopping_Mall.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;


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

        Optional<Member> findMember = memberService.findByEmail(form.getEmail());

        if (findMember.isPresent()) {
            model.addAttribute("errorMessage", "이미 존재하는 이메일입니다.");
            return "join";
        }

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
    public String loginPage(){
        return "login";
    }


    @PostMapping("/login")
    public String login(MemberForm form, Model model) {

        Optional<Member> storeMember = memberService.findByEmail(form.getEmail());

        if (storeMember.isEmpty()) {
            model.addAttribute("errorMessage", "존재하지 않는 이메일입니다.");
            return "login";
        }

        Member member = storeMember.get();

        if (!member.getPassword().equals(form.getPassword())) {
            model.addAttribute("errorMessage", "비밀번호가 일치하지 않습니다.");
            return "login";
        }

        return "home";
    }

}
