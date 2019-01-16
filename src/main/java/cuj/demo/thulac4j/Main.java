package cuj.demo.thulac4j;

import io.github.yizhiru.thulac4j.POSTagger;
import io.github.yizhiru.thulac4j.Segmenter;
import io.github.yizhiru.thulac4j.term.TokenItem;

import java.util.List;

/**
 * @Auther: cujamin
 * @Date: 2019/1/15 18:03
 * @Description:
 */
public class Main {
    public static void main(String[] args) {
        try{
            POSTagger pos = new POSTagger("models/model_c_model.bin", "models/model_c_dat.bin");
            // 中文分词
            String sentence = "哎我现在有点事情";
            List<String> words = Segmenter.segment(sentence);
            // [滔滔, 的, 流水, ，, 向着, 波士顿湾, 无声, 逝去]
            System.out.println(words);

            // 词性标注
            List<TokenItem> tokenItems = pos.tagging(sentence);
            System.out.println("tokenItems :"+tokenItems);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
