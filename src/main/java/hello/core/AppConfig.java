package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // note: @Configuration 애노테이션을 안붙이면 ConfigurationSingletonTest.java에 설명되어있는 것처럼 CGLIB 기술로 자식 객체가 생성되지 않는다.
// note: 하여 아래 사용된 @Bean으로 생성되는 객체들이 빈으로 등록이 되고 생성이 되지만 싱글톤을 보장하지는 않는다.
public class AppConfig {

//  @Autowired
//  MemberRepository memberRepository;

  @Bean
  public MemberService memberService() { // note: 메서드명과 리턴타입이 역할(배역)을 담당한다.
    System.out.println("call AppConfig.memberService");
    return new MemberServiceImpl(memberRepository()); // note: 내부 로직이 구현을 담당한다. // note: 메서드의 리턴타임이 MemberService인데 실제 리턴타입은 MemberServiceImpl이다. 이렇게 작성해도 상관없으며 실제로 빈등록될떄는 MemberServiceImpl로 치환되어서 등록된다.
  }

  @Bean
  public MemberRepository memberRepository() {
    System.out.println("call AppConfig.memberRepository");
    return new MemoryMemberRepository();
  }

  @Bean
  public OrderService orderService() {
    System.out.println("call AppConfig.orderService");
    return new OrderServiceImpl(memberRepository(), discountPolicy());
//    return null;
  }

  @Bean
  public DiscountPolicy discountPolicy() {
//    return new FixDiscountPolicy();
    return new RateDiscountPolicy(); // note: 할인 정책을 바꾸려면 간단히 구현체만 바꿔주면 된다.
  }

}