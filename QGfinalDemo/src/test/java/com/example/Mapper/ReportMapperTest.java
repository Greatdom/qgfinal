package com.example.Mapper;

import com.example.entity.Report;
import com.example.mapper.ReportMapper;
import com.example.util.CRUDUtils;
import com.example.util.TimeUtil;
import org.junit.Test;

import java.util.List;

public class ReportMapperTest {
    @Test
    public void insert() {
        ReportMapper reportMapper = new ReportMapper();
        Report report = new Report();
        report.setReportTime(TimeUtil.getTime());
        report.setReportType("举报商家");
        report.setContent("他老是发好人卡我受不了了,快BAN掉他!");
        report.setResult("待处理");
        report.setUserId(1);
        report.setPointerId(1);
        int count = reportMapper.insert(report);
        System.out.println(count);
    }
    @Test
    public void selectAll(){
        ReportMapper reportMapper = new ReportMapper();
        List<Report> reports = reportMapper.selectAll();
        System.out.println(reports);
    }
    @Test
    public void selectByFlied(){
        ReportMapper reportMapper = new ReportMapper();
        Report report = new Report();
        report.setResult("待处理");
        report.setUserId(1);
        List<Report> reports = reportMapper.selectByFlied(report);
        System.out.println(reports);
    }
    @Test
    public void selectByTypeAndPointerId(){
        ReportMapper reportMapper = new ReportMapper();
        Report report = new Report();
        report.setReportType("用户");
        report.setPointerId(1);
        Report report2 = reportMapper.selectByTypeAndPointerId(report);
        System.out.println(report2);
    }
}
