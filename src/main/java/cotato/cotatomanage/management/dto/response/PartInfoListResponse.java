package cotato.cotatomanage.management.dto.response;

import cotato.cotatomanage.management.vo.PartInfoVo;
import lombok.Builder;

import java.util.List;

public class PartInfoListResponse {

    List<PartInfoVo> partInfoVoList;

    @Builder
    private PartInfoListResponse(List<PartInfoVo> partInfoVoList) {
        this.partInfoVoList = partInfoVoList;
    }

    public static PartInfoListResponse from(List<PartInfoVo> partInfoVoList) {
        return PartInfoListResponse.builder()
                .partInfoVoList(partInfoVoList)
                .build();
    }
}
