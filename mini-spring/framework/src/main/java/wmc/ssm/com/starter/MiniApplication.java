package wmc.ssm.com.starter;

import org.apache.catalina.LifecycleException;
import wmc.ssm.com.beans.BeanFactory;
import wmc.ssm.com.core.ClassScanner;
import wmc.ssm.com.web.handler.HandlerManager;
import wmc.ssm.com.web.server.TomcatServer;

import java.util.List;
import java.util.logging.Handler;

public class MiniApplication {
        public static void run(Class<?> cls,String[] args)
        /**
         * 作为框架的入口类，一般传参为应用的入口类，
         * 通过入口类就可以定位到项目的根目录，
         * 也就能够获取到应用入口类的信息
         * Sting[] args该参数数组一般为应用入口类的参数数组
         */
        {
            System.out.println("Hello mini-spring");
            TomcatServer tomcatServer = new TomcatServer(args);
            try {
                tomcatServer.startServer();
                List<Class<?>> classList = ClassScanner.scanClasses(cls.getPackage().getName());
                BeanFactory.initBean(classList);  //利用beanFactory初始化bean
                HandlerManager.resolveMappingHandler(classList);
                classList.forEach(it-> System.out.println(it.getName()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}
