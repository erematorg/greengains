package com.google.gson.internal.bind;

import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.Enum;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class EnumTypeAdapter<T extends Enum<T>> extends TypeAdapter<T> {
    static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() {
        /* JADX WARNING: type inference failed for: r3v0, types: [com.google.gson.reflect.TypeToken<T>, com.google.gson.reflect.TypeToken] */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public <T> com.google.gson.TypeAdapter<T> create(com.google.gson.Gson r2, com.google.gson.reflect.TypeToken<T> r3) {
            /*
                r1 = this;
                java.lang.Class r1 = r3.getRawType()
                java.lang.Class<java.lang.Enum> r2 = java.lang.Enum.class
                boolean r3 = r2.isAssignableFrom(r1)
                r0 = 0
                if (r3 == 0) goto L_0x0020
                if (r1 != r2) goto L_0x0010
                goto L_0x0020
            L_0x0010:
                boolean r2 = r1.isEnum()
                if (r2 != 0) goto L_0x001a
                java.lang.Class r1 = r1.getSuperclass()
            L_0x001a:
                com.google.gson.internal.bind.EnumTypeAdapter r2 = new com.google.gson.internal.bind.EnumTypeAdapter
                r2.<init>(r1)
                return r2
            L_0x0020:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.bind.EnumTypeAdapter.AnonymousClass1.create(com.google.gson.Gson, com.google.gson.reflect.TypeToken):com.google.gson.TypeAdapter");
        }
    };
    private final Map<T, String> constantToName;
    private final Map<String, T> nameToConstant;
    private final Map<String, T> stringToConstant;

    private EnumTypeAdapter(Class<T> cls) {
        this.nameToConstant = new HashMap();
        this.stringToConstant = new HashMap();
        this.constantToName = new HashMap();
        try {
            Field[] declaredFields = cls.getDeclaredFields();
            int i3 = 0;
            for (Field field : declaredFields) {
                if (field.isEnumConstant()) {
                    declaredFields[i3] = field;
                    i3++;
                }
            }
            Field[] fieldArr = (Field[]) Arrays.copyOf(declaredFields, i3);
            AccessibleObject.setAccessible(fieldArr, true);
            for (Field field2 : fieldArr) {
                Enum enumR = (Enum) field2.get((Object) null);
                String name = enumR.name();
                String str = enumR.toString();
                SerializedName serializedName = (SerializedName) field2.getAnnotation(SerializedName.class);
                if (serializedName != null) {
                    name = serializedName.value();
                    for (String put : serializedName.alternate()) {
                        this.nameToConstant.put(put, enumR);
                    }
                }
                this.nameToConstant.put(name, enumR);
                this.stringToConstant.put(str, enumR);
                this.constantToName.put(enumR, name);
            }
        } catch (IllegalAccessException e3) {
            throw new AssertionError(e3);
        }
    }

    public T read(JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        String nextString = jsonReader.nextString();
        T t2 = (Enum) this.nameToConstant.get(nextString);
        return t2 == null ? (Enum) this.stringToConstant.get(nextString) : t2;
    }

    public void write(JsonWriter jsonWriter, T t2) throws IOException {
        jsonWriter.value(t2 == null ? null : this.constantToName.get(t2));
    }
}
