/* Доброго времени суток.
Очень страшненький тест получился =) Понимаю, что в следующем ТЗ будем учиться тестированию...
Прошу обратить внимание на конструкторы в моделях. Правильно ли я их реализовала? Были сомнения по поводу эпиков.
В остальном - все работает: создание, изменение, удаление всех типов задач,
определение статусов у эпиков на основании подзадач.*/

import model.Epic;
import model.Subtask;
import model.Task;
import model.TaskStatus;
import servise.TaskManager;

public class Main {

    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        manager.addTask(new Task("Купить квартиру", "Ну или для начала начать на нее откладывать!", TaskStatus.NEW));
        manager.addTask(new Task("Выгулять кота", "Он не видел улицу 10000 лет!",
                TaskStatus.NEW));
        manager.addEpic(new Epic("Купить продукты", "Дождаться зарплаты и купить еды"));
        manager.addSubtask(new Subtask("Составить список", "Ведь без него опять одна кока-кола...",
                TaskStatus.DONE, 3));
        manager.addSubtask(new Subtask("Разобрать покупки", "Положить продукты в холодильник",
                TaskStatus.DONE, 3));
        manager.addEpic(new Epic("Купить продукты", "Зарплата пришла пора идти вв магазин"));
        manager.addSubtask(new Subtask("Поехать в магазин", "Покупка продуктов",
                TaskStatus.IN_PROGRESS, 6));

        System.out.println(manager.getAllTasks());
        System.out.println(manager.getAllEpics());
        System.out.println(manager.getAllSubtasks());

        manager.updateTask(new Task("Отмыть кота", "Не умереть от потери крови", 2,
                TaskStatus.DONE));
        manager.updateEpic(new Epic("Купить продукты", "Купить еды", 3));
        manager.updateSubtask(new Subtask("Отдохнуть", "Набраться сил перед походом в магазин...", 5,
                TaskStatus.IN_PROGRESS,3));

        System.out.println(manager.getAllTasks());
        System.out.println(manager.getAllEpics());
        System.out.println(manager.getAllSubtasks());

        manager.deleteTaskByIg(1);
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
