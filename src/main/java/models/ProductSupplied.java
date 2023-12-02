package models;

import java.sql.Date;

public class ProductSupplied {

  private int supplierId;
  private int productId;
  private Date contactDate;

  public ProductSupplied(int supplierId, int productId, Date contactDate) {
    this.supplierId = supplierId;
    this.productId = productId;
    this.contactDate = contactDate;
  }

  public int supplierId() {
    return supplierId;
  }

  public int productId() {
    return productId;
  }

  public Date contactDate() {
    return contactDate;
  }

  @Override
  public String toString() {
    return (
      "ProductSupplied{" +
      "supplierId=" +
      supplierId +
      ", productId=" +
      productId +
      ", contactDate=" +
      contactDate +
      '}'
    );
  }
}
