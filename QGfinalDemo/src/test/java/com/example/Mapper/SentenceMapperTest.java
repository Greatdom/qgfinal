package com.example.Mapper;

import com.example.entity.Sentence;
import com.example.mapper.SentenceMapper;
import org.junit.Test;

import java.util.List;

public class SentenceMapperTest {
    @Test
    public void insert() {
        SentenceMapper sentenceMapper = new SentenceMapper();
        Sentence sentence = new Sentence();
        sentence = new Sentence();
        sentence.setUserId(1);
        sentence.setUserRole("ADMIN");
        sentence.setContent("我就是想发好人卡怎么了？！");
        sentence.setSentenceTime("未设计该模块");
        sentence.setSessionId(1);
        int count = sentenceMapper.insert(sentence);
        System.out.println(count);
    }
    @Test
    public void selectAll() {
        SentenceMapper sentenceMapper = new SentenceMapper();
        List<Sentence> sentences = sentenceMapper.selectAll();
        System.out.println(sentences);
    }
    @Test
    public void selectBySessionId() {
        SentenceMapper sentenceMapper = new SentenceMapper();
        List<Sentence> sentences = sentenceMapper.selectBySessionId(1);
        System.out.println(sentences);
    }
}
