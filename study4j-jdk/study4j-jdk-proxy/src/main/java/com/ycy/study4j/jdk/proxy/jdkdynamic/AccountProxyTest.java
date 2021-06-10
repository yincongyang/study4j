package com.ycy.study4j.jdk.proxy.jdkdynamic;

import com.ycy.study4j.jdk.proxy.Account;
import com.ycy.study4j.jdk.proxy.AccountImpl;

/**
 * Created by yincongyang on 17/11/10.
 */
public class AccountProxyTest {

  public static void main(String[] args) {
    // 下面使用JDK的代理类，一个代理就可以代理很多接口
    Account account1 = (Account) new AccountProxyFactory().bind(new AccountImpl());
    System.out.println(account1);
    account1.queryAccount();
  }
}
