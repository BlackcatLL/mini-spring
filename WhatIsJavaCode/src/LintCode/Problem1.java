package LintCode;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 计算一个数的阶乘，输出它的末尾有几个0
 */
public class Problem1 {
    /*单纯用阶乘结果就算出的结果判断尾部有多少个0
    public long trailingZeros(long n) {
        // write your code here, try to do it without arithmetic operators.
        BigInteger multi = new BigInteger(String.valueOf(1));
        for(int i=1;i<=n;i++){
            BigInteger value = new BigInteger(String.valueOf(i));
            multi=multi.multiply(value);
        }
        Character a = '0';
        StringBuilder stringBuilder = new StringBuilder(multi.toString());
      long zeroNum = 0;
        for(int i=stringBuilder.length()-1;i>=0;i--){
            if(a.equals(stringBuilder.charAt(i))){
                zeroNum=zeroNum+1;
            }
            else
                break;
        }
        System.out.println(multi);
        return zeroNum;
    }

    public static void main(String[] args) {
        Problem1 problem1 = new Problem1();
        System.out.println(problem1.trailingZeros(100));
    }*/

    /**
     * 每个5的倍数与偶数相乘都会增加一个0，然后25*4会增加2个，125*8会增加3个
     * 即5的一次方 * 2的一次方 增加一个 0 ；
     * 5的2次方 * 2的2次方 增加2个0；
     * 5的3次方 * 2的3次方 增加3个0；
     * 依次类推；
     * @param n
     * @return
     */

    public long trailingZeros(long n) {
        //5!  里有一个5
        //10! 里有 10 / 5 一共有两个5
        //15! 里有 15 / 10 / 5 一共有三个5

        //必须初始化
        long count = 0;

        //每一轮除5的商累加
        while(n > 5){
            count = count + n / 5;
            n = n / 5;
        }
        return count;
    }
}
