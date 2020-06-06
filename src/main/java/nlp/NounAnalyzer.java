package nlp;

import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.trees.EnglishGrammaticalStructure;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TypedDependency;

import java.util.Collection;

// 利用stanford parse进行处理，获取名词集合
public class NounAnalyzer {

    private String model="edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz";

    public LexicalizedParser createParser(){
        LexicalizedParser lp = LexicalizedParser.loadModel(model);
        return lp;
    }

    public String analyze(String temple, LexicalizedParser parser){
        // 利用parser获取词性并输出temple中的名词
        // 用逗号分割
        StringBuffer sb = new StringBuffer();

        Tree t = parser.parse(temple);
        //英语语法结构
        EnglishGrammaticalStructure es=new EnglishGrammaticalStructure(t);
        //依存关系,获取td关系组合
        Collection<TypedDependency> tdl=es.typedDependencies();

        for(int i=0;i<tdl.size();i++){
            TypedDependency td=(TypedDependency)tdl.toArray()[i];
            String word=td.dep().word();
            String tag=td.dep().tag();
            if(tag.contains("NN")){
                //名词
                sb.append(word).append(",");
            }
        }

        // return sb.substring(0, sb.length() - 1);
        return sb.toString();
    }
}
