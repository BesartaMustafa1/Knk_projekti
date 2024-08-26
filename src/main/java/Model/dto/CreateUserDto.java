package Model.dto;

public class CreateUserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String Salt;
    private String passwordHash;

    public CreateUserDto(String firstName, String lastName, String email, String salt, String passwordHash) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        Salt = salt;
        this.passwordHash = passwordHash;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getSalt() {
        return Salt;
    }

    public String getPasswordHash() {
        return passwordHash;
    }
}
