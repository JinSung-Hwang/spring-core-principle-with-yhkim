package hello.core.autowired;

import hello.core.member.Member;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

public class AutowiredTest {

  @Test
  void AutowiredOption() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
  }

  static class TestBean {
    @Autowired(required = false)
    public void setNoBean1(Member noBean1) { // note: required = false이면 Member가 Bean등록이 안되어있으면 setNoBean1자체가 호출되지 않늗다.
      System.out.println("noBean1 = " + noBean1);
    }
    @Autowired
    public void setNoBean2(@Nullable Member noBean2) {
      System.out.println("noBean2 = " + noBean2); // note: null이 들어온다.
    }
    @Autowired
    public void setNoBean3(Optional<Member> noBean3) {
      System.out.println("noBean3 = " + noBean3); // note: Optional.empty가 들어온다.
    }
  }

}
