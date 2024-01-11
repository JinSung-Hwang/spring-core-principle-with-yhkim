package hello.core.scan.filter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

public class ComponentFilterAppConfigTest {

  @Test
  void filterScan() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
    BeanA beanA = ac.getBean("beanA", BeanA.class);
    assertThat(beanA).isNotNull();

    assertThrows(
        NoSuchBeanDefinitionException.class,
        () -> ac.getBean("beanB", BeanB.class));
  }

  @Configuration
  @ComponentScan(
      includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
      excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
      // note: scan FilterType은 5가지로 나누어질 수 있다.
      // note:   1. ANNOTATION: 기본값, 애노테이션을 인식해서 스캔한다.
      // note:   2. ASSIGNABLE: 지정한 타입과 자식 타입을 인식해서 스캔한다.
      // note:   3. ASPECTJ: AspectJ패턴을 사용해서 스캔한다.
      // note:   4. REGEX: 정규 표현식을 이용해서 스캔한다.
      // note:   5.CUSTOM: TypeFilter라는 인터페이스를 구현해서 처리한다.
  )
  static class ComponentFilterAppConfig {}


}
