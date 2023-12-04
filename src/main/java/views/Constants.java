package views;

/**
 * This is kind of scuffed but it works for now
 */
public final class Constants {

  public enum Options {
    GET_PRODUCT_BY_ID(0),
    GET_PRODUCTS_BY_PRODUCT_TYPE(1),
    GET_WAREHOUSE_BY_AREA_CODE(2);

    int value = 0;

    Options(int value) {
      this.value = value;
    }

    public int value() {
      return value;
    }
  }

  public static final String[] dropdownOptions = {
    "Get Product By ID",
    "Get Products By Product Type",
    "Get Warehouse By Area Code",
  };

  public static final class Table {

    public static final String[] productCols = {
      "ProductId",
      "ProductName",
      "ProductQuantity",
      "ProductPrice",
      "ProductDescription",
      "TypeId",
    };

    public static final String[] warehouseCols = {
      "WarehouseName",
      "WarehouseAddress",
      "WarehousePhone",
    };
    public static final String[] defaultColumns = productCols;
    public static final Object[][] emptyTable = new Object[0][productCols.length];
  }

  /**
   * Get the columns for the table based on the index
   * @param index - the index of the dropdown
   * @return String[] - the columns for the table
   */
  public static String[] getColumns(int index) {
    Options option = Options.values()[index];
    switch (option) {
      case GET_PRODUCT_BY_ID:
        return Table.productCols;
      case GET_PRODUCTS_BY_PRODUCT_TYPE:
        return Table.productCols;
      case GET_WAREHOUSE_BY_AREA_CODE:
        return Table.warehouseCols;
      default:
        return Table.defaultColumns;
    }
  }
}
