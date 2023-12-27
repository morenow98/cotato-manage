package cotato.cotatomanage.api;

import org.springframework.boot.autoconfigure.integration.IntegrationProperties;
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
public class MemberApiController {
    private final ManageService memberService;
    @PostMapping("/add")
    public ResponseEntity<?> addMember(@RequestBody AddMemberRequest request){
        memberService.addMember(request);
        return ResponseEntity.ok().build();
    }

}
