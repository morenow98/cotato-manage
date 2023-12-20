package cotato.cotatomanage.repository;

import cotato.cotatomanage.domain.Member;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MemoryMemberRepository implements MemberRepository {

    private final Map<String, Member> repository = new HashMap<>();

    @Override
    public void save(Member member) {
        repository.put(member.getName(), member);
        log.info("REPOSITORY 저장 완료");
    }


}
