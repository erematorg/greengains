package com.google.zxing;

import java.util.List;

public enum DecodeHintType {
    OTHER(Object.class),
    PURE_BARCODE(r4),
    POSSIBLE_FORMATS(List.class),
    TRY_HARDER(r4),
    CHARACTER_SET(String.class),
    ALLOWED_LENGTHS(r9),
    ASSUME_CODE_39_CHECK_DIGIT(r4),
    ASSUME_GS1(r4),
    RETURN_CODABAR_START_END(r4),
    NEED_RESULT_POINT_CALLBACK(ResultPointCallback.class),
    ALLOWED_EAN_EXTENSIONS(r9),
    ALSO_INVERTED(r4);
    
    private final Class<?> valueType;

    private DecodeHintType(Class<?> cls) {
        this.valueType = cls;
    }

    public Class<?> getValueType() {
        return this.valueType;
    }
}
