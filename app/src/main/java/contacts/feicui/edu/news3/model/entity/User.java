package contacts.feicui.edu.news3.model.entity;

import java.util.List;

/**
 * Created by liuyue on 2016/6/3.
 */
public class User {

    //用户id
    private String uid;
    //用户邮箱
    private String email;
    //用户积分
    private int integration;
    //评论数量
    private int comnum;
    //头像
    private String portrait;
    //登录日志
    private List<LoginLog> loginlog;

    public User(String uid, String email, int integration, int comnum, String portrait, List<LoginLog> loginlog) {
        this.uid = uid;
        this.email = email;
        this.integration = integration;
        this.comnum = comnum;
        this.portrait = portrait;
        this.loginlog = loginlog;
    }

    public String getUid() {
        return uid;
    }

    public String getEmail() {
        return email;
    }

    public int getIntegration() {
        return integration;
    }

    public int getComnum() {
        return comnum;
    }

    public String getPortrait() {
        return portrait;
    }

    public List<LoginLog> getLoginlog() {
        return loginlog;
    }
}
