package Repository;

import Database.DatabaseUtil;
import Model.Student;
import Model.dto.StudentDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    public int create(Student studentData) {
        String query = "INSERT INTO Student (StudentID, Name, Email, BookID, Department) VALUES (?, ?, ?, ?, ?)";
        int affectedRows = -1;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, studentData.getStudentID());
            pst.setString(2, studentData.getName());
            pst.setString(3, studentData.getEmail());
            pst.setInt(4, studentData.getBookID());
            pst.setString(5,studentData.getDepartment());
            affectedRows = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRows;
    }

    public boolean update(StudentDto studentDto) {
        String query = "UPDATE Student SET Name = ?, Email = ?, BookID = ?, Department = ? WHERE StudentID = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, studentDto.getName());
            pst.setString(2, studentDto.getEmail());
            pst.setInt(3, studentDto.getBookID());
            pst.setString(4, studentDto.getDepartment());
            pst.setInt(5, studentDto.getStudentID());
            int affectedRows = pst.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean delete(int studentID) {
        String query = "DELETE FROM Student WHERE StudentID = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, studentID);
            int affectedRows = pst.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public List<Student> getAllStudents()  throws SQLException{
            List<Student> students = new ArrayList<>();
            String query = "SELECT * FROM Student";

            try (Connection conn = DatabaseUtil.getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    Student student = getFromResultSet(rs);
                    if (student != null) {
                        students.add(student);
                    }
                }
            }
            return students;
        }

    private Student getFromResultSet(ResultSet rs) {
        try {
            int studentID = rs.getInt("StudentID");
            String name = rs.getString("Name");
            String email = rs.getString("Email");
            int bookID = rs.getInt("BookID");
            String department = rs.getString("Department");

            return new Student(studentID, name, email, bookID, department);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<Student> getStudentsWithBooks() throws SQLException {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM Student WHERE BookID IS NOT NULL";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstm = conn.prepareStatement(query);
             ResultSet resultSet = pstm.executeQuery()) {

            while (resultSet.next()) {
                int studentID = resultSet.getInt("studentID");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                int bookID = resultSet.getInt("bookID");
                String department = resultSet.getString("department");

                students.add(new Student(studentID, name, email, bookID, department));
            }
        }

        return students;
    }


}
