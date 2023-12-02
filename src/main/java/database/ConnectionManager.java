package database;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class ConnectionManager {

  private final String DATABASE_URL;
  private final String DATABASE_UN;
  private final String DATABASE_PW;
  private final String JDBC_DRIVER;

  private Connection connection;

  /**
   * Make sure to include a config.properties file in the resources folder.
   * There is an example file in the resources folder called config.properties.example
   * Change the values in the example file to match your database configuration
   */
  public ConnectionManager() {
    Properties prop = new Properties();
    try (
      InputStream input = getClass()
        .getClassLoader()
        .getResourceAsStream("config.properties")
    ) {
      prop.load(input);
      DATABASE_URL = prop.getProperty("DATABASE_URL");
      DATABASE_UN = prop.getProperty("DATABASE_USERNAME");
      DATABASE_PW = prop.getProperty("DATABASE_PASSWORD");
      JDBC_DRIVER = prop.getProperty("JDBC_DRIVER");
    } catch (Exception e) {
      throw new RuntimeException(
        "Error reading database configuration file",
        e
      );
    }
  }

  public Connection connect() {
    try {
      Class.forName(JDBC_DRIVER);
      connection =
        java.sql.DriverManager.getConnection(
          DATABASE_URL,
          DATABASE_UN,
          DATABASE_PW
        );
    } catch (Exception e) {
      e.printStackTrace();
    }

    return connection;
  }

  public void close() {
    try {
      connection.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
