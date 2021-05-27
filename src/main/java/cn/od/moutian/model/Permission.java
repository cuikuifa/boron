package cn.od.moutian.model;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by zxw on 2018/9/5 0005.
 * 后台权限资源实体类
 */
@Setter         //：注解在属性上；为属性提供 setting 方法 、
@Getter         // 注解在属性上；为属性提供 getting 方法
@ToString       // 注解在属性上；为属性提供 toString 方法
@NoArgsConstructor  //：注解在类上；为类提供一个无参的构造方法
@AllArgsConstructor //：注解在类上；为类提供一个全参的构造方法
public class Permission {
    /**
     * 权限Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 权限对应的资源
     */
    private String resource;

    /**
     * 权限的代码/通配符,对应代码中@hasAuthority(xx)
     */
    private String code;

    /**
     * 对应的资源操作
     */
    private String handle;

}