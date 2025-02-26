package org.example.dao;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.example.entity.Role;
import org.example.entity.User;
import org.example.exception.DaoException;
import org.example.utils.ConnectionManager;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserDao implements Dao<Long, User> {

    private static final UserDao INSTANCE = new UserDao();

    public static UserDao getInstance() {
        return INSTANCE;
    }

    private static final String SAVE_SQL =
            "INSERT INTO order_booking.users(name, email, password, role) VALUES (?, ?, ?, ?)";

    private static final String GET_BY_EMAIL_AND_PASSWORD_SQL =
            "SELECT * FROM order_booking.users WHERE email = ? AND password = ?";

    @SneakyThrows
    public Optional<User> findByEmailAndPassword(String email, String password) {
        try(var connection = ConnectionManager.getConnection();
            var preparedStatment = connection.prepareStatement(GET_BY_EMAIL_AND_PASSWORD_SQL)){
            preparedStatment.setString(1, email);
            preparedStatment.setString(2, password);

            var resultSet = preparedStatment.executeQuery();
            User user = null;
            if(resultSet.next()) {
                user = buildEntity(resultSet);
            }
            return Optional.ofNullable(user);
        }
    }

    private User buildEntity(ResultSet resultSet) throws SQLException {
        return User.builder()
                .id(resultSet.getObject("id", Integer.class))
                .name(resultSet.getObject("name", String.class))
                .email(resultSet.getObject("email", String.class))
                .password(resultSet.getObject("password", String.class))
                .role(Role.find(resultSet.getObject("role", String.class)).orElse(null))
                .build();
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public List<User> findAll() {
        return List.of();
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public User save(User user) {
        try(var connection = ConnectionManager.getConnection();
        var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getRole().name());

            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            user.setId(generatedKeys.getObject("id", Integer.class));

            return user;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
