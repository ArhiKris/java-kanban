/* Доброго времени суток.
Теперь вроде точно все исправила =)*/

import ru.yandex.tasktracker.model.Epic;
import ru.yandex.tasktracker.model.Subtask;
import ru.yandex.tasktracker.model.Task;
import ru.yandex.tasktracker.model.TaskStatus;
import ru.yandex.tasktracker.service.TaskManager;

public class Main {

    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        Task task1 = new Task("Купить квартиру", "Ну или для начала начать на нее откладывать!");
        manager.addTask(task1);
        Task task2 = new Task("Выгулять кота", "Он не видел улицу 10000 лет!");
        manager.addTask(task2);
        Epic epic1 = new Epic("Купить продукты", "Дождаться зарплаты и купить еды");
        manager.addEpic(epic1);
        Subtask subtask1 = new Subtask("Составить список", "Ведь без него опять одна кока-кола...", 3);
        manager.addSubtask(subtask1);
        Subtask subtask2 = new Subtask("Разобрать покупки", "Положить продукты в холодильник", 3);
        manager.addSubtask(subtask2);
        Epic epic2 = new Epic("Записаться в спортзал", "Похудеть к лету");
        manager.addEpic(epic2);
        Subtask subtask3 = new Subtask("Купить новые кроссовки и спортивный костюм", "Покупка продуктов", 6);
        manager.addSubtask(subtask3);

        System.out.println(manager.getAllTasks());
        System.out.println(manager.getAllEpics());
        System.out.println(manager.getAllSubtasks());

        task2.setId(2);
        task2.setStatus(TaskStatus.DONE);
        manager.updateTask(task2);

        epic2.setId(6);
        manager.updateEpic(epic2);

        subtask2.setId(5);
        subtask2.setStatus(TaskStatus.IN_PROGRESS);
        manager.updateSubtask(subtask2);

        System.out.println(manager.getAllTasks());
        System.out.println(manager.getAllEpics());
        System.out.println(manager.getAllSubtasks());

        manager.deleteTaskById(1);
        manager.deleteEpicById(6);
        manager.deleteSubtaskById(7);

        System.out.println(manager.getAllTasks());
        System.out.println(manager.getAllEpics());
        System.out.println(manager.getAllSubtasks());

        manager.deleteAllTasks();
        manager.deleteAllEpics();
        manager.deleteAllSubtasks();

        System.out.println(manager.getAllTasks());
        System.out.println(manager.getAllEpics());
        System.out.println(manager.getAllSubtasks());
    }
}
