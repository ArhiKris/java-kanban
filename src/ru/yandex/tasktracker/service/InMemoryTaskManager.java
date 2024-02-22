package ru.yandex.tasktracker.service;

import ru.yandex.tasktracker.model.Epic;
import ru.yandex.tasktracker.model.Subtask;
import ru.yandex.tasktracker.model.Task;
import ru.yandex.tasktracker.model.TaskStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTaskManager implements TaskManager {
    private final Map<Long, Task> tasks = new HashMap<>();
    private final Map<Long, Epic> epics = new HashMap<>();
    private final Map<Long, Subtask> subtasks = new HashMap<>();
    private long id = 1;
    private final HistoryManager historyManager = Managers.getDefaultHistory();

    @Override
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public List<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    @Override
    public List<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    @Override
    public void deleteAllTasks() {
        tasks.clear();
    }

    @Override
    public void deleteAllEpics() {
        epics.clear();
        subtasks.clear();
    }

    @Override
    public void deleteAllSubtasks() {
        subtasks.clear();
        for (Epic epic : epics.values()) {
            epic.setSubTasksIds(new ArrayList<>());
        }
    }

    @Override
    public Task getTaskById(long id) {
        if (!tasks.containsKey(id)) {
            System.out.println("Задачи с таким id нет");
            return null;
        }
        Task task = tasks.get(id);
        historyManager.add(task);
        return task;
    }

    @Override
    public Epic getEpicById(long id) {
        if (!epics.containsKey(id)) {
            System.out.println("Эпика с таким id нет");
            return null;
        }
        Epic epic = epics.get(id);
        historyManager.add(epic);
        return epic;
    }

    @Override
    public Subtask getSubtaskById(long id) {
        if (!subtasks.containsKey(id)) {
            System.out.println("Подзадачи с таким id нет");
            return null;
        }
        Subtask subtask = subtasks.get(id);
        historyManager.add(subtask);
        return subtask;
    }

    @Override
    public Task addTask(Task task) {
        task.setId(id++);
        tasks.put(task.getId(), task);
        return task;
    }

    @Override
    public Epic addEpic(Epic epic) {
        epic.setId(id++);
        epics.put(epic.getId(), epic);
        setEpicStatus(epic.getId());
        return epic;
    }

    @Override
    public Subtask addSubtask(Subtask subtask) {
        subtask.setId(id++);
        subtasks.put(subtask.getId(), subtask);
        epics.get(subtask.getEpicId()).addSubTaskId(subtask.getId());
        setEpicStatus(subtask.getEpicId());
        return subtask;
    }

    @Override
    public Task updateTask(Task task) {
        Task updatedTask = getTaskById(task.getId());
        updatedTask.setName(task.getName());
        updatedTask.setDescription(task.getDescription());
        updatedTask.setStatus(task.getStatus());
        return updatedTask;
    }

    @Override
    public Epic updateEpic(Epic epic) {
        Epic updatedEpic = getEpicById(epic.getId());
        updatedEpic.setName(epic.getName());
        updatedEpic.setDescription(epic.getDescription());
        setEpicStatus(epic.getId());
        return updatedEpic;
    }

    @Override
    public Subtask updateSubtask(Subtask subtask) {
        Subtask updatedSubtask = getSubtaskById(subtask.getId());
        updatedSubtask.setName(subtask.getName());
        updatedSubtask.setDescription(subtask.getDescription());
        updatedSubtask.setStatus(subtask.getStatus());
        updatedSubtask.setEpicId(subtask.getEpicId());
        setEpicStatus(subtask.getEpicId());
        return updatedSubtask;
    }

    @Override
    public void deleteTaskById(long id) {
        tasks.remove(id);
    }

    @Override
    public void deleteEpicById(long id) {
        Epic epic = getEpicById(id);
        for (long subtaskId : epic.getSubTasksIds()) {
            subtasks.remove(subtaskId);
        }
        epics.remove(id);
    }

    @Override
    public void deleteSubtaskById(long id) {
        Subtask subtask = getSubtaskById(id);
        epics.get(subtask.getEpicId()).getSubTasksIds().remove(id);
        subtasks.remove(id);
    }

    @Override
    public List<Subtask> getSubtasksByEpicId(long id) {
        List<Subtask> subtasksByEpicId = new ArrayList<>();
        List<Long> ids = getEpicById(id).getSubTasksIds();
        for (Long subId : ids) {
            subtasksByEpicId.add(getSubtaskById(subId));
        }
        return subtasksByEpicId;
    }

    private void setEpicStatus(long epicId) {
        Epic epic = getEpicById(epicId);
        if (epic.getSubTasksIds().isEmpty()) {
            epic.setStatus(TaskStatus.NEW);
            return;
        }
        TaskStatus status = null;
        for (long subtaskId : epic.getSubTasksIds()) {
            Subtask subtask = getSubtaskById(subtaskId);
            if (status == null) {
                status = subtask.getStatus();
                continue;
            }
            if (status.equals(subtask.getStatus()) && status != TaskStatus.IN_PROGRESS) {
                continue;
            }
            epic.setStatus(TaskStatus.IN_PROGRESS);
            return;
        }
        epic.setStatus(status);
    }

    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }


    public Map<Long, Task> getTasks() {
        return tasks;
    }

    public Map<Long, Epic> getEpics() {
        return epics;
    }

    public Map<Long, Subtask> getSubtasks() {
        return subtasks;
    }
}
