package cotato.cotatomanage;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CotatoManageApplication {

	public static void main(String[] args){
		SpringApplication.run(CotatoManageApplication.class, args);

	}
}
/*

***************** 제가 작성을 하다 진전이 없어 기훈님 코드를 참고해 작성하였습니다 ******************
***************** 기훈님 코드를 보며 새로 알게 된 내용을 적어 주석으로 첨부합니다  ******************
ResponseEntity : HTTP 요청(Request) 또는 응답(Response)에 해당하는 HttpHeader와 HttpBody를 포함하는 클래스

서비스 객체 생성해 작성

Postmapping (/add)
<?> : 임의의 제네릭 타입 변수이다.
post 과정 : 클라이언트로부터 받은 데이터를 JSON 형태의 객체로 변환하여 메서드의 매개변수로 전달합니다

@RequestBody : HTTP 요청 본문을 AddmemberRequest 객체로 변환하는 역할을 하여 request 매개변수로 받는다.

memberService의 addMember 메서드에 request 매개변수를 넣어준다.

return responseEntity.ok().build() : 메서드가 성공적으로 실행되었을 때, 클라이언트에게 HTTP 상태 코드 200을 응답해준다.

.ok() : 상태코드 200
.build() : 본문이 없는 응답 생성

Getmapping(/print/part/{period})

period는 경로 변수로, 클라이언트가 지정한 기간을 나타낸다.

@PathVariable : 경로 변수값을 메서드 매개변수에 바인딩하는 어노테이션

responseEntity.ok().body() 성공응답으로 본문을 생성한다.


slf4j : simple logging facade for java, 어플리케이션에서 로깅에 대한 추상화 제공하는 인터페이스

localdate : 현재 날짜를 얻을 수 있는 객체

builder : 빌더 패턴을 자동으로 생성해주는 기능 제공

override : 해당 메서드가 부모 클래스나 인터페이스에서 상속된 메서드를 재정의

private static MemberRepository instance = new MemberRepository()
		싱글톤 패턴으로 알려진 디자인 패턴의 일종.
		싱글톤 패턴은 어플리케이션 전체에서 단일한 인스턴스를 유지하고
		해당 인스턴스에 대한 전역적인 접근을 제공하는 패턴입니다.
		이를 통해 자원을 효율적으로 사용하고 객체 간의 일관성을 유지할 수 있습니다.

foreach 반복문은 array 객체에서만 사용가능한 메서드이다.
순서대로 배열의 요소를 지나감

log.info : 로그인, 상태변경과 같은 정보성 메시지를 나타내는 로그레벨

poll() 메서드는 리스트의 첫번째 요소를 제거하고  반환

priorityqueue list의 파트 이름을 꺼내와 orderedList를 만든다.

enum은 열거형 상수들의 집합을 정의하는 데이터 타입입니다.

Part enumPart = Part.valueOf(part);
문자열 part를 Part 열거형으로 변환
*/