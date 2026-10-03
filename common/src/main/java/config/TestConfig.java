package config;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class TestConfig {

    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream input = TestConfig.class
                .getClassLoader()
                .getResourceAsStream("test.properties")) {

            if (input == null) {
                throw new RuntimeException("Файл test.properties не найден");
            }

            PROPERTIES.load(new InputStreamReader(input, StandardCharsets.UTF_8));

        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить test.properties", e);
        }
    }

    public static String getBaseUrl() {
        return PROPERTIES.getProperty("base.url");
    }

    public static String getApiUrl() {
        return PROPERTIES.getProperty("api.url");
    }

    public static long getTimeout() {
        return Long.parseLong(PROPERTIES.getProperty("timeout"));
    }

    public static boolean isLoggingEnabled() {
        return Boolean.parseBoolean(PROPERTIES.getProperty("logging"));
    }

    public static String getAdminLogin() {
        return PROPERTIES.getProperty("admin.login");
    }

    public static String getAdminPassword() {
        return PROPERTIES.getProperty("admin.password");
    }

    public static String getProductName() {
        return PROPERTIES.getProperty("product.name");
    }

    public static int getProductPrice() {
        return Integer.parseInt(PROPERTIES.getProperty("product.price"));
    }
}