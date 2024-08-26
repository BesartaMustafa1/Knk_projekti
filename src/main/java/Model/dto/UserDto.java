package Model.dto;

public class UserDto {
    private String firstName;
    private String lastName;
    private String Email;
    private String Password;
    private String CPassword;

    public UserDto(String firstName, String lastName, String Email, String Password, String CPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.Email = Email;
        this.Password = Password;
        this.CPassword = CPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }


    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public String getConfirmPassword() {
        return CPassword;
    }
}