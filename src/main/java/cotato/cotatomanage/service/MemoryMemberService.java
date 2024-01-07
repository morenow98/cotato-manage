package cotato.cotatomanage.service;


import com.fasterxml.jackson.core.io.schubfach.DoubleToDecimal;
import cotato.cotatomanage.domain.Part;
import cotato.cotatomanage.domain.dto.JoinMemberRequest;
import cotato.cotatomanage.domain.dto.PartResponse;
import cotato.cotatomanage.domain.entity.Member;
import cotato.cotatomanage.repository.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemoryMemberService {

    private final MemoryMemberRepository memberRepository;

    public void joinMember(JoinMemberRequest request){

        int period = StringToInt(request.getPeriod());
        Member member = Member.builder()
                .name(request.getName())
                .period(period)
                .age(Integer.parseInt(request.getAge()))
                .part(Part.getPart(request.getPart()))
                .ability(Integer.parseInt(request.getAge()))
                .build();
        memberRepository.save(member);
    }

    public List<PartResponse> getAllPartByPeriod(Integer period){
        return Arrays.stream(Part.values())
                .map(part -> getEachPart(part,period))
                .toList();
    }

    private PartResponse getEachPart(Part part, int period){
        List<Member> partMembersAll = memberRepository.findByPart(part);
        List<Member> partMembersPeriod = partMembersAll.stream()
                .filter(member ->member.getPeriod() == period)
                .collect(Collectors.toList());
        if (partMembersPeriod.isEmpty()){
            return null;
        }
        return PartResponse.builder()
                .part(part.getKey())
                .ability((int) getAbilityAverage(partMembersPeriod))
                .count(partMembersPeriod.size())
                .build();
    }

    private static int StringToInt(String inputString) {
        String numericPart = inputString.replaceAll("[^0-9]","");
        try{
            return Integer.parseInt(numericPart);
        } catch(NumberFormatException e){
            return -1;
        }
    }

    private double getAbilityAverage(List<Member> partMembers){
        return partMembers.stream()
                .mapToInt(Member::getAbility)
                .average().getAsDouble();
    }
}
