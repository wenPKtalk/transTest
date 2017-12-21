package common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;

public class GenerateData {

    public static String getData(){
        StringBuilder result = new StringBuilder();

        try{
            URL url = ClassLoader. getSystemClassLoader().getResource("data.txt");
            String path = url.getPath().substring(1);
            String data = null;
            BufferedReader br = new BufferedReader(new FileReader(path));//构造一个BufferedReader类来读取文件

            while((data = br.readLine())!=null){//使用readLine方法，一次读一行
                result.append(data);
            }
            br.close();

        }catch(Exception e){
            e.printStackTrace();
        }
        return String.valueOf(result);

    }
}
