package wmc.ssm.com.core;

import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassScanner {
    public  static List<Class<?>> scanClasses(String packageName) throws IOException, ClassNotFoundException {
        List<Class<?>> classList = new ArrayList<>();
        String path = packageName.replace(".","/");
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Enumeration<URL> resources = classLoader.getResources(path);
        while(resources.hasMoreElements())
        {
            URL resource = resources.nextElement();
            //处理资源类型是JAR包的情况
            if(resource.getProtocol().contains("jar")){
                JarURLConnection jarURLConnection = (JarURLConnection)resource.openConnection();
                String jarFilePath = jarURLConnection.getJarFile().getName();
                classList.addAll(getClassesFormJar(jarFilePath,path));
            }else{
                //TODO
            }
        }
        return classList;
    }

    private static List<Class<?>> getClassesFormJar(String jarFilePath,String Path) throws IOException, ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();  //初始化容器来存储jar包
        JarFile jarFile = new JarFile(jarFilePath);
        Enumeration<JarEntry> jarEntries = jarFile.entries();
        while(jarEntries.hasMoreElements()){   //依次处理jar包里的JarEntry文件
            JarEntry jarEntry = jarEntries.nextElement();
            String entryName = jarEntry.getName();  //获取JarEntry文件名字 如wmc/ssm/com/test/Test.class
            if(entryName.startsWith(Path)&&entryName.endsWith(".class")){
                String classFullName = entryName.replace("/",".").substring(0,entryName.length()-6);
                classes.add(Class.forName(classFullName));
            }
        }
        return classes;
    }
}
