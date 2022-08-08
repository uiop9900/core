package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

    //기존에 내가 하던 방식, @Autowired로 부르기 -> new를 통한 순수한 자바코드에서 쓸수가 없다. -> 쓰지않는다. test/Config에서만 쓴다.
    private final MemberRepository memberRepository;
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
   // private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    private final DiscountPolicy discountPolicy;  //이렇게 선언하면 비어있기때문에 Nullpoint Exception이 터진다.

    //생성자가 하나이면 @Autowired 생략가능하다.
    @Autowired  //생성자로 호출하면 딱 1번만 호출됨 -> 불변, 필수 :: 항상 한계점을 만들고 촘촘하게 제어하는게 좋은 코드이다.
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);    //둘의 역할이 완벽하게 분리됨 -> 회원은 가격에 대해 아예 모른다.
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice); //order객체를 생성해서 반환한다.
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
