package contacts.feicui.edu.news3.model.entity;

/**
 * Created by liuyue on 2016/6/3.
 */
public class LoginLog {
    //登录地址
    private String address;
    //登录时的设备 true：手机登录 false：网页登录
    private int device;
    //登录时间
    private String time;

    public LoginLog(String address, int device, String time) {
        this.address = address;
        this.device = device;
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getDevice() {
        return device;
    }

    public void setDevice(int device) {
        this.device = device;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
