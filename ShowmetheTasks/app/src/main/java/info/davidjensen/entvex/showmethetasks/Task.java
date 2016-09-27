package info.davidjensen.entvex.showmethetasks;

/**
 * Created by entvex on 26-09-2016.
 */
public class Task {

    private int id;
    private String task_name;
    private String place_name;

    public Task() {
    }

    public Task(String task_name, String place_name) {
        super();
        this.task_name = task_name;
        this.place_name = place_name;
    }

    //getters & setters
    @Override
    public String toString() {
        return "Book [id=" + id + ", task_name=" + task_name + ", place_name=" + place_name
                + "]";
    }

    public String getTask_name() {
        return task_name;
    }

    public String getPlace_name() {
        return place_name;
    }

    public int getId() {
        return id;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public void setPlace_name(String place_name) {
        this.place_name = place_name;
    }

    public void setId(int id) {
        this.id = id;
    }
}