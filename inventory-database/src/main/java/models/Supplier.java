package models;

public class Supplier {

  private int id;
  private String name;
  private String email;
  private String phone;
  private Double deliveryCost;

  public Supplier(
    String name,
    String email,
    String phone,
    Double deliveryCost
  ) {
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.deliveryCost = deliveryCost;
  }

  public Supplier(
    int id,
    String name,
    String email,
    String phone,
    Double deliveryCost
  ) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.deliveryCost = deliveryCost;
  }

  public int supplierId() {
    return id;
  }

  public String supplierName() {
    return name;
  }

  public String supplierEmail() {
    return email;
  }

  public String supplierPhone() {
    return phone;
  }

  public Double supplierDeliveryCost() {
    return deliveryCost;
  }

  public void setSupplierId(int id) {
    this.id = id;
  }
}
