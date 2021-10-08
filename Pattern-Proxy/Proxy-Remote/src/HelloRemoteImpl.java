import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 2020년도 2학기
 * @author 김상진
 * 프록시 패턴: 원격 프록시
 * 원격 프록시 객체
 */
@SuppressWarnings("serial")
public class HelloRemoteImpl extends UnicastRemoteObject implements Hello {

	public HelloRemoteImpl() throws RemoteException {}
	@Override
	public String sayHello(String name) throws RemoteException {
		return "원격에서 전달되는 황홀한 문자열!!! "+name;
	}

}
