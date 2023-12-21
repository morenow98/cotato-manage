package cotato.cotatomanage.management;

import cotato.cotatomanage.management.domain.Part;
import cotato.cotatomanage.management.dto.request.RegisterRequest;
import cotato.cotatomanage.management.dto.response.MemberInfoListResponse;
import cotato.cotatomanage.management.dto.response.PartInfoListResponse;
import cotato.cotatomanage.management.service.ManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/management")
public class ManagementController {

    private final ManagementService managementService;

    @PostMapping
    public void registerMember(RegisterRequest registerRequest) {
        log.info("멤버 등록");
        managementService.registerMember(registerRequest);
    }

    @GetMapping
    public MemberInfoListResponse getPartAbility(
            @RequestParam Part part,
            @RequestParam int periodNow
    ) {
        log.info("특정 파트 실력");
        return managementService.getPartAbility(part, periodNow);
    }

    @GetMapping
    public PartInfoListResponse getPartStatAbility(@RequestParam int periodNow) {
        log.info("파트별 실력 통계");
        return managementService.getPartStatsAbility(periodNow);
    }

    @GetMapping
    public MemberInfoListResponse getAllAbility(@RequestParam int periodNow) {
        log.info("모든 부원 실력");
        return managementService.getAllAbility(periodNow);
    }
}
