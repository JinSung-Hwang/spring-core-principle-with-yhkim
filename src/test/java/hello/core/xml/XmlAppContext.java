package hello.core.xml;

import static org.assertj.core.api.Assertions.assertThat;

import hello.core.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class XmlAppContext {

  // note: BeanFactory를 상속받고 있는 ApplicationContext는 AppConfig.class, AppConfig.xml, AppConfig.xxx 등 다양한 방식으로 설정 정보를 받을 수 있다.

  // note: AppConfig.class인 자바 파일로 설정 정보를 받으려면 ApplicationContext의 구현체인 AnnotationConfigApplicationContext를 사용하면 된다.
  // note: AppConfig.xml인 XML파일로 설정 정보를 받으려면 ApplicationContext의 구현체인 GenericXmlApplicationContext를 사용하면 된다.
  // note: 아래에서 appConfig.xml를 통해서 설정 정보 받는 예제를 확인할 수 있다.

  @Test
  void xmlAppContext() {
    ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
    MemberService memberService = ac.getBean("memberService", MemberService.class);
    assertThat(memberService).isInstanceOf(MemberService.class);
  }
}