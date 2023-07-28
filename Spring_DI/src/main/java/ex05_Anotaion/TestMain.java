package ex05_Anotaion;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TestMain {
	public static void main(String[] args) {
		// 해당 컨테이너가 설정정보를 읽어서 객체를 생성/관리하고 의존성 주입을 할 수 있다.
		ApplicationContext context = 
				new GenericXmlApplicationContext("ex05_Anotaion/config.xml");
		Service service = (Service) context.getBean("sic");
		service.biz();
	}
}
