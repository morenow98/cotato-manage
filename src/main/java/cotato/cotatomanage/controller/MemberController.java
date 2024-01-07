package cotato.cotatomanage.controller;

import cotato.cotatomanage.domain.dto.JoinMemberRequest;
import cotato.cotatomanage.domain.dto.PartResponse;
import cotato.cotatomanage.service.MemoryMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemoryMemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<?> joinMember(@RequestBody JoinMemberRequest request){
        memberService.joinMember(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/part/{period}")
    public ResponseEntity<?> getAllPartByPeriod(@PathVariable("period") int period){
        List<PartResponse> allPartByPeriod = memberService.getAllPartByPeriod(period);
        return ResponseEntity.ok(allPartByPeriod);
    }
}
