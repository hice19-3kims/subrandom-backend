package kkkw.subrandom.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import kkkw.subrandom.domain.Member;
import kkkw.subrandom.dto.MemberDto;
import kkkw.subrandom.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<Member> signup(
            @Valid @RequestBody MemberDto memberDto
    ) {
        return ResponseEntity.ok(memberService.signup(memberDto));
    }

    @GetMapping("/me")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Member> getMyUserInfo(HttpServletRequest request) {
        return ResponseEntity.ok(memberService.getMyMemberWithAuthorities());
    }

    @GetMapping("/{email}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Member> getUserInfo(@PathVariable String email) {
        return ResponseEntity.ok(memberService.getMemberWithAuthorities(email));
    }

}
