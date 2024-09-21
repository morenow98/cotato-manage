package cotato.cotatomanage.service;


import com.fasterxml.jackson.core.io.schubfach.DoubleToDecimal;
import cotato.cotatomanage.domain.Part;
import cotato.cotatomanage.domain.dto.JoinMemberRequest;
import cotato.cotatomanage.domain.dto.MemberResponse;
import cotato.cotatomanage.domain.dto.PartResponse;
import cotato.cotatomanage.domain.entity.Member;
import cotato.cotatomanage.repository.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemoryMemberService {

    private final MemoryMemberRepository memberRepository;


    /**
     * 회원 등록
     */
    public void joinMember(JoinMemberRequest request) {

        int period = StringToInt(request.getPeriod());
        validateMemberAge(request.getAge());

        Member member = Member.builder()
                .name(request.getName())
                .period(period)
                .age(request.getAge())
                .part(Part.getPart(request.getPart()))
                .ability(request.getAge())
                .build();
        memberRepository.save(member);
        log.info("동아리원 등록: {}", request.getName());
    }

    /**
     * 모든 멤버 출력
     */

    public List<MemberResponse> getAllMember(int period) {
        return memberRepository.findAll().stream()
                .map(member -> buildMemberResponse(member, period))
                .sorted()
                .collect(Collectors.toList());
    }

    public List<MemberResponse> getAllPartMember(Part part, int period) {
        return memberRepository.findAll().stream()
                .map(member -> buildMemberResponse(member, period))
                .filter(memberResponse -> memberResponse.getPart() == part.getKey())
                .sorted()
                .collect(Collectors.toList());
    }

    public MemberResponse buildMemberResponse(Member member, int period) {
        return MemberResponse.builder()
                .name(member.getName())
                .period(member.getPeriod() + "기")
                .age(member.getAge())
                .part(member.getPart().getKey())
                .ability(member.calculateAbility(period, member.getPart()))
                .build();
    }

    /**
     * 기수별 모든 파트 출력
     */
    public List<PartResponse> getAllPartByPeriod(Integer period) {
        return Arrays.stream(Part.values())
                .map(part -> getEachPart(part, period))
                .filter(Objects::nonNull).sorted().toList();
    }

    /**
     * 기수별 각 파트 멤버
     */
    private PartResponse getEachPart(Part part, int period) {
        List<Member> partMembersAll = memberRepository.findByPart(part);
        List<Member> partMembersPeriod = partMembersAll.stream()
                .filter(member -> member.getPeriod() == period)
                .collect(Collectors.toList());
        if (partMembersPeriod.isEmpty()) {
            return null;
        }
        return PartResponse.builder()
                .part(part.getKey())
                .ability(getAbilityAverage(partMembersPeriod))
                .count(partMembersPeriod.size())
                .build();
    }

    private static int StringToInt(String inputString) {
        String numericPart = inputString.replaceAll("[^0-9]", "");
        try {
            return Integer.parseInt(numericPart);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private int getAbilityAverage(List<Member> partMembers) {
        return (int) partMembers.stream()
                .mapToInt(Member::getAbility)
                .average()
                .orElse(0);
    }

    private void validateMemberAge(int age) {
        if (age>30) {
            throw new IllegalArgumentException("너무 늙었습니다!");
        } else if (age<22) {
            throw new IllegalArgumentException("너무 어립니다!");
        }
    }
}
