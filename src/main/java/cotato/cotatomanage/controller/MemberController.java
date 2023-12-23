package cotato.cotatomanage.controller;

import cotato.cotatomanage.domain.dto.JoinMemberRequest;
import cotato.cotatomanage.domain.dto.MemberResponse;
import cotato.cotatomanage.service.MemberService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<?> createMember(@RequestBody JoinMemberRequest request) {
        log.info("회원 가입 요청 : {}", request.getName());
        memberService.createMember(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = "/{period}", params = "type=all-member")
    public ResponseEntity<?> getAllMember(@PathVariable("period") int period) {
        log.info("모든 동아리원 출력 컨트롤러");
        List<MemberResponse> allMember = memberService.getAllMember(period);
        return ResponseEntity.ok().body(allMember);
    }

    @GetMapping(value = "/{period}", params = "type=each-part")
    public ResponseEntity<?> getEachPart(@RequestParam(name = "part") String part,
                                         @PathVariable(name = "period") int period) {
        log.info("특정 파트 출력 컨트롤러");
        List<MemberResponse> partMembers = memberService.getPartMembers(part, period);
        return ResponseEntity.ok(partMembers);
    }

    @GetMapping("/part/{period}")
    public ResponseEntity<?> getAllPart(@PathVariable("period") int period) {
        log.info("{} 기수 모든 파트 출력 컨트롤러", period);
        List<PartResponse> allPart = memberService.getAllPart(period);
        return ResponseEntity.ok(allPart);
    }
}
