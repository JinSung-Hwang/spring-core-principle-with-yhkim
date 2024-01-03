package hello.core.singleton;

public class SingletonService {

  // note: 이 객체를 1개만 static으로 미리 생성해둔다.
  private static final SingletonService instance = new SingletonService();

  // note: 생성해둔 객체를 읽어온다.
  public static SingletonService getInstance() {
    return instance;
  }

  private SingletonService() { } // note: 외부에서 임의로 이 객체를 생성하지 못하도록 private로 만듬

  public void logic() {
    System.out.println("로직 실행");
  }


}
