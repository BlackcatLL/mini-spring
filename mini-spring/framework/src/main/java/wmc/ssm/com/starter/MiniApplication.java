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
         * ��Ϊ��ܵ�����࣬һ�㴫��ΪӦ�õ�����࣬
         * ͨ�������Ϳ��Զ�λ����Ŀ�ĸ�Ŀ¼��
         * Ҳ���ܹ���ȡ��Ӧ����������Ϣ
         * Sting[] args�ò�������һ��ΪӦ�������Ĳ�������
         */
        {
            System.out.println("Hello mini-spring");
            TomcatServer tomcatServer = new TomcatServer(args);
            try {
                tomcatServer.startServer();
                List<Class<?>> classList = ClassScanner.scanClasses(cls.getPackage().getName());
                BeanFactory.initBean(classList);  //����beanFactory��ʼ��bean
                HandlerManager.resolveMappingHandler(classList);
                classList.forEach(it-> System.out.println(it.getName()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}
