package hello.core.web;

import hello.core.common.MyLogger;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LogDemoController {
  private final  LogDemoService logDemoService;
  private final MyLogger myLogger; // note: MyLogger는 리퀘스트 스코프 빈으로 생성되었다.(@Scope(value = "request"))하여 Http요청되어야 빈이 생성되기때문에 이 LogDemoController에서는 주입할 수가 없어서 에러가 난다.

  @RequestMapping("log-demo")
  @ResponseBody
  public String logDemo(HttpServletRequest request) {
    String requestURL = request.getRequestURL().toString();
    myLogger.setRequestURL(requestURL);

    myLogger.log("controller test");
    logDemoService.logic("testId");
    return "OK";
  }

}
