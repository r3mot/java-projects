package database;

import java.util.ArrayList;
import java.util.List;
import models.Product;
import models.Supplier;
import views.Constants;

public class DataService {

  private final DataAccess dataAccess;

  public DataService() {
    dataAccess = new DataAccess();
  }

  /**
   * Get all products from the database as a List<Product>
   * Stream the list and map each product to an Object[]
   *
   * @param typeName - the name of the product type
   * @return Object[][] - a 2D array of products
   */
  public Object[][] getProductsByType(String typeName) {
    List<Product> products = dataAccess.getProductsByType(typeName);

    return products == null
      ? Constants.Table.emptyTable // see views.Constants
      : products
        .stream()
        .map(product ->
          new Object[] {
            product.productId(),
            product.productName(),
            product.productQuantity(),
            product.productPrice(),
            product.productDescription(),
            product.productTypeId(),
          }
        )
        .toArray(Object[][]::new);
  }

  /**
   * Get a single product by its ID
   *
   * @param productId - the ID of the product
   * @return Object[][] - a 2D array of product
   */
  public Object[][] getProductById(int productId) {
    Product product = dataAccess.getProductById(productId);

    return product == null
      ? Constants.Table.emptyTable // see views.Constants
      : new Object[][] {
        {
          product.productId(),
          product.productName(),
          product.productQuantity(),
          product.productPrice(),
          product.productDescription(),
          product.productTypeId(),
        },
      };
  }

  public List<Supplier> getLastThreeSuppliers() {
    // TODO: implement
    return new ArrayList<>();
  }
}
