import model.Tasks;
import repository.TaskRepo;
import service.TasksService;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        TasksService tasksService = new TasksService();
        TaskRepo taskRepo = new TaskRepo();
        while (true) {
            System.out.println("Выберите номер действия: \n1.Создать" +
                    "\n2.Редактирование" +
                    "\n3.Удалить" +
                    "\n4.Показать задачи");
            System.out.print("Выберите номер действия (1-4):");
            int answer = scanner.nextInt();
            switch (answer){
                case 1:
                    System.out.println("Введите текстовое описание задачи :");
                    tasksService.craeteTask(scanner.next());
                    break;
                case 2:
                    System.out.println("Введите идентификатор задачи для редактирования:");
                    int answerUpdate = scanner.nextInt();
                    System.out.println("Введите новое текстовое описание задачи:");
                    tasksService.editTask(answerUpdate, scanner.next());
                    break;
                case 3:
                    System.out.println("Введите идентификатор задачи, который хотите удалить:");
                    tasksService.deleteTask(scanner.nextInt());
                    break;
                case 4:
                    System.out.println("Список задач:");
                    ArrayList<Tasks>  tasks = tasksService.getAllTasks();
                    for (Tasks task : tasks) {
                        System.out.print(task.toString());
                    }
                    System.out.println();
                    break;
                case 5:
                    System.out.println("Введите идентификатор задачи, которую хотите отметить как выполненную:");
                    tasksService.completedTask(scanner.nextInt());
                    break;
                default:
                    System.out.println("Ошибка!");

            }
        }
    }
}