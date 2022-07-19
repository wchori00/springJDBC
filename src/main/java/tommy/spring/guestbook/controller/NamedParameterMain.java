package tommy.spring.guestbook.controller;

import java.util.Date;
import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tommy.spring.guestbook.dao.NamedParamGuestMessageDao;
import tommy.spring.guestbook.vo.GuestMessage;

public class NamedParameterMain {
	private GuestMessage makeGuestMessage(String guestName, String message) {
		GuestMessage msg = new GuestMessage();
		msg.setGuestName(guestName);
		msg.setMessage(message);
		msg.setRegistryDate(new Date());
		return msg;
	}
	public static void main(String[] args) {
		String[] configLocations = new String[] { "applicationContext.xml" };
		AbstractApplicationContext context = new ClassPathXmlApplicationContext(configLocations);
		NamedParamGuestMessageDao dao = (NamedParamGuestMessageDao)context.getBean("namedParamGuestMessageDao");
		NamedParameterMain myTest = new NamedParameterMain();
		dao.insert(myTest.makeGuestMessage("ȫ�浿", "�氡�氡"));
		dao.insert(myTest.makeGuestMessage("javaline", "Hi, Hello"));
		dao.insert(myTest.makeGuestMessage("Spring", "�ȳ��ϼ���^^"));
		int count = dao.count();
		System.out.println("��ü�ۼ� : " + count);
		List<GuestMessage> list = dao.select(1, count);
		for(GuestMessage msg: list) {
			System.out.println(msg.getId() + " : " + msg.getGuestName() + " : " + msg.getMessage() + " : " + msg.getRegistryDate());
		}
		context.close();
	}
}
