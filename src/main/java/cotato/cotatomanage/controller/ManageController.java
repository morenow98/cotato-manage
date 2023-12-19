package cotato.cotatomanage.controller;

import cotato.cotatomanage.domain.dto.AddMemberRequest;
import cotato.cotatomanage.service.ManageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ManageController {

    private final ManageService memberService;
    @PostMapping("/add")
    public ResponseEntity<?> addMember(@RequestBody AddMemberRequest request){
        memberService.addMember(request);
        return ResponseEntity.ok().build();
    }


}
