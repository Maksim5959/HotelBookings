package com.chuyashkou.hotels_booking.util;

public enum DbMappingConstant {

    DB_URL("db.url"),
    DB_USER_NAME("db.user.name"),
    DB_USER_PASSWORD("db.user.password"),
    DB_DRIVER("db.driver"),
    DB_POOL_SIZE("db.pool.size");

    private final String key;

    DbMappingConstant(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
