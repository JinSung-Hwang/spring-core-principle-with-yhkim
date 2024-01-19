package hello.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.beans.factory.annotation.Qualifier;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier("mainDiscountPolicy")
public @interface MainDiscountPolicy {}
// note: @Qualifier("mainDiscountPolicy")를 사용하던것을 @MainDiscountPolicy로 대체할 수 있도록 만들었다.
// note: 이렇게 @Qualifier의 메타애노테이션을 가져와서 만들면 @Qualifier("mainDiscountPolicy")로 만들었을때 안쪽 문자열에 오탈자가 있더라도 컴파일에서는 에러가 발생하지 않는다.
// note: 하여 @MainDiscountPolicy을 사용할때 오탈자가 있으면 컴파일 에러가 발생한다.

// note: 하지만 이렇게 @MainDiscountPolicy을 만들면 에노테이션에대한 스프링 공통의 애노테이션이 아니기 때문에 가독성이 떨어지고 유지보수가 어려워질 수 있다.
// note: 내 개인적인 취향으로는 커스텀애노테이션을 만들면 가독성도 떨어지고 유지보수에 어려워진다고 생각한다. 커스텀 애노테이션을 만들게되면 문서화를 잘해야한다고 생각하고 권한과 같은 특수한 목적으로 사용하는것이 좋다고 생각한다.

