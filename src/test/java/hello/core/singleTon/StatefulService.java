package hello.core.singleTon;

public class StatefulService {
    //이런식으로 하지말고 지역변수 담는것으로 변환한다. -> return price 해서 각 사용자마다 다른 지역변수에 값을 담는다.
     private int price;

     public void order(String name, int price) {
         System.out.println("name = " + name + " price = " + price);
         this.price = price;
     }

    public int getPrice() {
        return price;
    }
}
