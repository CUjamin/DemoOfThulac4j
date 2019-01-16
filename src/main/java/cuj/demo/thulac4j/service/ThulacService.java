package cuj.demo.thulac4j.service;

import io.github.yizhiru.thulac4j.term.TokenItem;

import java.util.List;

/**
 * @Auther: cujamin
 * @Date: 2019/1/16 18:53
 * @Description:
 */
public interface ThulacService {
    List<TokenItem> tagging(String sentence);
}
