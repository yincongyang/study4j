package com.ycy.study4j.jdk.proxy.staticstate;

import com.ycy.study4j.jdk.proxy.Account;
import com.ycy.study4j.jdk.proxy.AccountImpl;

/**
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
