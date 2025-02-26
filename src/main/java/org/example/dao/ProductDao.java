package org.example.dao;

import lombok.NoArgsConstructor;
import org.example.entity.Product;
import org.example.exception.DaoException;
import org.example.utils.ConnectionManager;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ProductDao implements Dao<Long, Product>{

    private static final ProductDao INSTANCE = new ProductDao();
    public static ProductDao getInstance(){
        return INSTANCE;
    }

    private static final String SAVE_SQL =
            "INSERT INTO order_booking.products(name, description, price, isavailable) VALUES (?, ?, ?, ?)";

    private static final String FIND_ALL_SQL =
            "SELECT id, name, description, price, isavailable FROM order_booking.products";

    @Override
    public boolean update(Product product) {
        return false;
    }

    @Override
    public List<Product> findAll() {
        try(var connection = ConnectionManager.getConnection();
        var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)){
            var resultSet = preparedStatement.executeQuery();

            List<Product> products = new ArrayList<>();

            while (resultSet.next()) {
                products.add(new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getBigDecimal("price"),
                        resultSet.getBoolean("isavailable")
                ));
            }
            return products;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Product save(Product product) {
        try(var connection = ConnectionManager.getConnection();
            var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setBigDecimal(3, product.getPrice());
            preparedStatement.setBoolean(4, product.getIsAvailable());

            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            product.setId(generatedKeys.getObject("id", Integer.class));

            return product;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
