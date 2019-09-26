package LintCode;

/**
 * 位运算实现A+B问题
 */
public class Problem2 {
    /**
     *
     * @param num1
     * @param num2
     * @return
     */

    /**
     * 11,13-----1011    1101
     *         x = 11^13   0110
     *         y = 11&13   1001
     *         y 进位操作10010
     *         x = x^y
     *         y = x&y
     *         再进位直到 y = 0
     *
     * +        11000
     * @param num1
     * @param num2
     * @return
     */

    public static int sub(int num1, int num2) {
       int x = num1 ^ num2;
       int y = num1 & num2;

       if(y!=0){
           y=y<<1;
           int temp = x ;
           x= x^y;
           y=temp&y;
       }
        return x;
    }

    public static void main(String[] args) {
        System.out.println(sub(13,7));
    }
}
