import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2020년도 2학기 
 * @author 김상진
 * 관찰자 패턴: java.beans.PropertyChangeSupport 활용한 관찰자 패턴의 구현
 * @file NewsAgency.java
 * 관찰 대상
 */
public class NewsAgency {
	private String news;

	private PropertyChangeSupport support
		= new PropertyChangeSupport(this);
	public NewsAgency() {}
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }
 
    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl); // equals 주의 
    }
 
    public void setNews(String value) {
        support.firePropertyChange("news", this.news, value);
        this.news = value;
    }
}
