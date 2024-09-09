package Model;

public class ReturnBook {
    private int studentID;
    private int bookID;

    public ReturnBook(int studentID, int bookID) {
        this.studentID = studentID;
        this.bookID = bookID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }
}
