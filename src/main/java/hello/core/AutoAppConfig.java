package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
//    basePackages = "hello.core.member", // note: 탐색할 패키지의 위치를 지정한다. 이 위치 이하의 빈 객체를 찾아서 등록한다. 이렇게하면 불필요한 library같은곳에서 탐색하는 시간을 줄 일 수 있다.
//    basePackageClasses = AutoAppConfig.class, // note: 지정한 클래스가 위치한 패키지부터 탐색을 시작한다.
    // note: 만약 지정하지 않으면 @ComponentScan이 붙여져 있는 클래스의 패키지부터 하위 패키지까지 빈 객체를 찾아서 등록한다.
    // note: 스프링 부트에서는 @SpringBootApplication안에 @ComponentScan이 메타애노테이션으로 되어있기 때문에 스프링 부트를 사용하면 @SpringBootApplication이 있는 패키지부터 빈 탐색, 등록을 진행한다.
    // note: 메타애노테이션으로 되어있다고 자바에서 연동을 해주는것이 아니라 스프링부트에서 연동해주는것이다.
    excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {}
