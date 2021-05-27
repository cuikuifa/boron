package cn.od.moutian.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "user_data")
public class User01 {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像地址
     */
    @Column(name = "profile_photo")
    private String profilePhoto;

    /**
     * 性别
     */
    private Byte sex;

    /**
     * 等级
     */
    private Byte level;

    /**
     * 个性签名
     */
    private String signature;

    private String role;

    /**
     * 注册时间         //todo 2/16 改为入库时间
     */
    @Column(name = "register_time")
    private Date registerTime;

    /**
     * 出生时间
     */
    private String birthday;

    /**
     * (主人的）关注数
     */
    @Column(name = "follow_number")
    private Integer followNumber;

    /**
     * （被关注）粉丝数
     */
    @Column(name = "fans_number")
    private Integer fansNumber;

    /**
     * 视频播放数
     */
    @Column(name = "video_views")
    private Long videoViews;

    /**
     * 用户来源，1B站
     */
    private Byte type;

    /**
     * 获取主键id
     *
     * @return id - 主键id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键id
     *
     * @param id 主键id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取昵称
     *
     * @return nickname - 昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置昵称
     *
     * @param nickname 昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取头像地址
     *
     * @return profile_photo - 头像地址
     */
    public String getProfilePhoto() {
        return profilePhoto;
    }

    /**
     * 设置头像地址
     *
     * @param profilePhoto 头像地址
     */
    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    /**
     * 获取性别
     *
     * @return sex - 性别
     */
    public Byte getSex() {
        return sex;
    }

    /**
     * 设置性别
     *
     * @param sex 性别
     */
    public void setSex(Byte sex) {
        this.sex = sex;
    }

    /**
     * 获取等级
     *
     * @return level - 等级
     */
    public Byte getLevel() {
        return level;
    }

    /**
     * 设置等级
     *
     * @param level 等级
     */
    public void setLevel(Byte level) {
        this.level = level;
    }

    /**
     * 获取个性签名
     *
     * @return signature - 个性签名
     */
    public String getSignature() {
        return signature;
    }

    /**
     * 设置个性签名
     *
     * @param signature 个性签名
     */
    public void setSignature(String signature) {
        this.signature = signature;
    }

    /**
     * @return role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * 获取注册时间
     *
     * @return register_time - 注册时间
     */
    public Date getRegisterTime() {
        return registerTime;
    }

    /**
     * 设置注册时间
     *
     * @param registerTime 注册时间
     */
    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    /**
     * 获取出生时间
     *
     * @return birthday - 出生时间
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * 设置出生时间
     *
     * @param birthday 出生时间
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取(主人的）关注数
     *
     * @return follow_number - (主人的）关注数
     */
    public Integer getFollowNumber() {
        return followNumber;
    }

    /**
     * 设置(主人的）关注数
     *
     * @param followNumber (主人的）关注数
     */
    public void setFollowNumber(Integer followNumber) {
        this.followNumber = followNumber;
    }

    /**
     * 获取（被关注）粉丝数
     *
     * @return fans_number - （被关注）粉丝数
     */
    public Integer getFansNumber() {
        return fansNumber;
    }

    /**
     * 设置（被关注）粉丝数
     *
     * @param fansNumber （被关注）粉丝数
     */
    public void setFansNumber(Integer fansNumber) {
        this.fansNumber = fansNumber;
    }

    /**
     * 获取视频播放数
     *
     * @return video_views - 视频播放数
     */
    public Long getVideoViews() {
        return videoViews;
    }

    /**
     * 设置视频播放数
     *
     * @param videoViews 视频播放数
     */
    public void setVideoViews(Long videoViews) {
        this.videoViews = videoViews;
    }

    /**
     * 获取用户来源，1B站
     *
     * @return type - 用户来源，1B站
     */
    public Byte getType() {
        return type;
    }

    /**
     * 设置用户来源，1B站
     *
     * @param type 用户来源，1B站
     */
    public void setType(Byte type) {
        this.type = type;
    }
}