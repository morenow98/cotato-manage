package cotato.cotatomanage.controller;

import cotato.cotatomanage.domain.Part;
import cotato.cotatomanage.domain.dto.JoinMemberRequest;
import cotato.cotatomanage.domain.dto.MemberResponse;
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

    /**
     * 회원 등록
     */
    @PostMapping("/join")
    public ResponseEntity<?> joinMember(@RequestBody JoinMemberRequest request){
        memberService.joinMember(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 전체 멤버 조회
     */
    @GetMapping("/part/{period}/all")
    public ResponseEntity<?> getAllMember(@PathVariable("period") int period){
        List<MemberResponse> allMember = memberService.getAllMember(period);
        return ResponseEntity.ok(allMember);
    }
    @GetMapping("/part/{period}/all/{partKey}")
    public ResponseEntity<?> getAllMember(@PathVariable int period, @PathVariable String partKey){
        List<MemberResponse> allPartMember = memberService.getAllPartMember(Part.getPart(partKey), period);
        return ResponseEntity.ok(allPartMember);
    }

    /**
     * 기수별 전체 멤버 조회
     */
    @GetMapping("/part/{period}")
    public ResponseEntity<?> getAllPartByPeriod(@PathVariable("period") int period){
        List<PartResponse> allPartByPeriod = memberService.getAllPartByPeriod(period);
        return ResponseEntity.ok(allPartByPeriod);
    }
}
