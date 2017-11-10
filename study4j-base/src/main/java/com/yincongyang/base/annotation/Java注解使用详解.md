# Java注解使用详解

## Annotation的解释
JDK1.5之后，引入了元数据的概念，也就是Annotation，其实它是代码里的特殊标记，这些标记可以再编译、类加载、运行时被读取，并执行相应的处理。
注解通常配合反射来实现某些具体功能，或者用来标注某些信息，而不具备具体逻辑。


## Annotation的简单使用

**java内建注解**：

- `@Override` 覆写的Annotation
- `@Deprecated` 不赞成使用的Annotation
- `@SuppressWarnings` 压制安全警告的Annotation

**自定义注解**：

```
@Target
@Retention
@Inherited
@Documented
[public] @interface Annotation名称{
        数据类型  变量名称();
}
```

## Annotation的注解使用说明

**限定注释使用范围`@Target`**
```
范围定义：
public enum ElementType {  
  TYPE,         // Class, interface, or enum (but not annotation)  
  FIELD,        // Field (including enumerated values)  
  METHOD,       // Method (does not include constructors)  
  PARAMETER,        // Method parameter  
  CONSTRUCTOR,      // Constructor  
  LOCAL_VARIABLE,   // Local variable or catch clause  
  ANNOTATION_TYPE,  // Annotation Types (meta-annotations)  
  PACKAGE       // Java package  
}

使用方式：
@Target( { ElementType.METHOD, ElementType.CONSTRUCTOR })  
public @interface TargetTest { 
} 
```

**注释保持性策略`@Retention`**
```
策略定义：
public enum RetentionPolicy {  
  SOURCE,// Annotation is discarded by the compiler  
  CLASS,// Annotation is stored in the class file, but ignored by the VM  
  RUNTIME// Annotation is stored in the class file and read by the VM  
}  
只有RUNTIME的注解可以被反射捕获到

使用方式：

import java.lang.annotation.Retention ;  
import java.lang.annotation.RetentionPolicy ;  
@Retention(value=RetentionPolicy.RUNTIME) // 表示此Annotation在运行时有效  
public @interface MyDefaultRententionAnnotation{  
public String name() default "沉缘" ;  
} 
```

**标注继承`@Inherited`**
它的作用是控制注解是否会影响到子类（一个Annotation是否可以被继承下来）。


**文档化功能`@Documented`**
略



