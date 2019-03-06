package day01.nguyendpt.chidstudy;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

public class ObjectPlay implements Serializable {
    private String category;
    private String vietName;
    private String engName;
    private Integer image;
    private String note;
    private String[] answers;

    public ObjectPlay(){

    }

    public ObjectPlay(String category, String vietName, String engName, Integer image, String[] answers){
        this.category = category;
        this.vietName = vietName;
        this.engName = engName;
        this.image = image;
        this.answers = answers;
        this.note = null;
    }

    public ObjectPlay(String category, String vietName, String engName, Integer image, String[] answers, String note){
        this.category = category;
        this.vietName = vietName;
        this.engName = engName;
        this.image = image;
        this.answers = answers;
        this.note = note;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getVietName() {
        return vietName;
    }

    public void setVietName(String vietName) {
        this.vietName = vietName;
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String[] getAnswers() {
        return answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
