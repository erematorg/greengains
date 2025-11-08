package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ser.impl.ReadOnlyClassToSerializerMap;
import com.fasterxml.jackson.databind.util.TypeKey;
import java.util.function.BiConsumer;

public final /* synthetic */ class a implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ReadOnlyClassToSerializerMap f6564a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ ReadOnlyClassToSerializerMap.Bucket[] f6565b;

    public /* synthetic */ a(ReadOnlyClassToSerializerMap readOnlyClassToSerializerMap, ReadOnlyClassToSerializerMap.Bucket[] bucketArr) {
        this.f6564a = readOnlyClassToSerializerMap;
        this.f6565b = bucketArr;
    }

    public final void accept(Object obj, Object obj2) {
        ReadOnlyClassToSerializerMap.Bucket[] bucketArr = this.f6565b;
        this.f6564a.lambda$new$0(bucketArr, (TypeKey) obj, (JsonSerializer) obj2);
    }
}
