package framework.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * JsonReader - Doc test data tu file JSON bang Jackson.
 * Ho tro doc list of maps de dung voi @DataProvider.
 */
public class JsonReader {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Doc file JSON tu resources va tra ve List<Map<String, String>>.
     * Moi map la 1 dong data, key la ten truong, value la gia tri.
     *
     * @param resourcePath duong dan tu thu muc resources
     * @return list cac map chua du lieu
     */
    public static List<Map<String, String>> readJsonData(String resourcePath) {
        try (InputStream is = JsonReader.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (is == null) {
                throw new RuntimeException("Khong tim thay file JSON: " + resourcePath);
            }
            return objectMapper.readValue(is, new TypeReference<List<Map<String, String>>>() {});
        } catch (IOException e) {
            throw new RuntimeException("Khong the doc file JSON: " + resourcePath, e);
        }
    }

    /**
     * Chuyen List<Map> thanh Object[][] de dung lam @DataProvider.
     */
    public static Object[][] toDataProviderArray(String resourcePath) {
        List<Map<String, String>> data = readJsonData(resourcePath);
        Object[][] result = new Object[data.size()][1];
        for (int i = 0; i < data.size(); i++) {
            result[i][0] = data.get(i);
        }
        return result;
    }
}
