package hello.core.autowired;

import static org.assertj.core.api.Assertions.assertThat;

import hello.core.AppConfig;
import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AllBeanTest {
  // note: 등록된 빈객체가 모두 필요할 수 있다.
  // note: 예를 들어 고객이 할인 정책을 정해서 할인을 받는다고하면 할인 정책의 빈들을 가져와서 할인 정책을 선택할 수 있어야 한다.
  // note:
  @Test
  void findAllBean() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

    DiscountService discountService = ac.getBean(DiscountService.class);
    assertThat(discountService).isInstanceOf(DiscountService.class);

    Member member = new Member(1L, "userA", Grade.VIP);
    int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");
    assertThat(discountPrice).isEqualTo(1000);

    int fixDiscountPolicy = discountService.discount(member, 20000, "rateDiscountPolicy");
    assertThat(fixDiscountPolicy).isEqualTo(2000);
  }

  static class DiscountService {
    private final Map<String, DiscountPolicy> policyMap;
    private final List<DiscountPolicy> policies;

    public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
      this.policyMap = policyMap;
      this.policies = policies;
      System.out.println("policyMap = " + policyMap + ", policies = " + policies);
    }

    public int discount(Member member, int price, String discountCode) {
      DiscountPolicy discountPolicy = policyMap.get(discountCode);
      return discountPolicy.discount(member, price);
    }
  }


}
