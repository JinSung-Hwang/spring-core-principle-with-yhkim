package hello.core.web;

import hello.core.common.MyLogger;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LogDemoController {
  private final LogDemoService logDemoService;
  private final MyLogger myLogger; // note: MyLogger는 리퀘스트 스코프 빈으로 생성되었다.(@Scope(value = "request"))하여 Http요청되어야 빈이 생성되기때문에 이 LogDemoController에서는 주입할 수가 없어서 에러가 난다.
  // note: 원래는 에러가 났어야하지만 proxyMode = ScopedProxyMode.TARGET_CLASS로 설정되어있어서 프록시로 주입되면서 에러가 나지 않는다.

//  private final ObjectProvider<MyLogger> myLoggerObjectProvider;
  // note: MyLogger에 ObjectProvider로 감싸면 myLogger를 찾을 수 있는 디펜던시 MyLogger가 주입된다.

  @RequestMapping("log-demo")
  @ResponseBody
  public String logDemo(HttpServletRequest request) throws InterruptedException {
    String requestURL = request.getRequestURL().toString();
//    MyLogger myLogger = myLoggerObjectProvider.getObject();
    System.out.println("requestURL = " + requestURL.getClass());
    myLogger.setRequestURL(requestURL);

    myLogger.log("controller test");
    Thread.sleep(1000);
    logDemoService.logic("testId");
    return "OK";
  }

}
