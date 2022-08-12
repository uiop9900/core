package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class  BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig{

        @Bean
        //@Bean(initMethod = "init", destroyMethod = "close") //코드 수정이 불가한 외부라이브러리에도 사용가능 -> 메소드만 넣으면 되니까
        public NetworkClient networkClient() {
            //destroyMethod = "(infered)" 자동으로 close. shutdowm인거 알아서 찾아서 닫음, 이 설정이 싫다면 공백으로 남기면 된다.
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
