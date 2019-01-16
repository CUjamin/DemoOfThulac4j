package cuj.demo.thulac4j.service;

import io.github.yizhiru.thulac4j.POSTagger;
import io.github.yizhiru.thulac4j.term.TokenItem;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @Auther: cujamin
 * @Date: 2019/1/16 18:54
 * @Description:
 */
@Service
@Log4j
public class ThulacServiceImpl implements ThulacService {

    private POSTagger posTagger = null;

    public ThulacServiceImpl(){
        try{
            posTagger = new POSTagger("models/model_c_model.bin", "models/model_c_dat.bin");
        }catch (IOException e){
            log.info("posTagger init Exception ",e);
        }
    }


    @Override
    public List<TokenItem> tagging(String sentence) {

        return posTagger.tagging(sentence);
    }
}
