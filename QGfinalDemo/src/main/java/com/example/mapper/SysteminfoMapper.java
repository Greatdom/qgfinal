package com.example.mapper;

import com.example.connPool.DataSourceManager;
import com.example.entity.Systeminfo;
import com.example.util.CRUDUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SysteminfoMapper {
    public Systeminfo initial() {
        Systeminfo systeminfo=Systeminfo.getInstance();
        String sql = "select * from systeminfo";

        try {
            Connection conn = DataSourceManager.getConn();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String role=rs.getString("role");
                int adminNum=rs.getInt("admin_num");
                int userNum=rs.getInt("user_num");
                int productNum=rs.getInt("product_num");
                double totalMoney=rs.getDouble("total_money");
                int reportNum=rs.getInt("report_num");
                int sessionNum=rs.getInt("session_num");
                int dealNum=rs.getInt("deal_num");
                int commentNum=rs.getInt("comment_num");
                systeminfo.setId(id);
                systeminfo.setRole(role);
                systeminfo.setAdminNum(adminNum);
                systeminfo.setUserNum(userNum);
                systeminfo.setProductNum(productNum);
                systeminfo.setTotalMoney(totalMoney);
                systeminfo.setReportNum(reportNum);
                systeminfo.setSessionNum(sessionNum);
                systeminfo.setDealNum(dealNum);
                systeminfo.setCommentNum(commentNum);
                systeminfo.setInitialized(true);
            }
            rs.close();
            DataSourceManager.close(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return systeminfo;
    }
    public int update(Systeminfo systeminfo) {
        if(systeminfo==null){
            return 0;
        }
        if(systeminfo.isInitialized()){
            String updateSql="UPDATE systeminfo SET admin_num=?,user_num=?,product_num=?,total_money=?,report_num=?,session_num=?,deal_num=?,comment_num=?";
            return CRUDUtils.update(updateSql,systeminfo.getAdminNum(),systeminfo.getUserNum(),systeminfo.getProductNum(),systeminfo.getTotalMoney(),systeminfo.getReportNum(),systeminfo.getSessionNum(),systeminfo.getDealNum(),systeminfo.getCommentNum());
        }else{
            return 0;
        }
    }
}
