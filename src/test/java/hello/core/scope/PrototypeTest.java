package hello.core.scope;

import static org.assertj.core.api.Assertions.assertThat;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class PrototypeTest {
  @Test
  void prototypeBeanFind() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(prototypeBean.class);
    System.out.println("find prototypeBean1");
    prototypeBean prototypeBean1 = ac.getBean(prototypeBean.class);
    System.out.println("find prototypeBean2");
    prototypeBean prototypeBean2 = ac.getBean(prototypeBean.class);
    System.out.println("prototypeBean1 = " + prototypeBean1);
    System.out.println("prototypeBean2 = " + prototypeBean2);
    assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

    ac.close(); // note: 프로토타입 빈은 스프링 컨테이너가 종료될때 @PreDestory가 호출되지 않는다. 즉 프로토타입 빈은 생성과 의존성 주입까지만 관여하고 더이상 관리하지 않는다.
//    prototypeBean1.destroy(); // note: 만약 프로토타입 빈을 종료시키고 싶으면 프로토타입 빈을 사용하는 client가 직접 종료 메서드를 호출해야한다.
  }

  @Scope("prototype") // note: 프로토타입 빈은 스프링 컨테이너에 빈 객체를 요청할때마다 빈 객체가 새로 생선한다.
  static class prototypeBean {
    @PostConstruct
    public void init() {
      System.out.println("prototypeBean.init");
    }

    @PreDestroy
    public void destroy() {
      System.out.println("prototypeBean.destroy");
    }
  }
}
