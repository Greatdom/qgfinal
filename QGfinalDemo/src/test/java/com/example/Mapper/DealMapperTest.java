package com.example.Mapper;

import com.example.entity.Deal;
import com.example.mapper.DealMapper;
import org.junit.Test;

public class DealMapperTest {
    @Test
    public void insert() {
        DealMapper dealMapper = new DealMapper();
        Deal deal = new Deal();
        deal.setPayMethod("货到付款");
        deal.setDealStatus("待发货");
        deal.setDealTime("未制作该板块");
        deal.setUserId(1);
        deal.setProductId(1);
        deal.setProductNum(1);
        int count = dealMapper.insert(deal);
        System.out.println(count);
    }
}
