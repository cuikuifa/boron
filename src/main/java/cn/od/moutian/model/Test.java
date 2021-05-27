package cn.od.moutian.model;

import lombok.*;

import javax.persistence.*;

/**
 * Created by 周欣文 on 2018/10/23 0005.
 * 测试用实体类
 */
@Setter             //：注解在属性上；为属性提供 setting 方法 、
@Getter             // 注解在属性上；为属性提供 getting 方法
@ToString           // 注解在属性上；为属性提供 toString 方法
@NoArgsConstructor  //：注解在类上；为类提供一个无参的构造方法
@AllArgsConstructor //：注解在类上；为类提供一个全参的构造方法
@Table(name = "Test")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * 测试用字段
     * */
    @Column(name = "test")
    private String test;
}
