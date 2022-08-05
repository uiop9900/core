package hello.core.singleTon;

public class SingleTonService {

    private static final SingleTonService instance = new SingleTonService();

    public static SingleTonService getInstance() {
        return instance;
    }

    private SingleTonService() {    //다른곳에서 singleTonService를 생성하지 못하도록 막는다. -> 완벽한 싱글톤
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
