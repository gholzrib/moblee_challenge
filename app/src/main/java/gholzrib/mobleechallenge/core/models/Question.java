package gholzrib.mobleechallenge.core.models;

import java.util.ArrayList;

/**
 * Created by Gunther Ribak on 13/11/2016.
 * For more information contact me
 * through guntherhr@gmail.com
 */

public class Question {

    Boolean is_answered;
    Long view_count;
    Long answer_count;
    Long score;
    Long last_activity_date;
    Long creation_date;
    Long question_id;
    String link;
    String title;

    Owner owner;
    ArrayList<String> tags = new ArrayList<>();

    public Boolean getIs_answered() {
        return is_answered;
    }

    public void setIs_answered(Boolean is_answered) {
        this.is_answered = is_answered;
    }

    public Long getView_count() {
        return view_count;
    }

    public void setView_count(Long view_count) {
        this.view_count = view_count;
    }

    public Long getAnswer_count() {
        return answer_count;
    }

    public void setAnswer_count(Long answer_count) {
        this.answer_count = answer_count;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public Long getLast_activity_date() {
        return last_activity_date;
    }

    public void setLast_activity_date(Long last_activity_date) {
        this.last_activity_date = last_activity_date;
    }

    public Long getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Long creation_date) {
        this.creation_date = creation_date;
    }

    public Long getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Long question_id) {
        this.question_id = question_id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }
}
