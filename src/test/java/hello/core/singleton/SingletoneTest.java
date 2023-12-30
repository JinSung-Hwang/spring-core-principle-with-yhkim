package hello.core.singleton;

import static org.assertj.core.api.Assertions.assertThat;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletoneTest {
  
  @DisplayName("스프링이 없는 순수한 DI 컨테이너")
  @Test
  void pureContainer() {
    AppConfig appConfig = new AppConfig();
    // note: 조회: 호출할 때 마다 객체 생성
    MemberService memberService1 = appConfig.memberService();

    // note: 조회: 호출할 때 마다 객체를 생성
    MemberService memberService2 = appConfig.memberService();

    System.out.println("memberService1 = " + memberService1);
    System.out.println("memberService2 = " + memberService2);

    assertThat(memberService1).isNotSameAs(memberService2);
  }
}
