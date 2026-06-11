package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    public void createTable() throws SQLException {

        String sql = """
                CREATE TABLE IF NOT EXISTS students(
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(100),
                    age INT
                )
                """;

        try(Connection conn =
                    DBConnection.getConnection();
            Statement stmt =
                    conn.createStatement()) {

            stmt.execute(sql);
        }
    }

    public void save(Student student) throws SQLException {

        String sql = "INSERT INTO students(name, age) VALUES (?, ?)";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, student.getName());
            ps.setInt(2, student.getAge());

            ps.executeUpdate();
        }
    }

    public List<Student> findAll() throws SQLException {

        List<Student> students = new ArrayList<>();

        String sql = "SELECT * FROM students";

        try(Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while(rs.next()) {
                Student s = new Student();

                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setAge(rs.getInt("age"));

                students.add(s);
            }
        }

        return students;
    }
    public void update(int id, String name) throws SQLException {

        String sql = "UPDATE students SET name=? WHERE id=?";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setInt(2, id);

            ps.executeUpdate();
        }
    }
    public void delete(int id) throws SQLException {

        String sql = "DELETE FROM students WHERE id=?";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps =conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();
        }
    }
}
