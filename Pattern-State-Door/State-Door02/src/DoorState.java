
public interface DoorState {
	//void open();
	default void open() {}
    void close();
    void lock();
    void unlock();
}
