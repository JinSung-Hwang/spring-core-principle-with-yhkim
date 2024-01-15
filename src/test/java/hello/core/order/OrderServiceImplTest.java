package hello.core.order;

import static org.assertj.core.api.Assertions.assertThat;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Test;

class OrderServiceImplTest {
  // note: 생성자 주입을 사용하라
  // note:   생성자 주입은 의존성을 final로 설정하면 의존성이 주입되었는지 확인할 수 있고 컴파일 오류를 발생시킬 수 있다. (생성자 주입 방식이 아니면 final방식을 사용할 수 없다.)
  // note:   또한 선택적으로 의존성을 변경하려면 수정자 주입과 같이 사용할 수 있다.
  // note:   필드 주입은 테스트작성시 다른 객체로 바꿀 수 없고 테스트하려면 스프링 부트가 필요해지기 때문에 필드 주입은 사용하지 않는것이 좋다.
  @Test
  void createOrder() {
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    memberRepository.save(new Member(1L, "name", Grade.VIP));

    OrderServiceImpl orderService = new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    Order order = orderService.createOrder(1L, "itemA", 10000);
    assertThat(order.getDiscountPrice()).isEqualTo(1000);
  }

}