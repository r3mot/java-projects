package models;

public class ProductType {

  private int typeId;
  private String typeName;
  private String typeDescription;
  private String perishibilityStatus;

  public ProductType(
    int typeId,
    String typeName,
    String typeDescription,
    String perishibilityStatus
  ) {
    this.typeId = typeId;
    this.typeName = typeName;
    this.typeDescription = typeDescription;
    this.perishibilityStatus = perishibilityStatus;
  }

  public int typeId() {
    return typeId;
  }

  public String typeName() {
    return typeName;
  }

  public String typeDescription() {
    return typeDescription;
  }

  public String perishibilityStatus() {
    return perishibilityStatus;
  }

  public void setTypeId(int typeId) {
    this.typeId = typeId;
  }
}
