package cotato.cotatomanage.management.mapper;

import cotato.cotatomanage.management.domain.Member;
import cotato.cotatomanage.management.domain.Part;
import cotato.cotatomanage.management.dto.response.PartInfoListResponse;
import cotato.cotatomanage.management.helper.ManagementHelper;
import cotato.cotatomanage.management.repository.MemberRepository;
import cotato.cotatomanage.management.vo.MemberInfoVo;
import cotato.cotatomanage.management.dto.response.MemberInfoListResponse;
import cotato.cotatomanage.management.vo.PartInfoVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ManagementMapper {

    private final MemberRepository memberRepository;
    private final ManagementHelper managementHelper;

    public MemberInfoListResponse toMemberInfoListResponse(List<Member> memberList, int periodNow) {
        List<MemberInfoVo> memberInfoList =
                memberList.stream()
                        .map(member -> MemberInfoVo.of(member, periodNow))
                        .toList();
        managementHelper.sortAll(memberInfoList);
        return MemberInfoListResponse.from(memberInfoList);
    }

    public PartInfoListResponse toPartInfoListResponse(List<PartInfoVo> partInfoVoList) {
        managementHelper.sortPart(partInfoVoList);
        return PartInfoListResponse.from(partInfoVoList);
    }

    public List<PartInfoVo> toPartInfoVoList(int periodNow) {
        List<PartInfoVo> partInfoVoList = new ArrayList<>();
        Part[] parts = Part.class.getEnumConstants();
        for (Part part : parts) {
            List<Member> memberList = memberRepository.findAllByPart(part);
            PartInfoVo partInfoVo = PartInfoVo.of(part, memberList, periodNow);
            partInfoVoList.add(partInfoVo);
        }
        return partInfoVoList;
    }
}
