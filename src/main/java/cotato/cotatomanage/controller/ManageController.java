package cotato.cotatomanage.controller;

import cotato.cotatomanage.domain.Member;
import cotato.cotatomanage.domain.dto.AddMemberRequest;
import cotato.cotatomanage.domain.dto.OrderByPartResponse;
import cotato.cotatomanage.service.ManageService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ManageController {

    private final ManageService memberService;
    @PostMapping("/add")
    public ResponseEntity<?> addMember(@RequestBody AddMemberRequest request){
        memberService.addMember(request);
        return ResponseEntity.ok().build();
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
