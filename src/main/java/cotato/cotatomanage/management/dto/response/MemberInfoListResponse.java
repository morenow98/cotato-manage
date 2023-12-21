package cotato.cotatomanage.management.dto.response;

import cotato.cotatomanage.management.vo.MemberInfoVo;
import lombok.Builder;

import java.util.List;

public class MemberInfoListResponse {

    List<MemberInfoVo> memberInfoVoList;

    @Builder
    private MemberInfoListResponse(List<MemberInfoVo> memberInfoVoList) {
        this.memberInfoVoList = memberInfoVoList;
    }

    public static MemberInfoListResponse from(List<MemberInfoVo> memberInfoVoList) {
        return MemberInfoListResponse.builder()
                .memberInfoVoList(memberInfoVoList)
                .build();
    }
}
