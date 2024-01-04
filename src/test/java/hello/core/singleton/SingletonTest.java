package hello.core.singleton;

import static org.assertj.core.api.Assertions.assertThat;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {
  
  @DisplayName("스프링이 없는 순수한 DI 컨테이너로 가져온 객체를 비교한다.")
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

  @DisplayName("싱글톤 패턴을 적용한 객체를 비교한다.")
  @Test
  void singleToneServiceTest() {
    SingletonService singletonService1 = SingletonService.getInstance();
    SingletonService singletonService2 = SingletonService.getInstance();

    System.out.println("singletonService1 = " + singletonService1);
    System.out.println("singletonService2 = " + singletonService2);

    assertThat(singletonService1).isSameAs(singletonService2);
  }
  
  @DisplayName("스프링 컨테이너는 싱글톤인 빈 객체를 가져온다.")
  @Test
  void springContainer() {
//    AppConfig appConfig = new AppConfig();
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    // note: 조회: 호출할 때 마다 객체 생성
    MemberService memberService1 = ac.getBean("memberService", MemberService.class);

    // note: 조회: 호출할 때 마다 객체를 생성
    MemberService memberService2 = ac.getBean("memberService", MemberService.class);

    System.out.println("memberService1 = " + memberService1);
    System.out.println("memberService2 = " + memberService2);

    assertThat(memberService1).isSameAs(memberService2);
  }
  

}
