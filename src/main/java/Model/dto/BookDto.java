package Model.dto;

public class BookDto {
    private int bookID;
    private String bookName;
    private String autor;
    private int quantity;

    public BookDto(int bookID, String bookName, String autor, int quantity) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.autor = autor;
        this.quantity = quantity;
    }

    public int getBookID() {
        return bookID;
    }


    public String getBookName() {
        return bookName;
    }


    public String getAutor() {
        return autor;
    }

    public int getQuantity() {
        return quantity;
    }


}
