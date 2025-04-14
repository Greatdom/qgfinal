package com.example.mapper;

import com.example.entity.Deal;
import com.example.util.CRUDUtils;

import java.util.List;

public class DealMapper {
    public int insert(Deal deal) {
        String payMethod = deal.getPayMethod();
        String dealStatus = deal.getDealStatus();
        String dealTime = deal.getDealTime();
        Integer userId = deal.getUserId();
        Integer productId = deal.getProductId();
        Integer productNum=deal.getProductNum();
        String insertSql=
                "INSERT INTO deal (pay_method,deal_status,deal_time,user_id,product_id,product_num)" +
                        " VALUES (?, ?, ?, ?, ?, ?)";
        return CRUDUtils.insert(insertSql,payMethod,dealStatus,dealTime,userId,productId,productNum);
    }
    public List<Deal> selectByUser(Integer id) {
        String selectSql="SELECT * FROM deal WHERE user_id=?";
        return CRUDUtils.queryForList(Deal.class,selectSql,id);
    }
    public List<Deal> selectByProduct(Integer id) {
        String selectSql="SELECT * FROM deal WHERE product_id=?";
        return CRUDUtils.queryForList(Deal.class,selectSql,id);
    }
    public List<Deal> selectAll(){
        String selectSql="SELECT * FROM deal";
        return CRUDUtils.queryForList(Deal.class,selectSql);
    }
    public Deal selectById(Integer id) {
        String selectSql="SELECT * FROM deal WHERE id=?";
        return CRUDUtils.queryForObject(Deal.class,selectSql,id);
    }
    public int ChangeDealStatus(Deal deal) {
        if(deal==null||deal.getDealStatus()==null){
            return 0;
        }
        String dealStatus=deal.getDealStatus();

        String updateSql="UPDATE deal SET deal_status=? WHERE id=?";
        return CRUDUtils.update(updateSql,dealStatus,deal.getId());
    }
}
