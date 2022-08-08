package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean { //member는 자바 객체라 spring이 관리하지 않는다.
        
        @Autowired(required = false)
        public void setNoBean1(Member member) { //주입대상이 없으면 메소드 자체가 호출이 안된다.
            System.out.println("member = " + member);
        }

        @Autowired
        public void setNoBean2(@Nullable Member member2) {  //호출은 되나, member2 = null
            System.out.println("member2 = " + member2);
        }

        @Autowired
        public void setNoBean3(Optional<Member> member3) {  //member3 = Optional.empty
            System.out.println("member3 = " + member3);
        }
    }
}
