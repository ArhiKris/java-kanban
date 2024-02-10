package model;

public class Subtask extends Task {
    private long epicId;

    /** Конструктор для создания подзадачи*/
    public Subtask(String name, String description, TaskStatus status, long epicId) {
        super(name, description, status);
        this.epicId = epicId;
    }

    /** Конструктор для обновления подзадачи*/
    public Subtask(String name, String description, long id, TaskStatus status, long epicId) {
        super(name, description, id, status);
        this.epicId = epicId;
    }

    public long getEpicId() {
        return epicId;
    }

    public void setEpicId(long epicId) {
        this.epicId = epicId;
    }

    /** Пришлось переопределить еще раз для корректного выведения информации*/
    @Override
    public String toString() {
        return "Subtask{" +
                "name='" + super.getName() + '\'' +
                ", description='" + super.getDescription() + '\'' +
                ", id=" + super.getId() +
                ", status=" + super.getStatus() +
                ", epicId=" + epicId +
                '}';
    }
}
