import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import filereader.FileInfoGetter;
import nlp.NounAnalyzer;

import java.util.ArrayList;

public class test {
    public static void main(String args[]){

        String path = "noun_test.xlsx";
        int index = 0;

        FileInfoGetter getter = new FileInfoGetter();
        ArrayList<String> lines = getter.readFile(path, index);

        NounAnalyzer analyzer = new NounAnalyzer();
        LexicalizedParser parser = analyzer.createParser();

        long beginTime = System.currentTimeMillis();
        int num = 0;
        for(String i:lines){
            System.out.print(num + " ");
            num += 1;
            String result = analyzer.analyze(i, parser);
            System.out.println(result);
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - beginTime);
    }
}
