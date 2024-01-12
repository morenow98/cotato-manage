package cotato.cotatomanage.management.mapper;

import cotato.cotatomanage.management.domain.Member;
import cotato.cotatomanage.management.domain.Part;
import cotato.cotatomanage.management.dto.response.PartResponse;
import cotato.cotatomanage.management.helper.ManagementHelper;
import cotato.cotatomanage.management.repository.MemberRepository;
import cotato.cotatomanage.management.vo.MemberVo;
import cotato.cotatomanage.management.dto.response.MemberResponse;
import cotato.cotatomanage.management.vo.PartVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ManagementMapper {

    private final MemberRepository memberRepository;
    private final ManagementHelper managementHelper;

    public MemberResponse toMemberInfoListResponse(List<Member> memberList, int periodNow) {
        List<MemberVo> memberInfoList =
                memberList.stream()
                        .map(member -> MemberVo.of(member, periodNow))
                        .toList();
        managementHelper.sortAll(memberInfoList);
        return MemberResponse.from(memberInfoList);
    }

    public PartResponse toPartInfoListResponse(List<PartVo> partInfoVoList) {
        managementHelper.sortPart(partInfoVoList);
        return PartResponse.from(partInfoVoList);
    }

    public List<PartVo> toPartInfoVoList(int periodNow) {
        List<PartVo> partInfoVoList = new ArrayList<>();
        Part[] parts = Part.class.getEnumConstants();
        for (Part part : parts) {
            List<Member> memberList = memberRepository.findAllByPart(part);
            PartVo partInfoVo = PartVo.of(part, memberList, periodNow);
            partInfoVoList.add(partInfoVo);
        }
        return partInfoVoList;
    }
}