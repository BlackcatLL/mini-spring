package CopyFileMethod;

import java.io.*;

/*
*复制单个文件
*y原文件路径：@param oldPath String 如：c:/fqf.txt
* 复制后路径：@param nwePath String 如: f:/fqf.txt
*@return boolean
 */
public class CopyFile {
    public static void main(String []args) throws Exception {
        File file1 = new File("D:/Save Files/Code/test.txt");
    //    File file2 = new File("D:/Save Files/Code/test1.txt");
        File file3 = new File("D:/Save Files/Sandick/test.txt");
        //指定读写格式为gbk
        BufferedReader br1=new BufferedReader(new InputStreamReader(new FileInputStream(file1),"utf-8"));
      //  BufferedReader br2=new BufferedReader(new InputStreamReader(new FileInputStream(file2),"utf-8"));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file3),"utf-8"));
        try{
            String temp;
            while ((temp=br1.readLine())!=null){
                bw.write(temp);               //每次写入一行
                bw.newLine();                 //换行
                bw.flush();                   //刷新缓存
                System.out.println("文件内容："+temp);
                temp=br1.readLine();
                break;
            }
      /*      while ((temp=br2.readLine())!=null){
                bw.write(temp);               //每次写入一行
                bw.newLine();                 //换行
                bw.flush();                   //刷新缓存
                System.out.println("文件内容："+temp);
                temp=br2.readLine();
                break;
            }*/
        }catch (Exception e){
            e.printStackTrace();;
        }finally {
            br1.close();
         //   br2.close();
            bw.close();
        }
    }
}