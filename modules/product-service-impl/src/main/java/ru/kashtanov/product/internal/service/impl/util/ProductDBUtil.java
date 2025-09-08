package ru.kashtanov.product.internal.service.impl.util;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import ru.kashtanov.product.internal.service.impl.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDBUtil {

    public static void createProduct(String productName, String productDescription) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DataAccess.getConnection();
            String sql = "INSERT INTO product (product_name, product_description) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, productName);
            preparedStatement.setString(2, productDescription);
            preparedStatement.executeUpdate();
        } finally {
            DataAccess.cleanUp(connection, preparedStatement);
        }
    }

    public static List<Product> getAllProducts() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Product> products = new ArrayList<>();

        try {
            connection = DataAccess.getConnection();
            String sql = "SELECT * FROM product";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product(
                        resultSet.getLong("product_id"),
                        resultSet.getString("product_name"),
                        resultSet.getString("product_description")
                );
                products.add(product);
            }
        } finally {
            DataAccess.cleanUp(connection, preparedStatement, resultSet);
        }

        return products;
    }

    public static Product getProductById(long id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DataAccess.getConnection();
            String sql = "SELECT * FROM product WHERE product_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Product(
                        resultSet.getLong("product_id"),
                        resultSet.getString("product_name"),
                        resultSet.getString("product_description")
                );
            }
        } finally {
            DataAccess.cleanUp(connection, preparedStatement, resultSet);
        }

        return null;
    }

    public static void updateProduct(long id, String productName, String productDescription) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DataAccess.getConnection();
            String sql = "UPDATE product SET product_name = ?, product_description = ? WHERE product_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, productName);
            preparedStatement.setString(2, productDescription);
            preparedStatement.setLong(3, id);
            preparedStatement.executeUpdate();
        } finally {
            DataAccess.cleanUp(connection, preparedStatement);
        }
    }

    public static void deleteProduct(long id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DataAccess.getConnection();
            String sql = "DELETE FROM product WHERE product_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } finally {
            DataAccess.cleanUp(connection, preparedStatement);
        }
    }

    // Create table if not exists (for development)
    public static void createTable() throws SQLException {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DataAccess.getConnection();
            statement = connection.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS product (" +
                    "product_id BIGSERIAL PRIMARY KEY, " +
                    "product_name VARCHAR(255) NOT NULL, " +
                    "product_description TEXT" +
                    ")";

            statement.executeUpdate(sql);
        } finally {
            DataAccess.cleanUp(connection, statement);
        }
    }
}