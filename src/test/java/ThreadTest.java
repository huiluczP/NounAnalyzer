import com.sun.org.apache.bcel.internal.generic.NEW;
import data.DataMap;
import filereader.FileInfoGetter;
import nlp.AnaThread;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Map;

public class ThreadTest {
    public static void main(String[] args){
        String path = "noun_test.xlsx";
        int index = 0;

        DataMap map = new DataMap();
        ArrayList<String> lines = new FileInfoGetter().readFile(path, index);
        map.setTempleList(lines);

        //多条线程分配任务执行
        int num = 3;
        int now = 0;
        int piece = map.getTempleList().size() / num;
        ArrayList<Thread> threads = new ArrayList<Thread>();
        while(now < map.getTempleList().size()) {
            AnaThread t;
            if ((now+ piece)<map.getTempleList().size())
                t = new AnaThread(now, now + piece, map.getTempleMap(), map.getTempleList());
            else
                t = new AnaThread(now, map.getTempleList().size(), map.getTempleMap(), map.getTempleList());
            System.out.println(now + "-" + (now+piece));
            now = now + piece;
            Thread thread = new Thread(t);
            threads.add(thread);
        }

        long beginTime = System.currentTimeMillis();
        System.out.println("start threads");
        for(Thread t:threads){
            t.start();
        }

        try {
            for(Thread t:threads){
                t.join();
            }
            System.out.println("threads over");
            long endTime = System.currentTimeMillis();
            System.out.println("time:" + (endTime - beginTime));

            for (int i =0; i< lines.size();i++){
                System.out.print(i+":");
                System.out.println(map.getTempleMap().get(i));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
