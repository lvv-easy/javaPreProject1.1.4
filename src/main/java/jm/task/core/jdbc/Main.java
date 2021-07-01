package jm.task.core.jdbc;

import com.mysql.fabric.jdbc.FabricMySQLDriver;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService us = new UserServiceImpl();
        us.createUsersTable();
        us.saveUser("Vladimir", "Levin", (byte) 31);
        us.saveUser("Tatiana", "Levina", (byte) 32);
        us.saveUser("Izabella", "Levina", (byte) 1);
        us.saveUser("Vadim", "Pluzhnik", (byte) 32);
        System.out.println(us.getAllUsers());
        us.cleanUsersTable();
        us.dropUsersTable();

    }
}
