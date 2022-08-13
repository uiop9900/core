package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
// MODE --> 클래스면 Target_class, 인터페이스면 interfaces 선택
// CGLIB으로 일단 가짜 프록시를 넣어두고 나중에 진짜로 생성해서 넣는다. -> 꼭 필요한 시점까지 지연처리 한다는 것.
public class MyLogger { //요청이 와야 생성이 되는데, 아직 요청이 없어서 생성이 안된상태에서 달라고 하니까 에러가 뜬다.

    private String uuid;
    private String requestUrl;

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "] " + " [" + requestUrl + "] " + message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + this.uuid + "] request scope bean create " + this);
    }

    @PreDestroy
    public void close() {   //클라이언트에게 반환될때 호출된다.
        System.out.println("[" + this.uuid + "] request scope bean close " + this);
    }
}
