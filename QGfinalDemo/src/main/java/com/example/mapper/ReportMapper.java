package com.example.mapper;

import com.example.entity.Report;
import com.example.util.CRUDUtils;

import java.util.List;

public class ReportMapper {
    public int insert(Report report) {
        String reportTime=report.getReportTime();
        String reportType=report.getReportType();
        String content=report.getContent();
        String result=report.getResult();
        Integer userId=report.getUserId();
        Integer pointerId=report.getPointerId();
        String insertSql="insert into report (report_time,report_type,content,result,user_id,pointer_id) values(?,?,?,?,?,?)";
        return CRUDUtils.insert(insertSql,reportTime,reportType,content,result,userId,pointerId);
    }
    public List<Report> selectAll(){
        String selectSql="select * from report";
        return CRUDUtils.queryForList(Report.class,selectSql);
    }
    public List<Report> selectByFlied(Report report){
        if(report==null){
            return null;
        }
        String reportType=report.getReportType();
        String result=report.getResult();
        Integer userId=report.getUserId();
        Integer pointerId=report.getPointerId();
        if(result!=null){
            if(reportType!=null){
                String selectSql="select * from report where report_type=? and result=?";
                return CRUDUtils.queryForList(Report.class,selectSql,reportType,result);
            }else if(userId!=null){
                String selectSql="select * from report where user_id=? and result=?";
                return CRUDUtils.queryForList(Report.class,selectSql,userId,result);
            }else if(pointerId!=null){
                String selectSql="select * from report where pointer_id=? and result=?";
                return CRUDUtils.queryForList(Report.class,selectSql,pointerId,result);
            }else{
                String selectSql="select * from report where result=?";
                return CRUDUtils.queryForList(Report.class,selectSql,result);
            }
        }else{
            if(reportType!=null){
                String selectSql="select * from report where report_type=?";
                return CRUDUtils.queryForList(Report.class,selectSql,reportType);
            }else if(userId!=null){
                String selectSql="select * from report where user_id=?";
                return CRUDUtils.queryForList(Report.class,selectSql,userId);
            }else if(pointerId!=null){
                String selectSql="select * from report where pointer_id=?";
                return CRUDUtils.queryForList(Report.class,selectSql,pointerId);
            }else{
                return null;
            }
        }
    }
    public Report selectByTypeAndPointerId(Report report){
        if(report==null){
            return null;
        }
        String reportType=report.getReportType();
        Integer pointerId=report.getPointerId();
        String selectSql="select * from report where report_type=? and pointer_id=?";
        return CRUDUtils.queryForObject(Report.class,selectSql,reportType,pointerId);
    }
    public Report selectById(int id){
        String selectSql="select * from report where id=?";
        return CRUDUtils.queryForObject(Report.class,selectSql,id);
    }
    public int changeResult(Report report){
        String result=report.getResult();
        Integer id=report.getId();
        String updateSql="update report set result=? where id=?";
        return CRUDUtils.update(updateSql,result,id);
    }

}
