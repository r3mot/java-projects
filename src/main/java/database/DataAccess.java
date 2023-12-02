package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.Product;

public class DataAccess {

  private final ConnectionManager connection;

  public DataAccess() {
    connection = new ConnectionManager();
  }

  public Product getProductById(int productId) {
    PreparedStatement ps;
    ResultSet rs;

    final String query = "SELECT * FROM Product WHERE ProductID = ?";

    try {
      ps = connection.connect().prepareStatement(query);
      ps.setInt(1, productId);
      rs = ps.executeQuery();

      if (rs.next()) {
        Product product = new Product(
          rs.getInt("ProductID"),
          rs.getString("ProductName"),
          rs.getInt("ProductQuantity"),
          rs.getFloat("ProductPrice"),
          rs.getString("ProductDescription"),
          rs.getInt("TypeID")
        );
        ps.close();
        rs.close();
        return product;
      } else {
        ps.close();
        rs.close();
        return null;
      }
    } catch (Exception e) {
      // exit gracefully
      e.printStackTrace();
      return null;
    } finally {
      connection.close();
    }
  }

  public List<Product> getProductsByType(String typeName) {
    PreparedStatement ps;
    ResultSet rs;

    List<Product> products = new ArrayList<>();

    final String query =
      "SELECT * FROM Product " +
      "JOIN ProductType ON Product.TypeID = ProductType.TypeID " +
      "WHERE ProductType.TypeName = ?";

    try {
      ps = connection.connect().prepareStatement(query);
      ps.setString(1, typeName);
      rs = ps.executeQuery();

      while (rs.next()) {
        Product product = new Product(
          rs.getInt("ProductID"),
          rs.getString("ProductName"),
          rs.getInt("ProductQuantity"),
          rs.getFloat("ProductPrice"),
          rs.getString("ProductDescription"),
          rs.getInt("TypeID")
        );
        products.add(product);
      }

      ps.close();
      rs.close();
      return products;
    } catch (Exception e) {
      e.printStackTrace();
      return new ArrayList<>();
    } finally {
      connection.close();
    }
  }
}
