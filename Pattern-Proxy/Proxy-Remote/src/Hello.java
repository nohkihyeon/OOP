import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 2020년도 2학기
 * @author 김상진
 * 프록시 패턴: 원격 프록시
 * 대상 객체 interface
 */
public interface Hello extends Remote {
	String sayHello(String name) throws RemoteException;
}