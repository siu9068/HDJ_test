package kbl.test.hdj.utils;

import java.util.UUID;

public class CommonUtil {
    public static String getRandomString(int start, int end){
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString().replace("-", "");
        return uuidString.substring(start, end);
    }
}
