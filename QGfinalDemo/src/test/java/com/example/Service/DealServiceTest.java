package com.example.Service;

import com.example.entity.Deal;
import com.example.service.DealService;
import com.example.util.TimeUtil;
import org.junit.Test;

public class DealServiceTest {
    @Test
    public void add() {
        Deal deal = new Deal();
        deal.setPayMethod("货到付款");
        deal.setDealStatus("待发货");
        deal.setDealTime(TimeUtil.getTime());
        deal.setUserId(1);
        deal.setProductId(1);
        deal.setProductNum(1);;
        DealService dealService = new DealService();
        int count = dealService.add(deal);
        System.out.println(count);
    }
}
