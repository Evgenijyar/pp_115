package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("Николай", "Гоголь", (byte) 22);
        userService.saveUser("Лев", "Толстой", (byte) 45);
        userService.saveUser("Максим", "Горький", (byte) 18);
        userService.saveUser("Антон", "Чехов", (byte) 30);

        List<User> userList = userService.getAllUsers();
        for (User user : userList) {
            System.out.println(user);
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
