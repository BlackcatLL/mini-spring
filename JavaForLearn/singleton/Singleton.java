package singleton;

public class Singleton {
    private volatile static Singleton  singleton;

    private Singleton(){}

    public static Singleton getInstance() {
        if (getInstance() == null) {
            synchronized (Singleton.class) {
                if (getInstance() == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
