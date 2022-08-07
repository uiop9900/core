package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration  //설정으로 등록
@ComponentScan( //지정하지 않으면 선언한 클래스의 패키지를 기준으로 다 뒤진다. -> hello.core -->> 아예 config를 최상단의 class에서 선언한다.
        basePackages = "hello.core",
        excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION, classes = Configuration.class)  //기존 appConfig를 유지하기위해 설정
)  //@component를 스캔해서 알아서 컴포넌트 해준다.
public class AutoAppConfig {


}
