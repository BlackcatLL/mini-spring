package LintCode;

public class Problem3 {

    /**
     * @param k: An integer
     * @param n: An integer
     * @return: An integer denote the count of digit k in 1..n
     */
    public int digitCounts(int k, int n) {
        // write your code here
        int count=0,singlecount=0;
        int p=n;
        int num=0;//表示处理数字的位数 num=0表示处理个位 1表示十位等
        if(k==0) return (n/10)+1;
        if(n==0) return 0;
        while(p!=0){
            if((p%10)>k) singlecount=(int) ((p/10+1)*(Math.pow(10, (double)num)));
            if((p%10)==k) singlecount=(int) ((p/10)*(Math.pow(10, (double)num))+(n%((Math.pow(10, (double)num))))+1);
            if((p%10)<k)  singlecount=(int) ((p/10)*(Math.pow(10, (double)num)));
            count+=singlecount;
            p=p/10;
            num++;
        }
        return count;
    }


}
