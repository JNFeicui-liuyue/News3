package contacts.feicui.edu.news3.model.entity;

/**
 * Created by liuyue on 2016/6/1.
 */
public class SubType {
    private int subid;
    private String subgroup;

    public SubType(int subid, String subgroup) {
        this.subid = subid;
        this.subgroup = subgroup;
    }

    public int getSubid() {
        return subid;
    }

    public String getSubgroup() {
        return subgroup;
    }
}
