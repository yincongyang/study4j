package com.ycy.study4j.jdk.proxy.cglibdynamic;

import com.ycy.study4j.jdk.proxy.Account;
import com.ycy.study4j.jdk.proxy.AccountImpl;
import com.ycy.study4j.jdk.proxy.Person;

/**
 * Created by yincongyang on 17/11/10.
 */
public class AccountCglibProxyTest {

  public static void main(String[] args) {
    // 下面是用cglib的代理
    // 1.支持实现接口的类
    Account account2 = (Account) new AccountCglibProxyFactory().getInstance(new AccountImpl());
    account2.updateAccount();

    // 2.支持未实现接口的类
    Person person = (Person) new AccountCglibProxyFactory().getInstance(new Person());
    System.out.println(person);
    person.show();
  }
}
