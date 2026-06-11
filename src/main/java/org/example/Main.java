package org.example;

import org.h2.tools.Server;

public class Main {
    public static void main(String[] args) throws Exception {
        Server.createWebServer(
                "-web",
                "-webAllowOthers",
                "-webPort",
                "8082"
        ).start();

        System.out.println("H2 Console Started");
        StudentDAO dao = new StudentDAO();

        dao.createTable();

        dao.save(new Student("Romeo", 28));
        dao.save(new Student("John", 25));

        System.out.println("After Insert");
        dao.findAll()
                .forEach(s ->
                        System.out.println(
                                s.getId() + " "
                                        + s.getName() + " "
                                        + s.getAge()));

        dao.update(1, "Romeo RJ");

        System.out.println("After Update");

        dao.findAll()
                .forEach(s ->
                        System.out.println(
                                s.getId() + " "
                                        + s.getName()));

        dao.delete(2);

        System.out.println("After Delete");
        dao.findAll()
                .forEach(s ->
                        System.out.println(
                                s.getId() + " "
                                        + s.getName()));
    }
}