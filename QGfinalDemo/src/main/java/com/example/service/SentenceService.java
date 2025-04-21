package com.example.service;

import com.example.entity.Sentence;
import com.example.mapper.SentenceMapper;

import java.util.List;

public class SentenceService {
    SentenceMapper sentenceMapper = new SentenceMapper();
    public int add(Sentence sentence) {
        return sentenceMapper.insert(sentence);
    }
    public List<Sentence> selectAll(){
        return sentenceMapper.selectAll();
    }
    public List<Sentence> selectBySessionId(Integer sessionId){
        return sentenceMapper.selectBySessionId(sessionId);
    }
}
