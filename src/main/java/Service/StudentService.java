package Service;

import Model.Student;
import Model.dto.StudentDto;
import Repository.StudentRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public class StudentService {
    private final StudentRepository studentRepository = new StudentRepository();

    public int create(Student studentData) {
        return studentRepository.create(studentData);
    }
    public boolean updated(Student student) {
        StudentDto studentDto = new StudentDto(
                student.getStudentID(),
                student.getName(),
                student.getEmail(),
                student.getBookID(),
                student.getDepartment()
        );
        return studentRepository.update(studentDto);
    }

    public ObservableList<Student> getAllStudents() throws SQLException {
        List<Student> students = studentRepository.getAllStudents();
        return FXCollections.observableArrayList(students);
    }
    public List<Student> getStudentsWithBooks() throws SQLException {
        return studentRepository.getStudentsWithBooks();
    }

    public boolean delete(int StudentID) {
        return studentRepository.delete(StudentID);
    }
}
