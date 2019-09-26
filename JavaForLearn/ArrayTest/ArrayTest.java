package ArrayTest;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArrayTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();

        List<String> list1=new ArrayList<String>();
        list1.add("hello");
        list1.add("world!");
        list.addAll(list1);
      //  for (String str:list1) {
       //     System.out.print(str+" ");
     //   }
        String str1 = "abc123sd156w86";
     /*使用String类提供的方法提取数字
        str1 = str1.trim();
        String str2 = "";
        if(str1 != null && !"".equals(str1)){
            for(int i=0;i<str1.length();i++){
                if(str1.charAt(i)>=48 && str1.charAt(i)<=57){
                    str2 += str1.charAt(i);
                }
            }
        }
        System.out.println(str2);*/

     //使用正则表达式提取数字
        String regEx="[^0-9]";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(str1);
        System.out.println(matcher.replaceAll("").trim());

    }
}
