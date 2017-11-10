package com.yincongyang.base.proxy.staticstate;

import com.yincongyang.base.proxy.Account;
import com.yincongyang.base.proxy.AccountImpl;

/**
 *
 * Created by yincongyang on 17/11/10.
 */
public class AccountProxyTest {
    public static void main(String[] args) {
        // AccountProxy为自己实现的代理类，可以发现，一个代理类只能为一个接口服务。
        Account account = new AccountImpl();
        AccountProxy proxy = new AccountProxy(account);
        proxy.queryAccount();
        proxy.updateAccount();
    }
}
