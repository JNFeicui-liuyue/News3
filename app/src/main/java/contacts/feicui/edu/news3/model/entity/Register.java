package contacts.feicui.edu.news3.model.entity;

/**有关注册和登录的entity
 * Created by liuyue on 2016/6/3.
 */
public class Register {

    String result;
    String token;//用户令牌，用于验证用户。每次请求都传递给服务器，具有时效期
    String explain;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
