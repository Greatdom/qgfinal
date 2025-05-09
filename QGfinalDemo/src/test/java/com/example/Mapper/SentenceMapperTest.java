package com.example.Mapper;

import com.example.entity.Sentence;
import com.example.mapper.SentenceMapper;
import com.example.util.TimeUtil;
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
        sentence.setSentenceTime(TimeUtil.getTime());
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
        List<Sentence> sentences = sentenceMapper.selectBySessionId(3);
        System.out.println(sentences);
    }
}
