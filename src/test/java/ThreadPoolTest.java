import data.DataMap;
import filereader.FileInfoGetter;
import nlp.AnaThreadPool;

import java.util.ArrayList;

public class ThreadPoolTest {
    public static void main(String args[]){
        String path = "noun_test.xlsx";
        int index = 0;

        DataMap map = new DataMap();
        ArrayList<String> lines = new FileInfoGetter().readFile(path, index);
        map.setTempleList(lines);

        long beginTime = System.currentTimeMillis();
        AnaThreadPool threadPool = new AnaThreadPool(map);
        threadPool.startThreadPool();

        while (!threadPool.isOver()){
            // 手动join
            // do nothing
        }

        long endTime = System.currentTimeMillis();

        for(int i=0;i<map.getTempleList().size();i++){
            System.out.println(i+":"+map.getTempleMap().get(i));
        }

        System.out.println("time:" + (endTime - beginTime));
    }
}
