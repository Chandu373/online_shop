package logging.enums;

import java.util.Map;
import java.util.TreeMap;

public enum LogType {

    INFO(1, "info"),
    WARNING(2, "warning"),
    DEBUG(3, "debug"),
    ERROR(4, "error");

    private int id;
    private String name;
    private static Map<Integer, LogType> map;

    static {
        map = new TreeMap<>();
        for (LogType logType : LogType.values()) {
            map.put(logType.id, logType);
        }
    }

    LogType(int id, String name) {
        id = this.id;
        name = this.name;
    }

}
