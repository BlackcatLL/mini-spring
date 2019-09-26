

import java.util.*;


class MainTest{
    public static void main(String[] args) {
        String toTest = "what's blue+yellow?/green";
        String[] result = toTest.split("/");  //  split( 分隔标知符)
        for(String str:result){
            System.out.println(str);
        }
    }

}

