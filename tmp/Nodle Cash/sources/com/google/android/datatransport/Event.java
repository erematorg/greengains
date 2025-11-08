package com.google.android.datatransport;

import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Event<T> {
    public static <T> Event<T> ofData(int i3, T t2, @Nullable ProductData productData, @Nullable EventContext eventContext) {
        return new AutoValue_Event(Integer.valueOf(i3), t2, Priority.DEFAULT, productData, eventContext);
    }

    public static <T> Event<T> ofTelemetry(int i3, T t2, @Nullable ProductData productData, @Nullable EventContext eventContext) {
        return new AutoValue_Event(Integer.valueOf(i3), t2, Priority.VERY_LOW, productData, eventContext);
    }

    public static <T> Event<T> ofUrgent(int i3, T t2, @Nullable ProductData productData, @Nullable EventContext eventContext) {
        return new AutoValue_Event(Integer.valueOf(i3), t2, Priority.HIGHEST, productData, eventContext);
    }

    @Nullable
    public abstract Integer getCode();

    @Nullable
    public abstract EventContext getEventContext();

    public abstract T getPayload();

    public abstract Priority getPriority();

    @Nullable
    public abstract ProductData getProductData();

    public static <T> Event<T> ofData(int i3, T t2, @Nullable ProductData productData) {
        return new AutoValue_Event(Integer.valueOf(i3), t2, Priority.DEFAULT, productData, (EventContext) null);
    }

    public static <T> Event<T> ofTelemetry(int i3, T t2, @Nullable ProductData productData) {
        return new AutoValue_Event(Integer.valueOf(i3), t2, Priority.VERY_LOW, productData, (EventContext) null);
    }

    public static <T> Event<T> ofUrgent(int i3, T t2, @Nullable ProductData productData) {
        return new AutoValue_Event(Integer.valueOf(i3), t2, Priority.HIGHEST, productData, (EventContext) null);
    }

    public static <T> Event<T> ofData(int i3, T t2, @Nullable EventContext eventContext) {
        return new AutoValue_Event(Integer.valueOf(i3), t2, Priority.DEFAULT, (ProductData) null, eventContext);
    }

    public static <T> Event<T> ofTelemetry(int i3, T t2, @Nullable EventContext eventContext) {
        return new AutoValue_Event(Integer.valueOf(i3), t2, Priority.VERY_LOW, (ProductData) null, eventContext);
    }

    public static <T> Event<T> ofUrgent(int i3, T t2, @Nullable EventContext eventContext) {
        return new AutoValue_Event(Integer.valueOf(i3), t2, Priority.HIGHEST, (ProductData) null, eventContext);
    }

    public static <T> Event<T> ofData(int i3, T t2) {
        return new AutoValue_Event(Integer.valueOf(i3), t2, Priority.DEFAULT, (ProductData) null, (EventContext) null);
    }

    public static <T> Event<T> ofTelemetry(int i3, T t2) {
        return new AutoValue_Event(Integer.valueOf(i3), t2, Priority.VERY_LOW, (ProductData) null, (EventContext) null);
    }

    public static <T> Event<T> ofUrgent(int i3, T t2) {
        return new AutoValue_Event(Integer.valueOf(i3), t2, Priority.HIGHEST, (ProductData) null, (EventContext) null);
    }

    public static <T> Event<T> ofData(T t2, @Nullable ProductData productData, @Nullable EventContext eventContext) {
        return new AutoValue_Event((Integer) null, t2, Priority.DEFAULT, productData, eventContext);
    }

    public static <T> Event<T> ofTelemetry(T t2, @Nullable ProductData productData, @Nullable EventContext eventContext) {
        return new AutoValue_Event((Integer) null, t2, Priority.VERY_LOW, productData, eventContext);
    }

    public static <T> Event<T> ofUrgent(T t2, @Nullable ProductData productData, @Nullable EventContext eventContext) {
        return new AutoValue_Event((Integer) null, t2, Priority.HIGHEST, productData, eventContext);
    }

    public static <T> Event<T> ofData(T t2, @Nullable ProductData productData) {
        return new AutoValue_Event((Integer) null, t2, Priority.DEFAULT, productData, (EventContext) null);
    }

    public static <T> Event<T> ofTelemetry(T t2, @Nullable ProductData productData) {
        return new AutoValue_Event((Integer) null, t2, Priority.VERY_LOW, productData, (EventContext) null);
    }

    public static <T> Event<T> ofUrgent(T t2, @Nullable ProductData productData) {
        return new AutoValue_Event((Integer) null, t2, Priority.HIGHEST, productData, (EventContext) null);
    }

    public static <T> Event<T> ofData(T t2, @Nullable EventContext eventContext) {
        return new AutoValue_Event((Integer) null, t2, Priority.DEFAULT, (ProductData) null, eventContext);
    }

    public static <T> Event<T> ofTelemetry(T t2, @Nullable EventContext eventContext) {
        return new AutoValue_Event((Integer) null, t2, Priority.VERY_LOW, (ProductData) null, eventContext);
    }

    public static <T> Event<T> ofUrgent(T t2, @Nullable EventContext eventContext) {
        return new AutoValue_Event((Integer) null, t2, Priority.HIGHEST, (ProductData) null, eventContext);
    }

    public static <T> Event<T> ofData(T t2) {
        return new AutoValue_Event((Integer) null, t2, Priority.DEFAULT, (ProductData) null, (EventContext) null);
    }

    public static <T> Event<T> ofTelemetry(T t2) {
        return new AutoValue_Event((Integer) null, t2, Priority.VERY_LOW, (ProductData) null, (EventContext) null);
    }

    public static <T> Event<T> ofUrgent(T t2) {
        return new AutoValue_Event((Integer) null, t2, Priority.HIGHEST, (ProductData) null, (EventContext) null);
    }
}
