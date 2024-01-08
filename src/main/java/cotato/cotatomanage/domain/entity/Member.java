package cotato.cotatomanage.domain.entity;

import cotato.cotatomanage.domain.Part;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

@Getter
public class Member {

    private String name;
    private int period;
    private int age;
    private Part part;

    private int ability;



    @Builder
    public Member(String name, Integer period, Integer age, Part part, Integer ability){
        this.name = name;
        this.period = period;
        this.age = age;
        this.part = part;
        this.ability = ability;
    }

    public int addAbilityByPeriod(int currentPeriod){
        int subPeriod = currentPeriod - period;
        return subPeriod * 2;
    }

    public int addAbilityByMonth(Part part, Month month){
        if(part.getMonthList().contains(month))
            return 10;
        return 0;
    }

    public int calculateAbility(int currentPeriod, Part part){
        Month month = LocalDate.now().getMonth();
        if(!isLimitAge(this.age)){
            return ability + addAbilityByPeriod(currentPeriod) + addAbilityByMonth(part,month);
        }
        return ability;
    }
    public boolean isLimitAge(int age){
        if (age >= 27){
            return true;
        }
        return false;
    }

//    @Override
//    public int compareTo(Member o) {
//        return o.getAbility() - this.getAbility();
//    }
}
