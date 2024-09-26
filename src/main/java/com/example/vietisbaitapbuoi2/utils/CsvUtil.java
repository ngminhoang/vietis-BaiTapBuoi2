package com.example.vietisbaitapbuoi2.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvUtil {

    public static <T> List<T> importCsv(MultipartFile file, Class<T> clazz) throws IOException {
        List<T> result = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String headerLine = reader.readLine();
            if (headerLine == null) {
                throw new IllegalArgumentException("File CSV không có dữ liệu");
            }
            String delimiter = detectDelimiter(headerLine);
            String[] headers = headerLine.split(delimiter);

            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(delimiter);
                if (headers.length > values.length) {
                    String[] extendedValues = new String[headers.length];
                    System.arraycopy(values, 0, extendedValues, 0, values.length);
                    for (int i = values.length; i < headers.length; i++) {
                        extendedValues[i] = "0";
                    }
                    values = extendedValues;
                }
                T entity = mapToEntity(headers, values, clazz);
                result.add(entity);
            }
        }

        return result;
    }

    private static String detectDelimiter(String headerLine) {
        String[] possibleDelimiters = {",", ";", "\t"};
        for (String delimiter : possibleDelimiters) {
            if (headerLine.contains(delimiter)) {
                return delimiter;
            }
        }
        return ",";
    }


    private static <T> T mapToEntity(String[] headers, String[] values, Class<T> clazz) {
        try {
            T instance = clazz.getDeclaredConstructor().newInstance();
            Map<String, String> csvData = new HashMap<>();
            for (int i = 0; i < headers.length; i++) {
                csvData.put(headers[i].trim(), values[i].trim());
            }
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(CsvColumn.class)) {
                    CsvColumn csvColumn = field.getAnnotation(CsvColumn.class);
                    String[] possibleColumnNames = csvColumn.name();
                    String valueToSet = null;
                    for (String columnName : possibleColumnNames) {
                        if (csvData.containsKey(columnName.trim())) {
                            valueToSet = csvData.get(columnName);
                            break;
                        }
                    }
                    if (csvColumn.required() && (valueToSet == null || valueToSet.isEmpty())) {
                        throw new IllegalArgumentException("Cột " + String.join(", ", possibleColumnNames) + " là bắt buộc nhưng không tìm thấy giá trị.");
                    }
                    if (valueToSet != null) {
                        field.setAccessible(true);
                        field.set(instance, convertValue(valueToSet, field.getType()));
                    }
                }
            }
            return instance;
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi ánh xạ CSV vào entity", e);
        }
    }


    private static Object convertValue(String value, Class<?> fieldType) {
        if (fieldType == int.class || fieldType == Integer.class) {
            return Integer.parseInt(value);
        } else if (fieldType == double.class || fieldType == Double.class) {
            return Double.parseDouble(value);
        } else if (fieldType == boolean.class || fieldType == Boolean.class) {
            return Boolean.parseBoolean(value);
        }
        return value;
    }
}
