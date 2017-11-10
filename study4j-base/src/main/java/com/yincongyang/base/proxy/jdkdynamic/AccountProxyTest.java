package com.yincongyang.base.proxy.jdkdynamic;

import com.yincongyang.base.proxy.Account;
import com.yincongyang.base.proxy.AccountImpl;

/**
 *
 * Created by yincongyang on 17/11/10.
 */
public class AccountProxyTest {
    public static void main(String[] args) {
        // 下面使用JDK的代理类，一个代理就可以代理很多接口
        Account account1 = (Account)new AccountProxyFactory().bind(new AccountImpl());
        System.out.println(account1);
        account1.queryAccount();
    }
}
