package hello.core.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

//public class NetworkClient implements InitializingBean, DisposableBean { // note: version1 빈 생성과 소멸 관리
public class NetworkClient {
  // note: InitializingBean, DisposableBean를 활용하여 빈 콜백 생명주기 다루는 방법
  // note:   1. 인터페이스는 스프링 전용 인터페이스라서 스프링에 의존적이라는 단점이 있다.
  // note:   2. 내가 코드를 고칠 수 없는 외부 라이브러리에는 적용할 수 없다.
  private String url;

  public NetworkClient() {
    System.out.println("생성자 호출, url = " + url);
  }

  public void setUrl(String url) {
    this.url = url;
  }

  // 서비스 시작시 호출
  public void connect() {
    System.out.println("connect: " + url);
  }

  public void call(String message) {
    System.out.println("call: " + url + " message = " + message);
  }

  // 서비스 종료시 호출
  public void disconnect() {
    System.out.println("close: " + url);
  }


  // note: version1 생성과 소멸 관리
//  @Override
//  public void afterPropertiesSet() throws Exception { // note: 의존 관계 주입이 끝나면 호출된다.
//    System.out.println("NetworkClient.afterPropertiesSet");
//    connect();
//    call("초기화 연결 메시지");
//  }
//
//  @Override
//  public void destroy() throws Exception { // note: 빈이 종료될 때 호출된다.
//    System.out.println("NetworkClient.destroy");
//    disconnect();
//  }


  // note: version2 빈 생성과 소멸 관리 메서드 등록
  @PostConstruct // note: version3: @PostConstruct 애노테이션을 활용하여 빈 생명주기 관리
  public void init() throws Exception { // note: 의존 관계 주입이 끝나면 호출된다.
    System.out.println("NetworkClient.afterPropertiesSet");
    connect();
    call("초기화 연결 메시지");
  }

  @PreDestroy // note: version3: @PreDestory 애노테이션을 활용하여 빈 생명주기 관리
  // note: version3은 간단하게 애노테이션으로 빈 생명주기에 메서드를 호출 할 수 있다.
  // note: 스프링은 권장하는 방식이고 간편하다. 빈 생명주기에 특정 메서드를 실행시키고 싶으면 version3인 @PostConstruct, @PreDestory 애노테이션을 사용하면된다.
  public void close() throws Exception { // note: 빈이 종료될 때 호출된다.
    System.out.println("NetworkClient.destroy");
    disconnect();
  }

}
