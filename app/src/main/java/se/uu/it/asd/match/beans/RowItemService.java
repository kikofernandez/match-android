package se.uu.it.asd.match.beans;

/**
 * Created by kikofernandezreyes on 19/08/15.
 */
public class RowItemService {
    private int id, assigned, image;
    private String[] skills;
    private String request, user_request;

    public RowItemService(int id, int assigned, String[] skills, String request, String user_request, int image) {
        this.id = id;
        this.assigned = assigned;
        this.skills = skills;
        this.request = request;
        this.user_request = user_request;
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAssigned() {
        return assigned;
    }

    public void setAssigned(int assigned) {
        this.assigned = assigned;
    }

    public String[] getSkills() {
        return skills;
    }

    public void setSkills(String[] skills) {
        this.skills = skills;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getUser_request() {
        return user_request;
    }

    public void setUser_request(String user_request) {
        this.user_request = user_request;
    }
}
