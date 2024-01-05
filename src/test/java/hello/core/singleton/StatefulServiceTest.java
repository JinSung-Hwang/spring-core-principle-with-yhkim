package hello.core.singleton;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

  @Test
  void statefulServiceSingleton() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
    StatefulService statefulService1 = ac.getBean(StatefulService.class);
    StatefulService statefulService2 = ac.getBean(StatefulService.class);

    // ThreadA: A사용자 10000원 주문
    statefulService1.order("userA", 10000);
    // ThreadB: B사용자 20000원 주문
    statefulService1.order("userB", 20000);

    // ThreadA: 사용자A 주문 금액 조회
    int price = statefulService1.getPrice();
    System.out.println("price = " + price);

    assertThat(statefulService1.getPrice()).isEqualTo(20000);
    // note: statefulService에서 지역변수를 사용했기 때문에 userA의 price를 조회해도 userB의 주문 정보가 나왔다. 싱글톤이라서 같은 객체를 사용한다.
    // note: 이렇게 간단한 예제에서만 지역변수 사용했을때 문제가 발생했지만 여러 상속에 걸쳐있으면 간헐적으로 에러가 발생해서 찾기 어려울 수 있다.
  }

  static class TestConfig {
    @Bean
    public StatefulService statefulService() {
      return new StatefulService();
    }
  }


}