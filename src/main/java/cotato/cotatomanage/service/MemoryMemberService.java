package cotato.cotatomanage.service;


import cotato.cotatomanage.domain.Part;
import cotato.cotatomanage.domain.dto.JoinMemberRequest;
import cotato.cotatomanage.domain.entity.Member;
import cotato.cotatomanage.repository.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
                .part(Part.valueOf(request.getPart()))
                .build();
        memberRepository.save(member);
    }

    private static Integer StringToInt(String inputString) {
        String numericPart = inputString.replaceAll("[^0-9]","");
        try{
            return Integer.parseInt(numericPart);
        } catch(NumberFormatException e){
            return -1;
        }
    }
}
