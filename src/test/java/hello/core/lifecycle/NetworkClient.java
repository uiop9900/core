package hello.core.lifecycle;


import javax.annotation.PostConstruct;  //javax -> 자바표준 -> 스프링이 아닌 다른 컨테이너에서도 잘 동작함
import javax.annotation.PreDestroy;

public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작 시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call " + url + " message = " + message);
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close  " + url);
    }

    @PostConstruct
    public void init() throws Exception { //의존관계 주입이 끝나고 호출한다.
        connect();
        call("초기화 연결 메세지");
    }

    @PreDestroy
    public void close() throws Exception {
        disconnect();
    }
}
