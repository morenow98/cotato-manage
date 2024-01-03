package controller;

import domain.MemberRepository;
import domain.Member;
import domain.MemberRequest;
import exception.MemberNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 전체 조회
    @GetMapping("/members")
    public List<Member> retrieveAllMembers(){
        return memberRepository.findAll();
    }

    // id로 개인별 조회
    @GetMapping("/members/{id}")
    public Member retrieveMember(@PathVariable(name = "id") int memberId) {
        Member member = memberRepository.findOne(memberId);

        if (member == null){
            throw new MemberNotFoundException("id[%s] not found");
        }

        return memberRepository.findOne(memberId);
    }

    // 멤버 등록
    @PostMapping("/members")
    public ResponseEntity<Object> createMember(@Valid @RequestBody MemberRequest memberRequest){
        Member member = Member.builder()
                .name(memberRequest.getName())
                .period(memberRequest.getPeriod())
                .age(memberRequest.getAge())
                .part(memberRequest.getPart())
                .build();

        int calculatedAbility = member.setAbility();
        member.setAbility(calculatedAbility);

        Member savedMember = memberRepository.save(member);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedMember.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

}
