package cotato.cotatomanage.service;

import cotato.cotatomanage.domain.Member;
import cotato.cotatomanage.dto.request.MemberRegistrationRequest;
import cotato.cotatomanage.repository.ManageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManageService {

    private final ManageRepository manageRepository;

    public void registerMember(MemberRegistrationRequest memberRegistrationRequest) {
        Member member = memberRegistrationRequest.toEntity();
        manageRepository.save(member);
    }
}
