package logging.enums;

import java.util.Map;
import java.util.TreeMap;

public enum LogModule {

    PRODUCT_SERVICE(1, "product-service"),
    ORDER_SERVICE(2, "order-service"),
    INVENTORY_SERVICE(3, "inventory-service");

    private int id;
    private String name;
    private static Map<Integer, LogModule> map;

    static {
        map = new TreeMap<>();
        for (LogModule logModule : LogModule.values()) {
            map.put(logModule.id, logModule);
        }
    }

    LogModule(int id, String name) {
        id = this.id;
        name = this.name;
    }
}
