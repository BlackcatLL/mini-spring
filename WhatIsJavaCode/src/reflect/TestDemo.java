package reflect;

public class TestDemo {


    public static void main(String[] args) {
        StringBuffer stringBuffer = new StringBuffer("spring");
        stringBuffer = stringBuffer.delete(3,6).insert(2,"umme").delete(1,2);
      //  System.out.println(stringBuffer);
        stringBuffer = stringBuffer.append("567");
     //   System.out.println(stringBuffer);
        String string = "123456qwer";
        String[] strings = new String[3];
        strings[0] = string.replace("2","3");
        strings[1]  = string.substring(3,5);
        strings[2]  = string.toUpperCase();
       for(int i=0;i<=2;i++){
           System.out.println(strings[i]);
       }
    }
}
