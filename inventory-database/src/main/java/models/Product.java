package models;

public class Product {

  private int id;
  private String name;
  private int quantity;
  private float price; // should probably be BigDecimal
  private String description;
  private int typeId;

  public Product(
    String name,
    int quantity,
    float price,
    String description,
    int typeId
  ) {
    this.name = name;
    this.quantity = quantity;
    this.price = price;
    this.description = description;
    this.typeId = typeId;
  }

  public Product(
    int id,
    String name,
    int quantity,
    float price,
    String description,
    int typeId
  ) {
    this.id = id;
    this.name = name;
    this.quantity = quantity;
    this.price = price;
    this.description = description;
    this.typeId = typeId;
  }

  public int productId() {
    return id;
  }

  public String productName() {
    return name;
  }

  public int productQuantity() {
    return quantity;
  }

  public float productPrice() {
    return price;
  }

  public String productDescription() {
    return description;
  }

  public int productTypeId() {
    return typeId;
  }

  public void setProductId(int id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return String.format(
      "Product: { id: %d, name: %s, quantity: %d, price: %f, description: %s, typeId: %d }",
      id,
      name,
      quantity,
      price,
      description,
      typeId
    );
  }
}
