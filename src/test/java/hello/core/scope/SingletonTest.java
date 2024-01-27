package hello.core.scope;

import static org.assertj.core.api.Assertions.assertThat;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class SingletonTest {

  @Test
  void singletonBeanFind() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(singletonBean.class);
    singletonBean singletonBean1 = ac.getBean(singletonBean.class);
    System.out.println("singletonBean1 = " + singletonBean1);
    singletonBean singletonBean2 = ac.getBean(singletonBean.class);
    System.out.println("singletonBean2 = " + singletonBean2);
    assertThat(singletonBean1).isSameAs(singletonBean2);

    ac.close();
  }

  @Scope("singleton")
  static class singletonBean {
    @PostConstruct
    public void init() {
      System.out.println("singletonBean.init");
    }

    @PreDestroy
    public void destroy() {
      System.out.println("singletonBean.destroy");
    }

  }

}
