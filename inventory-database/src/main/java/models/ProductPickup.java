package models;

import java.sql.Date;

public class ProductPickup {

  private int supplierId;
  private int warehouseId;
  private Date pickupDate;

  public ProductPickup(int supplierId, int warehouseId, Date pickupDate) {
    this.supplierId = supplierId;
    this.warehouseId = warehouseId;
    this.pickupDate = pickupDate;
  }

  public int supplierId() {
    return supplierId;
  }

  public int warehouseId() {
    return warehouseId;
  }

  public Date pickupDate() {
    return pickupDate;
  }

  @Override
  public String toString() {
    return (
      "ProductPickup{" +
      "supplierId=" +
      supplierId +
      ", warehouseId=" +
      warehouseId +
      ", pickupDate=" +
      pickupDate +
      '}'
    );
  }
}
