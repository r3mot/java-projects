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
   * Get all products from the database
   * Returns a 2D array of the products to be displayed in the table
   * @param typeName
   * @return
   */
  public Object[][] getProductsByType(String typeName) {
    List<Product> products = dataAccess.getProductsByType(typeName);

    return products == null
      ? Constants.Table.emptyTable
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

  public Object[][] getProductById(int productId) {
    Product product = dataAccess.getProductById(productId);

    return product == null
      ? Constants.Table.emptyTable
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
