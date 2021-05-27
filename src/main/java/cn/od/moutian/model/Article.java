package cn.od.moutian.model;

import lombok.*;

/**
 * Created by zxw on 2018/7/26 0026.
 * 无用的文章测试类
 */
@Setter         //：注解在属性上；为属性提供 setting 方法 、
@Getter         // 注解在属性上；为属性提供 getting 方法
@ToString       // 注解在属性上；为属性提供 toString 方法
@NoArgsConstructor  //：注解在类上；为类提供一个无参的构造方法
@AllArgsConstructor //：注解在类上；为类提供一个全参的构造方法
public class Article {
    private Integer id;           //: '@increment',
    private String timestamp;          //: +Mock.Random.date('T'),
    private String author;          //: '@first',
    private String reviewer;          //: '@first',
    private String title;          //: '@title(5, 10)',
    private String content_short;          //: '我是测试数据',
    private String content;          //: baseContent,
    private Float forecast;          //: '@float(0, 100, 2, 2)',
    //    private   importance;          //
    private String display_time;          //: '@datetime',
    private Boolean comment_disabled;          //: true,
    private Integer pageviews;          //: '@integer(300, 5000)',
    private String image_uri;
//    private   platforms;          //: ['a-platform']

}
