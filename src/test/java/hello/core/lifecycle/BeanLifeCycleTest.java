package hello.core.lifecycle;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

  @Test
  public void lifeCycleTest() {
    ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
    NetworkClient client = ac.getBean(NetworkClient.class);
    ac.close();
  }

  @Configuration
  static class LifeCycleConfig {
    // note: version2 빈 생성과 소멸 관리
    // note: 빈에 initMethod, destoryMethod 지정 방식의 장점
    // note: 1. 메서드 이름을 자유롭게 지정할 수 있다.
    // note: 2. 빈 객체가 스프링 코드에 의존하지 않는다. (인터페이스를 구현하지 않아도 된다.)
    // note: 3. 코드가 아니라 설정 정보를 등록하는 방식이기때문에 빈 생명주기 시점에 외부라이브러리 메서드를 호출 할 수 있다.
//    @Bean(initMethod = "init", destroyMethod = "close") // note: version2 빈 생성과 소멸 관리
    // note: destroyMethod는 기본값이 (inferred) 추론이라는 기능이있는데 등록된 빈의 close() 또는 shutdown()이라는 이름의 메서드를 빈 소멸시 자동으로 호출해준다. 추론을 사용하지 않을땐 "" 빈문자열을 넣어준다.
    @Bean
    public NetworkClient networkClient() {
      NetworkClient networkClient = new NetworkClient();
      networkClient.setUrl("http://hello-spring.dev");
      return networkClient;
    }
  }


}
