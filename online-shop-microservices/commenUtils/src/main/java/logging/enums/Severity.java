package logging.enums;

import java.util.Map;
import java.util.TreeMap;

public enum Severity {

    WARNING(1, "warning"),
    CRITICAL(2, "critical"),
    ERROR(3, "error");

    private int id;
    private String name;
    private static Map<Integer, Severity> map;

    static {
        map = new TreeMap<>();
        for (Severity severity : Severity.values()) {
            map.put(severity.id, severity);
        }
    }

    Severity(int id, String name) {
        id = this.id;
        name = this.name;
    }
}
