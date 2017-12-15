package com.yincongyang.base.proxy.staticstate;

import com.yincongyang.base.proxy.Account;

/**
 * TODO：挪到design设计模式说明中
 *
 * Created by yincongyang on 17/11/10.
 */
public class AccountProxy implements Account {

    private Account account;

    public AccountProxy(Account account) {
        super();
        this.account = account;
    }

    @Override
    public void queryAccount() {
        System.out.println("代理before");
        account.queryAccount();
        System.out.println("代理after");
    }

    @Override
    public void updateAccount() {
        System.out.println("代理before");
        account.updateAccount();
        System.out.println("代理after");
    }

}
