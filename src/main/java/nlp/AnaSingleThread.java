package nlp;

import data.DataMap;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;

import java.util.concurrent.atomic.AtomicInteger;

public class AnaSingleThread implements Runnable {

    private LexicalizedParser parser;
    private NounAnalyzer ana;
    private DataMap data;
    private int index;

    public AnaSingleThread(DataMap data, int index){
        this.ana = new NounAnalyzer();
        this.parser = ana.createParser();
        this.data = data;
        this.index = index;
    }

    public void run() {
        String temple;
        System.out.println(this.index);
        temple = this.data.getTempleList().get(this.index);
        String result = this.ana.analyze(temple, this.parser);
        this.data.getTempleMap().put(this.index, result);
    }
}
