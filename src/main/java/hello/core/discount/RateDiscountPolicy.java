package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

// note: 같은 빈 이름이 2개인 경우

// note: 1. 필드주입시 변수명 이름을 빈객체 이름으로 지정하면 같은 타임의 빈 객체 이름으로 주입된다.

// note: 2. qualifier
// note:   bean객체에 이름을 지정한다.
// note:   용도: 같은 타임의 bean객체가 있을때 명시적으로 빈 객체를 선택해서 가져올 수 있다.
// note:   사용법: @Component가 붙인곳에 @Qualifier를 붙이거나 AppConfig에서 Bean객체를 생성할떄 @Qualifier를 붙인다. 그 후 사용하려는 객체에서 의존성 주입시 @Qualifier를 붙인다.
// note:   단점: qualifier를 써야하는것이 단점이다.
// note:   예외 사항: 만약에 @Qualifier로 객체를 못찾으면 bean객체 이름으로 찾고 그래도 못찾으면 에러가 발생한다.

// note: 3. @Primary
// note:   같은 이름의 빈 객체가 있으면 @Primary가 붙은 객체가 주입 대상이 되어서 주입된다.
// note:   그러면 나머지 객체들은 필요에 따라서 동적으로 주입하거나 @Qualifier를 붙이면 된다.

@Component
//@Primary
@Qualifier("mainDiscountPolicy")
public class RateDiscountPolicy implements DiscountPolicy {

  private int discountPercent = 10;

  @Override
  public int discount(Member member, int price) {
    if (member.getGrade() == Grade.VIP) {
      return price * discountPercent / 100;
    } else {
      return 0;
    }
  }
}
