package cotato.cotatomanage.management.dto.response;

import cotato.cotatomanage.management.vo.PartVo;
import lombok.Builder;

import java.util.List;

public class PartResponse {

    List<PartVo> partInfoVoList;

    @Builder
    private PartResponse(List<PartVo> partInfoVoList) {
        this.partInfoVoList = partInfoVoList;
    }

    public static PartResponse from(List<PartVo> partInfoVoList) {
        return PartResponse.builder()
                .partInfoVoList(partInfoVoList)
                .build();
    }
}