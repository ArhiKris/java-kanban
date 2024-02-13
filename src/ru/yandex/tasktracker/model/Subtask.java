package ru.yandex.tasktracker.model;

public class Subtask extends Task {
    private long epicId;

    public Subtask(String name, String description, long epicId) {
        super(name, description);
        this.epicId = epicId;
    }

    public long getEpicId() {
        return epicId;
    }

    public void setEpicId(long epicId) {
        this.epicId = epicId;
    }

    //toString сгенирирован IDEA
    @Override
    public String toString() {
        return "Subtask{" +
                "epicId=" + epicId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", status=" + status +
                '}';
    }
}
