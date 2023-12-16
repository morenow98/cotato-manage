package cotato.cotatomanage.controller;

import cotato.cotatomanage.dto.request.MemberRegistrationDto;
import cotato.cotatomanage.service.ManageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ManageController {

    private final ManageService manageService;

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerMember(@RequestBody MemberRegistrationDto memberRegistrationDto) {
        manageService.registerMember(memberRegistrationDto);
    }
}
