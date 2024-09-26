package cotato.cotatomanage.management.service;

import cotato.cotatomanage.management.domain.Member;
import cotato.cotatomanage.management.domain.Part;
import cotato.cotatomanage.management.dto.request.RegisterRequest;
import cotato.cotatomanage.management.dto.response.MemberInfoListResponse;
import cotato.cotatomanage.management.dto.response.PartInfoListResponse;
import cotato.cotatomanage.management.mapper.ManagementMapper;
import cotato.cotatomanage.management.repository.MemberRepository;
import cotato.cotatomanage.management.vo.PartInfoVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ManagementService {

    private final MemberRepository memberRepository;
    private final ManagementMapper managementMapper;

    @Transactional
    public void registerMember(RegisterRequest registerRequest) {
        Member member = Member.of(registerRequest);
        memberRepository.save(member);
    }

    @Transactional
    public MemberInfoListResponse getPartAbility(Part part, int periodNow) {
        List<Member> memberList = memberRepository.findAllByPart(part);
        return managementMapper.toMemberInfoListResponse(memberList, periodNow);
    }

    @Transactional
    public PartInfoListResponse getPartStatsAbility(int periodNow) {
        List<PartInfoVo> partInfoVoList = managementMapper.toPartInfoVoList(periodNow);
        return managementMapper.toPartInfoListResponse(partInfoVoList);
    }

    @Transactional
    public MemberInfoListResponse getAllAbility(int periodNow) {
        List<Member> memberList = memberRepository.findAll();
        return managementMapper.toMemberInfoListResponse(memberList, periodNow);
    }

}
