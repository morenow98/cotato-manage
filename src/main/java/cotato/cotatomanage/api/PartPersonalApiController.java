package cotato.cotatomanage.api;

import org.springframework.web.bind.annotation.RestController;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import cotato.cotatomanage.entity.Member;
import cotato.cotatomanage.entity.Part;
import cotato.cotatomanage.service.ManageService;
import cotato.cotatomanage.dto.AddMemberRequest;
import cotato.cotatomanage.dto.OrderByPartResponse;
@RequiredArgsConstructor
@RestController
public class PartPersonalApiController {
    private final ManageService memberService;
    @GetMapping("/print/member/{period}")
    public ResponseEntity<?> printAllMembers(@PathVariable("period") int period) {
        return ResponseEntity.ok().body(memberService.printAllMembers(period));
    }
}
