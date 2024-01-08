package hello.core.singleton;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class ConfigurationSingletonTest {

  @Test
  void configurationTest() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
    OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
    MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

    MemberRepository memberRepository1 = memberService.getMemberRepository();
    MemberRepository memberRepository2  = orderService.getMemberRepository();
    System.out.println("memberService -> memberRepository1  = " + memberRepository1);
    System.out.println("orderService -> memberRepository2 = " + memberRepository2);
    System.out.println("memberRepository = " + memberRepository);

    assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
    assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
  }

  @Test
  void configurationDepp() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    AppConfig bean = ac.getBean(AppConfig.class);

    System.out.println("bean = " + bean.getClass());
    // note: bean = class hello.core.AppConfig$$EnhancerBySpringCGLIB$$ee780691
    // note: class명에 CGLIB$$ee780691 가 붙는다.
    // note: 중요: ac.getBean(AppConfig.class);를 통해서 가져온 bean이 AppConfig 자체가 아니라 CGLib이라는 기술로 만들어지고 AppConfig를 상속 받은 Bean객체이다.

    // note: AppConfig@CGLIB 예상 코드
//    ```java
//    @Bean
//    public MemberRepository memberRepository() {
//      if (memoryMemberRepository가 이미 스프링 컨테이너에 등록되어있다면?) {
//        return 스프링 컨테이너에서 찾아서 반환한다.;
//      } else {
//        기존 로직을 호출해서 MemoryMemberRepository를 생성하고 스프링 컨테이너에 등록한다.
//        return 생성된 객체를 반환한다.
//      }
//    }
//    ```
  }


}
