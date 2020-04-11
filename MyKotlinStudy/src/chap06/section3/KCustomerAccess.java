package chap06.section3;

public class KCustomerAccess {
    public static void main(String[] args) {
        // 코틀린의 클래스의 컴패니언 객체에 접근
        System.out.println(KCoustomer.strLEVEL);
        KCoustomer.login(); // 애노테이션을 사용할 때 접근 방법
        KCoustomer.Companion.login(); // 애노테이션을 사용하지 않을 때 접근 방법

        // KJob에 대한 객체 생성 후 접근
        KJob kjob = KCoustomer.JOB;
        System.out.println(kjob.getStrtitle());

        // KCoustomer를 통한 접근
        KCoustomer.JOB.setStrtitle("Accountant");
        System.out.println(KCoustomer.JOB.getStrtitle());
    }
}
//
