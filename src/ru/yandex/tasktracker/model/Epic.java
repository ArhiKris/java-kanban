package ru.yandex.tasktracker.model;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private List<Long> subTasksIds;

    public Epic(String name, String description) {
        super(name, description);
        this.subTasksIds = new ArrayList<>();
    }

    public List<Long> getSubTasksIds() {
        return subTasksIds;
    }

    public void setSubTasksIds(List<Long> subTasksIds) {
        this.subTasksIds = subTasksIds;
    }

    public void addSubTaskId(long subTaskId) {
        subTasksIds.add(subTaskId);
    }

    @Override
    public String toString() {
        return "Epic{" +
                "subTasksIds=" + subTasksIds +
                ", name='" + super.getName() + '\'' +
                ", description='" + super.getDescription() + '\'' +
                ", id=" + super.getId() +
                ", status=" + super.getStatus() +
                '}';
    }
}
