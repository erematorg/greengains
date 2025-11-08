package com.google.devtools.ksp.processing;

import com.google.devtools.ksp.symbol.KSType;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b!\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u0012\u0010\b\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0005R\u0012\u0010\n\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0005R\u0012\u0010\f\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u0005R\u0012\u0010\u000e\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0005R\u0012\u0010\u0010\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0005R\u0012\u0010\u0012\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0005R\u0012\u0010\u0014\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0005R\u0012\u0010\u0016\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0005R\u0012\u0010\u0018\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0005R\u0012\u0010\u001a\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0005R\u0012\u0010\u001c\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0005R\u0012\u0010\u001e\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0005R\u0012\u0010 \u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\u0005R\u0012\u0010\"\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b#\u0010\u0005¨\u0006$À\u0006\u0003"}, d2 = {"Lcom/google/devtools/ksp/processing/KSBuiltIns;", "", "annotationType", "Lcom/google/devtools/ksp/symbol/KSType;", "getAnnotationType", "()Lcom/google/devtools/ksp/symbol/KSType;", "anyType", "getAnyType", "arrayType", "getArrayType", "booleanType", "getBooleanType", "byteType", "getByteType", "charType", "getCharType", "doubleType", "getDoubleType", "floatType", "getFloatType", "intType", "getIntType", "iterableType", "getIterableType", "longType", "getLongType", "nothingType", "getNothingType", "numberType", "getNumberType", "shortType", "getShortType", "stringType", "getStringType", "unitType", "getUnitType", "api"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface KSBuiltIns {
    @NotNull
    KSType getAnnotationType();

    @NotNull
    KSType getAnyType();

    @NotNull
    KSType getArrayType();

    @NotNull
    KSType getBooleanType();

    @NotNull
    KSType getByteType();

    @NotNull
    KSType getCharType();

    @NotNull
    KSType getDoubleType();

    @NotNull
    KSType getFloatType();

    @NotNull
    KSType getIntType();

    @NotNull
    KSType getIterableType();

    @NotNull
    KSType getLongType();

    @NotNull
    KSType getNothingType();

    @NotNull
    KSType getNumberType();

    @NotNull
    KSType getShortType();

    @NotNull
    KSType getStringType();

    @NotNull
    KSType getUnitType();
}
