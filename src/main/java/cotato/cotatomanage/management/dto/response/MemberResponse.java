package cotato.cotatomanage.management.dto.response;

import cotato.cotatomanage.management.vo.MemberVo;
import lombok.Builder;

import java.util.List;

public class MemberResponse {

    List<MemberVo> memberInfoVoList;

    @Builder
    private MemberResponse(List<MemberVo> memberInfoVoList) {
        this.memberInfoVoList = memberInfoVoList;
    }

    public static MemberResponse from(List<MemberVo> memberInfoVoList) {
        return MemberResponse.builder()
                .memberInfoVoList(memberInfoVoList)
                .build();
    }
}