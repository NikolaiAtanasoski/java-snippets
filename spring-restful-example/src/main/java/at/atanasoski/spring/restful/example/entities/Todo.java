package at.atanasoski.spring.restful.example.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;


public class Todo extends BasicEntity<Todo, Long> {

    private @Id @GeneratedValue Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;
    private String text;
    private boolean isFinished;

    public Todo(){
        //default Constructor
    }

    public Todo(String text, boolean isFinished) {
        this.text = text;
        this.isFinished = isFinished;
    }

    @Override
    public Todo update(Todo newTodo) {
        this.text = newTodo.text;
        this.isFinished = newTodo.isFinished;
        return this;
    }

    @Override
    public String toString() {
        return "Todoamk{" +
               "id=" + id +
               ", user=" + user +
               ", text='" + text + '\'' +
               ", isFinished=" + isFinished +
               '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
