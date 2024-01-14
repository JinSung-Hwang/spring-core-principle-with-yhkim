package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import java.util.Dictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component("service")
@Component
public class OrderServiceImpl implements OrderService {
  // note: @Autowired 로 주입할 수 있는것은 스프링에 등록된 bean객체만 사용가능하다. 일반 객체는 @Autowired로 주입이 불가능하다.


  // note: 필드 주입, 필드에 @Autowired 애노테이션을 붙여서 사용하는 경우
  // note: 1. configuration에 사용하기 -> 물론 다른 방법도 있고 좋은 방법도 있어서 사용하지 않는다
  // note: 2. 테스트코드 작성할때 간편하게 사용하기 위해서 사용한다.
  // note: 그 이외에 필드 주입은 사용하지 않도록 권장된다. 왜냐하면 테스트할때 다른 객체로 대체하려고해도 다른 객체로 대체하기 힘들다.
  // note: 필드 주입은 생성자 또는 Setter를 통해서 주입할 수 없기 때문에 테스트시 다른 객체로 바꾸어서 테스트하기 힘들다.
  @Autowired
  private MemberRepository memberRepository;
  @Autowired
  private DiscountPolicy discountPolicy;
//  private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//  private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

  @Autowired // note: 생성자가 하나만 있으면 @Autowired를 안달아도 스프링부트에서 자동으로 주입해준다. 즉, OrderServiceImpl은 생성자가 하나이기때문에 @Autowired를 생략해도 되긴한다. 하지만 명시적인게 더 좋다.
  public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
    // note: 생성자 주입은 OrderServiceImpl객체가 생성될때 생성자 주입이 일어난다.
    this.memberRepository = memberRepository;
    this.discountPolicy = discountPolicy;
  }

  // note: 수정자 주입에도 @Autowired를 붙이면 자동으로 주입한다.
  @Autowired(required = false) // note: required false로 설정하면 주입을 선택적으로 할 수 있다. 자동 주입할 객체가 없으면 메서드가 호출되지 않는다.
  public void setMemberRepository(MemberRepository memberRepository) {
    // note: 수정자 주입은 OrderServiceImple이 생성되고 setter를 호출해서 주입이 일어난다.
    this.memberRepository = memberRepository; // note: 수정자 주입을하려면 MemberRepository에 final을 빼야한다.
  }
  @Autowired
  public void setDiscountPolicy(DiscountPolicy discountPolicy) {
    this.discountPolicy = discountPolicy;
  }

  @Autowired // note: 일반 메서드에서 @Autowired를 붙여서 Setter주입처럼 주입을 할 수 있다. 보통 이렇게 사용하지 않으며 동적으로 주입하려고하면 setter 주입을 사용한다.
  public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
    this.memberRepository = memberRepository;
    this.discountPolicy = discountPolicy;
  }

  public Order createOrder(Long memberId, String itemName, int itemPrice) {
    Member member = memberRepository.findById(memberId);
    int discountPrice = discountPolicy.discount(member, itemPrice);

    return new Order(memberId, itemName, itemPrice, discountPrice);
  }

  // 테스트 용도
  public MemberRepository getMemberRepository() {
    return memberRepository;
  }

}
