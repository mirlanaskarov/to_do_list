package service;

import enums.TaskStatus;
import model.Tasks;
import repository.TaskRepo;

import java.sql.PreparedStatement;
import java.sql.*;
import java.util.ArrayList;

public class TasksService {
    TaskRepo tasksRepo = new TaskRepo();
    public void craeteTask(String answer){
        try {
            Connection connection = tasksRepo.connectToDataBase();
            Statement statement = connection.createStatement();
            statement.executeUpdate("insert into tasks (status, description) values ('"+ TaskStatus.IN_PROGRESS.name()+"', '"+answer+"')");
            ArrayList<Tasks> tasks = getAllTasks();
            for (Tasks task : tasks) {
                if (task.getDescription().equals(answer)) {
                    System.out.println("Задача успешно добавлена! Идентификатор задачи: " + task.getId()+"\n");
                    connection.close();
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public ArrayList<Tasks> getAllTasks(){
        try {
            PreparedStatement preparedStatement = tasksRepo.connectToDataBase().prepareStatement("select id, \n" +
                    "case \n" +
                    "when status ='IN_PROGRESS' then '[Не выполнено]'\n" +
                    "when status = 'DONE' then '[Выполнено]'\n" +
                    "when status = 'DELETED' then '[Удалено]'\n" +
                    "end,description\n" +
                    "from tasks;");
            ArrayList<Tasks> allTasks = new ArrayList<>();
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()){
                Tasks tasks = new Tasks();
                tasks.setId(res.getInt(1));
                tasks.setStatus(res.getString(2));
                tasks.setDescription(res.getString(3));
                allTasks.add(tasks);
            } return allTasks;
        } catch (SQLException e) {
            System.out.println("Ошибка23"+e.getMessage());
            return null;
        }
    }
    public void editTask(int id, String newDescription){
        try {
            Connection connection = tasksRepo.connectToDataBase();
            Statement statement = connection.createStatement();
            statement.executeUpdate("update tasks set description = '"+newDescription+"' where id = '"+id+"'");
            System.out.println("Задача успешно отредактирована!\n");
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteTask(int id){
        try {
            Connection connection = tasksRepo.connectToDataBase();
            Statement statement = connection.createStatement();
            statement.executeUpdate("update tasks set status = '"+TaskStatus.DELETED+"' where id = '"+id+"'");
            System.out.println("Задача успешно удалено!\n");
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Tasks showAllTask(){
        return null;
    }
    public void completedTask(int id){

        try {
            Connection connection = tasksRepo.connectToDataBase();
            Statement statement = connection.createStatement();
            statement.executeUpdate("update tasks set status = '"+TaskStatus.DONE+"' where id = '"+id+"'");
            System.out.println("Задача успешно выполнено!\n");
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}