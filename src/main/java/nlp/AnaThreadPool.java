package nlp;

import data.DataMap;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AnaThreadPool {
    private ThreadPoolExecutor threadPool;
    private DataMap data;

    public AnaThreadPool(DataMap data){
        this.data = data;
        threadPool = new ThreadPoolExecutor(3, 5, 5,
                TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(100), new ThreadPoolExecutor.CallerRunsPolicy());
    }

    public void startThreadPool(){
        int simpleNum = 20;
        int now = 0;
        System.out.println("pool start");
        while (now < this.data.getTempleList().size()){
            // AnaSingleThread singleThread = new AnaSingleThread(data, i);
            AnaThread t;
            if ((now + simpleNum)<this.data.getTempleList().size())
                t = new AnaThread(now, now + simpleNum, this.data.getTempleMap(), this.data.getTempleList());
            else
                t = new AnaThread(now, this.data.getTempleList().size(), this.data.getTempleMap(), this.data.getTempleList());
            this.threadPool.execute(t);
            now += simpleNum;
        }
        this.threadPool.shutdown();
    }

    public boolean isOver(){
        // 判断是否完成线程任务
        int count = this.threadPool.getActiveCount();
        return count < 1 ;
    }

}
