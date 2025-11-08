package com.sun.jna.win32;

import com.sun.jna.Library;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public interface W32APIOptions extends StdCallLibrary {
    public static final Map<String, Object> ASCII_OPTIONS;
    public static final Map<String, Object> DEFAULT_OPTIONS;
    public static final Map<String, Object> UNICODE_OPTIONS;

    static {
        Map<String, Object> unmodifiableMap = Collections.unmodifiableMap(new HashMap<String, Object>() {
            private static final long serialVersionUID = 1;

            {
                put(Library.OPTION_TYPE_MAPPER, W32APITypeMapper.UNICODE);
                put(Library.OPTION_FUNCTION_MAPPER, W32APIFunctionMapper.UNICODE);
            }
        });
        UNICODE_OPTIONS = unmodifiableMap;
        Map<String, Object> unmodifiableMap2 = Collections.unmodifiableMap(new HashMap<String, Object>() {
            private static final long serialVersionUID = 1;

            {
                put(Library.OPTION_TYPE_MAPPER, W32APITypeMapper.ASCII);
                put(Library.OPTION_FUNCTION_MAPPER, W32APIFunctionMapper.ASCII);
            }
        });
        ASCII_OPTIONS = unmodifiableMap2;
        if (Boolean.getBoolean("w32.ascii")) {
            unmodifiableMap = unmodifiableMap2;
        }
        DEFAULT_OPTIONS = unmodifiableMap;
    }
}
