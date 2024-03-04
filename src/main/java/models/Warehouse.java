package models;

public class Warehouse {

  private int id;
  private String name;
  private String address;
  private String phone;

  public Warehouse(int id, String name, String address, String phone) {
    this.id = id;
    this.name = name;
    this.address = address;
    this.phone = phone;
  }

  public Warehouse(String name, String address, String phone) {
    this.name = name;
    this.address = address;
    this.phone = phone;
  }

  public int warehouseId() {
    return id;
  }

  public String warehouseName() {
    return name;
  }

  public String warehouseAddress() {
    return address;
  }

  public String warehousePhone() {
    return phone;
  }

  public void setWarehouseId(int id) {
    this.id = id;
  }
}
