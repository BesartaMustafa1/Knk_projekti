package Model.filter;

import java.util.StringJoiner;

public class ReturnBookFilter {
    private int studentId;
    private int bookId;

    public ReturnBookFilter(int studentId, int bookId) {
        this.studentId = studentId;
        this.bookId = bookId;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getBookId() {
        return bookId;
    }

    public String buildQuery() {
        StringJoiner query = new StringJoiner(" AND ", " WHERE ", "");
        if (studentId != 0) {
            query.add("StudentID = ?");
        }
        if (bookId != 0) {
            query.add("BookID = ?");
        }
        if (query.length() == 7) {
            return "";
        }

        return query.toString();
    }
}
