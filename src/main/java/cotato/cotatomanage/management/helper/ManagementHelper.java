package cotato.cotatomanage.management.helper;

import cotato.cotatomanage.management.domain.Part;
import cotato.cotatomanage.management.vo.MemberVo;
import cotato.cotatomanage.management.vo.PartVo;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
public class ManagementHelper {

    public void sortPart(List<PartVo> partInfoVoList) {
        partInfoVoList.sort(Comparator
                .comparing(PartVo::getAbility)
                .reversed()
                .thenComparing(PartVo::getCount)
                .thenComparing(PartVo::getPart, Comparator.comparing(Part::ordinal))
        );
    }

    public void sortAll(List<MemberVo> memberInfoList) {
        memberInfoList.sort(Comparator
                .comparing(MemberVo::getAbility)
                .reversed()
                .thenComparing(MemberVo::getAge, Comparator.reverseOrder())
                .thenComparing(MemberVo::getPeriod)
                .thenComparing(MemberVo::getName));
    }
}