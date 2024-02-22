import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.tasktracker.model.Epic;
import ru.yandex.tasktracker.model.Subtask;
import ru.yandex.tasktracker.model.Task;
import ru.yandex.tasktracker.service.Managers;
import ru.yandex.tasktracker.service.TaskManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TaskManagerTest {
    Task task;
    Epic epic;
    Subtask subtask;
    static TaskManager taskManager;

    @BeforeAll
    public static void beforeAll(){
        taskManager = Managers.getDefault();
    }

    @BeforeEach
    public void beforeEach() {
        task = new Task("Task","Task des");
        epic = new Epic("Epic","Epic des");
        subtask = new Subtask("Subtask", "Subtask des",0);
    }

    @Test
    public void shouldTaskEqualsGetTaskAfterAddTask() {
        Task addedTask = taskManager.addTask(task);
        long id = addedTask.getId();
        task.setId(id);
        task.setName("Name");
        assertEquals(id, taskManager.getTaskById(id).getId());
        assertEquals(addedTask, task);
    }

    @Test
    public void shouldEpictaskEqualsEeachOther() {
        Epic addedEpic = taskManager.addEpic(epic);
        long id = addedEpic.getId();
        epic.setId(1);
        epic.setName("Name epic");
        assertEquals(id,1);
        assertEquals(addedEpic,epic);
    }
    @Test
    public void shouldSubtaskEqualsEachOther() {
        Epic epicForSubtask = new Epic("Epic","Epic des");
        subtask.setEpicId(taskManager.addEpic(epicForSubtask).getId());
        Subtask addSubtask = taskManager.addSubtask(subtask);
        long id = addSubtask.getId();
        subtask.setId(id);
        subtask.setName("Name subtask");
        assertEquals(id,taskManager.getSubtaskById(id).getId());
        assertEquals(addSubtask,subtask);

    }

    @Test
    public void shouldBeManagers() {
        assertNotNull(Managers.getDefault());
        assertNotNull(Managers.getDefaultHistory());
    }

    @Test
    public void shouldBeEqualsWithAddedTask() {
        Task addedTask = taskManager.addTask(task);
        task.setId(addedTask.getId());
        assertEquals(task, taskManager.getTaskById(addedTask.getId()));
    }

    @Test
    public void shouldBeEqualsWithAddedEpic() {
        Epic addedEpic = taskManager.addEpic(epic);
        epic.setId(addedEpic.getId());
        assertEquals(epic, taskManager.getEpicById(addedEpic.getId()));
    }

    @Test
    public void shouldBeEqualsWithAddedSubtask() {
        Epic addedEpic = taskManager.addEpic(epic);
        subtask.setEpicId(addedEpic.getId());
        Subtask addedSubtask = taskManager.addSubtask(subtask);
        subtask.setId(addedSubtask.getId());
        assertEquals(subtask, taskManager.getSubtaskById(addedSubtask.getId()));
    }

    @Test
    public void shouldBeNotConflictIdInManager() {
        taskManager.addTask(task);
        task.setId(1);
        Task addedTask = taskManager.addTask(task);
        assertEquals(addedTask.getId(), task.getId());
    }

    @Test
    public void shouldBeStaticTaskAfterAddInManager() {
        Task addedTask = taskManager.addTask(task);
        assertEquals(addedTask.getName(), task.getName());
        assertEquals(addedTask.getDescription(), task.getDescription());
    }

    @Test
    public void shouldBeStaticEpicAfterAddInManager() {
        Epic addedEpic = taskManager.addEpic(epic);
        assertEquals(addedEpic.getName(), epic.getName());
        assertEquals(addedEpic.getDescription(), epic.getDescription());
    }

    @Test
    public void shouldBeStaticSubtaskAfterAddInManager() {
        Epic addedEpic = taskManager.addEpic(epic);
        subtask.setEpicId(addedEpic.getId());
        Subtask addedSubtask = taskManager.addSubtask(subtask);
        assertEquals(addedSubtask.getName(), subtask.getName());
        assertEquals(addedSubtask.getDescription(), subtask.getDescription());
        assertEquals(addedSubtask.getEpicId(), subtask.getEpicId());
    }

    @Test
    public void shouldBeSavedTaskInHistoryManager() {
        Task addedTask = taskManager.addTask(task);
        taskManager.getTaskById(addedTask.getId());
        assertEquals(taskManager.getHistory().getLast(), addedTask);
    }
}