package domain;

import lombok.Generated;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Getter
@Repository
public class MemberRepository {
    private static List<Member> members = new ArrayList<>();


    public List<Member> findAll(){
        return members;
    }

    public Member save(Member member){
       members.add(member);
       return member;
    }


    public Member findOne(int id){
        for(Member member : members){
            if(member.getId() == id){
                return member;
            }
        }
        return null;
    }



}
