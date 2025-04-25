package com.example.srevlet.Quartz;

import com.example.common.enums.ProductStatusEnum;
import com.example.entity.Product;
import com.example.mapper.ProductMapper;
import com.example.service.SentenceService;
import com.example.util.TimeUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.List;

public class PublishProduct implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String hindTime= TimeUtil.getTime();
        ProductMapper productMapper = new ProductMapper();
        List<Product> productList = productMapper.selectNotPublished();
        for (Product product : productList) {
            System.out.println(product);
            if(TimeUtil.IsHeadTimeBefore(product.getPublishTime(),hindTime)){
                product.setPublishStatus(ProductStatusEnum.PUBLISHED.getValue());
                productMapper.update(product);
                SentenceService sentenceService = new SentenceService();
                String content="您于["+product.getPublishTime()+"]成功定时上架商品["+product.getName()+"]";
                sentenceService.addSystemToUser(product.getUserId(),content);
            }
        }
    }
}
