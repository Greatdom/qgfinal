package com.example.service;

import com.example.entity.Deal;
import com.example.mapper.DealMapper;

import java.util.List;

public class DealService {
    DealMapper dealMapper = new DealMapper();
    public int add(Deal deal) {
        return dealMapper.insert(deal);
    }
    public List<Deal> selectByUser(int id){
        return dealMapper.selectByUser(id);
    }
    public List<Deal> selectByProduct(int id){
        return dealMapper.selectByProduct(id);
    }
    public List<Deal> selectAll(){
        return dealMapper.selectAll();
    }
    public Deal selectById(int id){
        return dealMapper.selectById(id);
    }
}
