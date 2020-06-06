package nlp;

import edu.stanford.nlp.parser.lexparser.LexicalizedParser;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class AnaThread implements Runnable {

    private int start;
    private int end;
    private ConcurrentHashMap<Integer, String> map;
    private ArrayList<String> list;

    public AnaThread(int start, int end, ConcurrentHashMap<Integer, String> map, ArrayList<String> list){
        this.start = start;
        this.end = end;
        this.map = map;
        this.list = list;
    }

    public void run() {
        NounAnalyzer na = new NounAnalyzer();
        LexicalizedParser parser = na.createParser();
        int now = this.start;
        String temple;
        while(now < this.end){
            synchronized(this) {
                // System.out.println(now);
                temple = list.get(now);
                now += 1;
            }
            String result = na.analyze(temple, parser);
            map.put(now - 1, result);
        }
    }
}
