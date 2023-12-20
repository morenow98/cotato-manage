package cotato.cotatomanage.repository;

import cotato.cotatomanage.domain.entity.Member;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MemoryMemberRepository implements MemberRepository {

    private final List<Member> repository = new ArrayList<>();

    @Override
    public void save(Member member) {
        repository.add(member);
        log.info("REPOSITORY 저장 완료");
    }
}
