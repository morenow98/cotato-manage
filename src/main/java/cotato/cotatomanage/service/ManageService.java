package cotato.cotatomanage.service;

import cotato.cotatomanage.domain.Member;
import cotato.cotatomanage.dto.request.MemberRegistrationDto;
import cotato.cotatomanage.repository.ManageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManageService {

    private final ManageRepository manageRepository;

    public void registerMember(MemberRegistrationDto memberRegistrationDto) {
        Member member = memberRegistrationDto.toEntity();
        manageRepository.save(member);
    }
}
