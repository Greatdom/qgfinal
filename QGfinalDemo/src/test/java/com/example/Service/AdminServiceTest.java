package com.example.Service;

import com.example.entity.Account;
import com.example.service.AdminService;
import org.junit.Test;

public class AdminServiceTest {
    @Test
    public void add() {
        AdminService adminService = new AdminService();
        Account account = new Account();
        account.setUsername("admin3");
        account.setPassword("admin3");
        int count = adminService.add(account);
        System.out.println(count);
    }
}
