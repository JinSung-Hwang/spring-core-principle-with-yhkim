package hello.core.order;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

  MemberService memberService;
  OrderService orderService;

  @BeforeEach
  public void beforeEach() {
    AppConfig appConfig = new AppConfig();
    memberService = appConfig.memberService();
    orderService = appConfig.orderService();
  }

  @Test
  void createOrder() {
    // given
    Long memberId = 1L;
    Member member = new Member(memberId, "memberA", Grade.VIP);
    memberService.join(member);

    // when
    Order order = orderService.createOrder(memberId, "itemA", 10000);

    // then
    Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
  }

  @Test
  void fieldInjectionTest() {
    OrderServiceImpl orderService = new OrderServiceImpl();
    // note: 필드 주입을하면 의존성을 넣어줄 방법이 없다.
    // note: 그래서 의존성을 넣기위해서 setter method를 만들어서 넣어줘야한다.
//    orderService.setMemberRepository(new MemoryMemberRepository());
//    orderService.setDiscountPolicy(new DiscountPolicy());

    orderService.createOrder(1L, "itemA", 10000);
  }

}
