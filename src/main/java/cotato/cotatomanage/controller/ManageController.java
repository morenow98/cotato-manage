package cotato.cotatomanage.controller;

import cotato.cotatomanage.domain.dto.JoinMemberRequest;
import cotato.cotatomanage.service.ManageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class ManageController {

    private final ManageService memberService;
    @PostMapping("/join")
    public ResponseEntity<?> createMember(@RequestBody JoinMemberRequest request){
        memberService.addMember(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/print/part/{period}")
    public ResponseEntity<?> printByPart(@PathVariable("period") int period){
        return ResponseEntity.ok().body(memberService.printByPart(period));
    }

    @GetMapping("/print/member/{period}")
    public ResponseEntity<?> printAllMembers(@PathVariable("period") int period) {
        return ResponseEntity.ok().body(memberService.printAllMembers(period));
    }

    @GetMapping("/print/{part}/member/{period}")
    public ResponseEntity<?> printAllMembersByPart(@PathVariable("part") String part, @PathVariable("period") int period) {
        return ResponseEntity.ok().body(memberService.printAllMembersByPart(period,part));
    }
}