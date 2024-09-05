package Model.dto;

public class StudentDto {
    private int StudentID;
    private String Name;
    private String Email;
    private int BookID;
    private String Department;

    public StudentDto(int studentID, String name, String email, int bookID, String department) {
        StudentID = studentID;
        Name = name;
        Email = email;
        BookID = bookID;
        Department = department;
    }

    public int getStudentID() {
        return StudentID;
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }

    public int getBookID() {
        return BookID;
    }

    public String getDepartment() {
        return Department;
    }
}
