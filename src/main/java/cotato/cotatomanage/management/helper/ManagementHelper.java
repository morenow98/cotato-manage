package cotato.cotatomanage.management.helper;

import cotato.cotatomanage.management.domain.Part;
import cotato.cotatomanage.management.vo.MemberInfoVo;
import cotato.cotatomanage.management.vo.PartInfoVo;

import java.util.Comparator;
import java.util.List;

public class ManagementHelper {

    public void sortPart(List<PartInfoVo> partInfoVoList) {
        partInfoVoList.sort(Comparator
                .comparing(PartInfoVo::getAbility)
                .reversed()
                .thenComparing(PartInfoVo::getCount)
                .thenComparing(PartInfoVo::getPart, Comparator.comparing(Part::ordinal))
        );
    }

    public void sortAll(List<MemberInfoVo> memberInfoList) {
        memberInfoList.sort(Comparator
                .comparing(MemberInfoVo::getAbility)
                .reversed()
                .thenComparing(MemberInfoVo::getAge, Comparator.reverseOrder())
                .thenComparing(MemberInfoVo::getPeriod)
                .thenComparing(MemberInfoVo::getName));
    }
}
