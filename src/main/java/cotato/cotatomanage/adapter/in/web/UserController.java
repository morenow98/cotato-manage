package cotato.cotatomanage.adapter.in.web;

import cotato.cotatomanage.adapter.in.request.UserRequest;
import cotato.cotatomanage.adapter.in.response.PartResponse;
import cotato.cotatomanage.adapter.in.response.UserResponse;
import cotato.cotatomanage.application.port.service.UserService;
import cotato.cotatomanage.domain.PartType;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public void registerUser(
            @RequestBody @Valid UserRequest request
    ) {
        userService.userRegister(request.toCommand());
    }

    @GetMapping("/users")
    public List<UserResponse> getUserInfo(@RequestParam int period) {
        return userService.searchUsersInfo(period).stream()
                .map(UserResponse::from).toList();
    }

    @GetMapping("/parts")
    public List<PartResponse> getPartInfo(@RequestParam int period) {
        return userService.searchPartInfo(period).stream()
                .map(PartResponse::from).toList();
    }

    @GetMapping("/parts/{part-type}")
    public List<UserResponse> getUserOfPartInfo(@RequestParam int period, @PathVariable("part-type") PartType partType) {
        return userService.searchUsersOfPartInfo(period, partType).stream()
                .map(UserResponse::from).toList();
    }
}
