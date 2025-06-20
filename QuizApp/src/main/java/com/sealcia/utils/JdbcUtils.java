package com.sealcia.utils;

public class JdbcUtils {
    private static JdbcUtils instace;
    static {
        
    }
    private JdbcUtils() {
    
    }

    public static JdbcUtils getInstance() {
        if (instace == null) {
            instace = new JdbcUtils();
        }
        return instace;
    }
}
