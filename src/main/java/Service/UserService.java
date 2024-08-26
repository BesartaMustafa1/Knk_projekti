package Service;

import Model.User;
import Model.dto.CreateUserDto;
import Model.dto.LoginUserDto;
import Model.dto.UserDto;
import Repository.UserRepository;

import java.sql.SQLException;

public class UserService {
    public static boolean signUp(UserDto userData) throws SQLException {
        String password = userData.getPassword();
        String confirmPassword = userData.getConfirmPassword();

        if (!password.equals(confirmPassword)) {
            return false;
        }


        String Salt = PasswordHasher.generateSalt();
        String passwordHash = PasswordHasher.generateSaltedHash(
                password, Salt
        );

        CreateUserDto createUserData = new CreateUserDto(
                userData.getFirstName(),
                userData.getLastName(),
                userData.getEmail(),
                Salt,
                passwordHash
        );

        return UserRepository.create(createUserData);
    }
    public static boolean login(LoginUserDto loginData){
        User user = UserRepository.getByEmail(loginData.getEmail());
        if(user == null){
            return false;
        }

        String password = loginData.getPassword();
        String salt = user.getSalt();
        String passwordHash = user.getPasswordHash();

        return PasswordHasher.compareSaltedHash(
                password, salt, passwordHash
        );
    }


}
