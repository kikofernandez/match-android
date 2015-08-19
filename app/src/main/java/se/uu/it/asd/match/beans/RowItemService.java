package se.uu.it.asd.match.beans;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created by kikofernandezreyes on 19/08/15.
 */
public class RowItemService implements Parcelable{
    private int id, assigned, image;
    private String[] skills;
    private String request, user_request;

    public RowItemService(Parcel in){
        id = in.readInt();
        assigned = in.readInt();
        image = in.readInt();
        skills = in.createStringArray();
        request = in.readString();
        user_request = in.readString();
    }

    public RowItemService(int id, int assigned, String[] skills, String request, String user_request, int image) {
        this.id = id;
        this.assigned = assigned;
        this.skills = skills;
        this.request = request;
        this.user_request = "@"+user_request;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(assigned);
        dest.writeInt(image);
        dest.writeStringArray(skills);
        dest.writeString(request);
        dest.writeString(user_request);
    }

    public static final Parcelable.Creator<RowItemService> CREATOR = new Parcelable.Creator<RowItemService>() {

        @Override
        public RowItemService createFromParcel(Parcel source) {
            return new RowItemService(source);
        }

        @Override
        public RowItemService[] newArray(int size) {
            return new RowItemService[size];
        }

    };
}
