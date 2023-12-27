package cotato.cotatomanage.controller;

import cotato.cotatomanage.dto.request.MemberRegistrationRequest;
import cotato.cotatomanage.dto.response.MemberResponse;
import cotato.cotatomanage.dto.response.PartResponse;
import cotato.cotatomanage.service.ManageService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ManageController {

    private final ManageService manageService;

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerMember(@RequestBody MemberRegistrationRequest memberRegistrationRequest) {
        manageService.registerMember(memberRegistrationRequest);
    }

    @GetMapping("/parts")
    @ResponseStatus(HttpStatus.OK)
    public List<PartResponse> getParts(@RequestParam(name = "period") int currentPeriod) {
        return manageService.getParts(currentPeriod);
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<MemberResponse> getMembers(@RequestParam(name = "period") int currentPeriod,
                                           @RequestParam(required = false) Optional<String> part) {
        return manageService.getMembers(currentPeriod, part);
    }
}
