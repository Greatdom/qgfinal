package com.example.service;

import com.example.entity.Report;
import com.example.entity.Systeminfo;
import com.example.mapper.ReportMapper;
import com.example.mapper.SysteminfoMapper;
import com.example.util.CRUDUtils;

import java.util.List;

public class ReportService {
    ReportMapper reportMapper=new ReportMapper();
    public int add(Report report) {
        int count=0;
        count=reportMapper.insert(report);
        if(count>0){
            Systeminfo systeminfo = Systeminfo.getInstance();
            systeminfo.setReportNum(systeminfo.getReportNum()+1);
            SysteminfoMapper systeminfoMapper = new SysteminfoMapper();
            systeminfoMapper.update(systeminfo);
        }
        return count;
    }
    public List<Report> selectAll() {
        return reportMapper.selectAll();
    }
    public List<Report> selectByField(Report report) {
        return reportMapper.selectByFlied(report);
    }
    public Report selectById(int id) {
        return reportMapper.selectById(id);
    }
    public Report selectByTypeAndPointerId(String reportType, Integer pointerId) {
        Report report = new Report();
        report.setReportType(reportType);
        report.setPointerId(pointerId);
        return reportMapper.selectByTypeAndPointerId(report);
    }
    public int changeResult(Report report) {
        return reportMapper.changeResult(report);
    }
}
