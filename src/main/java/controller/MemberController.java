package controller;

import domain.MemberRepository;
import domain.Member;
import domain.MemberRequest;
import domain.Part;
import exception.MemberNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

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

    @GetMapping("/part")
    public ResponseEntity<List<Map<String, String>>> getParts() {
        List<Member> allMembers = memberRepository.findAll();

        Map<Part, List<Member>> membersByPart = allMembers.stream()
                .collect(Collectors.groupingBy(Member::getPart));

        List<Map<String, String>> partStats = new ArrayList<>();

        membersByPart.forEach((part, members) -> {
            if (!members.isEmpty()) {
                int totalAbility = members.stream().mapToInt(Member::getAbility).sum();
                int averageAbility = totalAbility / members.size();
                int count = members.size();

                Map<String, String> partStat = new HashMap<>();
                partStat.put("part", part.name());
                partStat.put("ability", String.valueOf(averageAbility));
                partStat.put("count", String.valueOf(count));

                partStats.add(partStat);
            }
        });

        partStats.sort((stat1, stat2) -> {
            int abilityComparison = Integer.compare(Integer.parseInt(stat2.get("ability")), Integer.parseInt(stat1.get("ability")));
            if (abilityComparison != 0) {
                return abilityComparison;
            } else {
                int countComparison = Integer.compare(Integer.parseInt(stat1.get("count")), Integer.parseInt(stat2.get("count")));
                if (countComparison != 0) {
                    return countComparison;
                } else {
                    List<String> partOrder = Arrays.asList("Designer", "PM", "BackEnd", "FrontEnd");
                    return partOrder.indexOf(stat1.get("part")) - partOrder.indexOf(stat2.get("part"));
                }
            }
        });

        return new ResponseEntity<>(partStats, HttpStatus.OK);
    }
}
