package com.ycy.study4j.jdk.proxy;

/**
 * Created by yincongyang on 17/11/10.
 */
public class AccountImpl implements Account {

    @Override
    public void queryAccount() {
        System.out.println("查看账户");
    }

    @Override
    public void updateAccount() {
        System.out.println("修改账户");
    }

}
