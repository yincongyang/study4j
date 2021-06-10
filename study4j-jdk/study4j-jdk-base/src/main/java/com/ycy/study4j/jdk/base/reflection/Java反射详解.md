# Java反射详解

反射常用于各种设计模式中，可方便的根据类名去初始化对象，并调用方法，可减少代码的耦合性，提高健壮性。

## 反射常用方法
```
Class类是Reflection API中核心的类，他位于java.lang.Class
列出一些常用的方法。
- getName() : 获得类的完整名字
- getFields() : 获得类的public类型的属性
- getDeclaredFields() : 获得类的所有属性
- getMethods() : 获得类的public类型的方法
- getDeclaredMethods() : 获得类的所有方法
- getMethod(String name, Class[] parameterTypes) : 获取类的特定方法(name参数指定方法名字，parameterTypes参数指定方法参数类型)
- getConstructors() : 获得类的public类型的构造方法
- getConstructor(Class[] parameterTypes) : 获得类的特定构造方法(parameterTypes参数指定构造方法的参数类型)
- newInstance() : 通过类的不带参数的构造方法创建这个类的一个对象
```

## 获取class对象方法

获取某个类或某个对象所对应的Class对象的常用的3种方法

- 使用Class类的静态方法forName： 
```
 Class.forName("java.lang.String");
```
 
- 使用类的.class语法： 
```
 String.class;
```
   
- 使用对象的getClass()方法(java.lang.Object类中的方法)： 
```
 String s = "aa"; 
 Class<?> clazz = s.getClass();
```
   
## 如何获得对象实例
- 调用无参数的构造方法：
```
1.调用Class对象的newInstance()方法:
Class<?> classType = ClassClass.forName("java.lang.String");
Object object = classTpye.newInstance();
2.调用Class对象的Constructor对象的newInstance()方法，传递一个空的Class对象数组作为参数：
Class<?> classType = ClassClass.forName("java.lang.String");
Constructor cons = classType.getConstructor(new Class[]{});  
Object object =cons.newInstance(new Object[]{});
```
b)调用有参数的构造方法：
```
1.调用Class对象的Constructor对象的newInstance()方法，传递一个可变长的Class对象数组作为参数，本例传递String,int两个参数：
Class<?> classType = ClassClass.forName("java.lang.String");
Constructor cons = classType.getConstructor(new Class[]{String.class, int.class});
Object object =cons.newInstance(new Object[]{"hello",3});
```

## 反射调用对象的私有属性和方法
正常的调用是不可以访问对象的private修饰的属性和方法的，这也是java的封装性原则。
但是通过反射可以实现访问对象的私有属性和方法。
```
Filed.setAccessible(true)
Method.setAccessible(true)
```
可以实现禁止java访问控制检查，从而达到访问对象的私有属性和方法。










