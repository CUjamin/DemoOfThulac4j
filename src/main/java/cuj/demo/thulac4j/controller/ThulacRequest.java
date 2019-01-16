package cuj.demo.thulac4j.controller;

import java.util.List;

/**
 * @Auther: cujamin
 * @Date: 2019/1/16 19:04
 * @Description:
 */
public class ThulacRequest {
    private List<String> sentenceList;

    public List<String> getSentenceList() {
        return sentenceList;
    }

    public void setSentenceList(List<String> sentenceList) {
        this.sentenceList = sentenceList;
    }

    @Override
    public String toString() {
        return "ThulacRequest{" +
                "sentenceList=" + sentenceList +
                '}';
    }
}
