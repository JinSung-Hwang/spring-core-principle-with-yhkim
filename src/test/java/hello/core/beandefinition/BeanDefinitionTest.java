package hello.core.beandefinition;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BeanDefinitionTest {

  // note: 스프링에서는 'BeanDefinition'이라는 것으로 빈 메타정보를 추상화를 한다.
  // note: 스프링에서 빈을 만드는 방법은 2개가 있다.
  // note: 1. 직접 스프링 빈을 등록하는 방법 - XML 설정 방식
  // note: 2. 빈 팩토리를 통해서 빈을 등록하는 방법 - AppConfig.class 설정 방식

//  AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig .class);
  GenericXmlApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");

  @DisplayName("빈 설정 메타정보를 확인합니다.")
  @Test
  void findApplicationBean() {
     String[] beanDefinitionNames = ac.getBeanDefinitionNames();
     for (String beanDefinitionName : beanDefinitionNames) {
       BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

       if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
         System.out.println("beanDefinitionName = " + beanDefinitionName + " beanDefinition = " + beanDefinition);
       }
     }
  }

}
