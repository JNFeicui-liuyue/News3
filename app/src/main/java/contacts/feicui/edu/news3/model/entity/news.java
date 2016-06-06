package contacts.feicui.edu.news3.model.entity;

import java.io.Serializable;

/**用来存储从网络获取的新闻数据，声明新闻条目需要的
 * 各个属性以及各个属性的获得和设置方法
 * Created by liuyue on 2016/6/3.
 */
public class News implements Serializable{

    private static final long serialVersionUID = 1L;
    //类型标识 1：列表新闻 2.大图新闻
    public int type;
    //新闻id
    public int nid;
    //时间戳
    public String stamp;
    //图标
    public String icon;
    //新闻标题
    public String title;
    //新闻摘要
    public String summary;
    //新闻链接
    public String link;

//    public News(int type, int nid, String stamp, String icon, String title, String summary, String link) {
//        this.type = type;
//        this.nid = nid;
//        this.stamp = stamp;
//        this.icon = icon;
//        this.title = title;
//        this.summary = summary;
//        this.link = link;
//    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getType() {
        return type;
    }

    public int getNid() {
        return nid;
    }

    public String getStamp() {
        return stamp;
    }

    public String getIcon() {
        return icon;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public String getLink() {
        return link;
    }

    @Override
    public String toString() {
        return "News [nid = "+ nid +",title = " + title + ",summary = " + summary
                + ",icon = " + icon + " ,url = " + link + ",type = " + type +"]";
    }
}
