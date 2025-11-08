package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.FieldSet;
import com.google.crypto.tink.shaded.protobuf.Internal;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

@CheckReturnValue
final class SchemaUtil {
    private static final int DEFAULT_LOOK_UP_START_NUMBER = 40;
    private static final Class<?> GENERATED_MESSAGE_CLASS = getGeneratedMessageClass();
    private static final UnknownFieldSchema<?, ?> PROTO2_UNKNOWN_FIELD_SET_SCHEMA = getUnknownFieldSetSchema(false);
    private static final UnknownFieldSchema<?, ?> PROTO3_UNKNOWN_FIELD_SET_SCHEMA = getUnknownFieldSetSchema(true);
    private static final UnknownFieldSchema<?, ?> UNKNOWN_FIELD_SET_LITE_SCHEMA = new UnknownFieldSetLiteSchema();

    private SchemaUtil() {
    }

    public static int computeSizeBoolList(int i3, List<?> list, boolean z2) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (!z2) {
            return CodedOutputStream.computeBoolSize(i3, true) * size;
        }
        return CodedOutputStream.computeLengthDelimitedFieldSize(size) + CodedOutputStream.computeTagSize(i3);
    }

    public static int computeSizeBoolListNoTag(List<?> list) {
        return list.size();
    }

    public static int computeSizeByteStringList(int i3, List<ByteString> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int computeTagSize = CodedOutputStream.computeTagSize(i3) * size;
        for (int i4 = 0; i4 < list.size(); i4++) {
            computeTagSize += CodedOutputStream.computeBytesSizeNoTag(list.get(i4));
        }
        return computeTagSize;
    }

    public static int computeSizeEnumList(int i3, List<Integer> list, boolean z2) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int computeSizeEnumListNoTag = computeSizeEnumListNoTag(list);
        if (!z2) {
            return (CodedOutputStream.computeTagSize(i3) * size) + computeSizeEnumListNoTag;
        }
        return CodedOutputStream.computeLengthDelimitedFieldSize(computeSizeEnumListNoTag) + CodedOutputStream.computeTagSize(i3);
    }

    public static int computeSizeEnumListNoTag(List<Integer> list) {
        int i3;
        int size = list.size();
        int i4 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof IntArrayList) {
            IntArrayList intArrayList = (IntArrayList) list;
            i3 = 0;
            while (i4 < size) {
                i3 += CodedOutputStream.computeEnumSizeNoTag(intArrayList.getInt(i4));
                i4++;
            }
        } else {
            int i5 = 0;
            while (i4 < size) {
                i5 = i3 + CodedOutputStream.computeEnumSizeNoTag(list.get(i4).intValue());
                i4++;
            }
        }
        return i3;
    }

    public static int computeSizeFixed32List(int i3, List<?> list, boolean z2) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (!z2) {
            return CodedOutputStream.computeFixed32Size(i3, 0) * size;
        }
        return CodedOutputStream.computeLengthDelimitedFieldSize(size * 4) + CodedOutputStream.computeTagSize(i3);
    }

    public static int computeSizeFixed32ListNoTag(List<?> list) {
        return list.size() * 4;
    }

    public static int computeSizeFixed64List(int i3, List<?> list, boolean z2) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (!z2) {
            return CodedOutputStream.computeFixed64Size(i3, 0) * size;
        }
        return CodedOutputStream.computeLengthDelimitedFieldSize(size * 8) + CodedOutputStream.computeTagSize(i3);
    }

    public static int computeSizeFixed64ListNoTag(List<?> list) {
        return list.size() * 8;
    }

    public static int computeSizeGroupList(int i3, List<MessageLite> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i4 = 0;
        for (int i5 = 0; i5 < size; i5++) {
            i4 += CodedOutputStream.computeGroupSize(i3, list.get(i5));
        }
        return i4;
    }

    public static int computeSizeInt32List(int i3, List<Integer> list, boolean z2) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int computeSizeInt32ListNoTag = computeSizeInt32ListNoTag(list);
        if (!z2) {
            return (CodedOutputStream.computeTagSize(i3) * size) + computeSizeInt32ListNoTag;
        }
        return CodedOutputStream.computeLengthDelimitedFieldSize(computeSizeInt32ListNoTag) + CodedOutputStream.computeTagSize(i3);
    }

    public static int computeSizeInt32ListNoTag(List<Integer> list) {
        int i3;
        int size = list.size();
        int i4 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof IntArrayList) {
            IntArrayList intArrayList = (IntArrayList) list;
            i3 = 0;
            while (i4 < size) {
                i3 += CodedOutputStream.computeInt32SizeNoTag(intArrayList.getInt(i4));
                i4++;
            }
        } else {
            int i5 = 0;
            while (i4 < size) {
                i5 = i3 + CodedOutputStream.computeInt32SizeNoTag(list.get(i4).intValue());
                i4++;
            }
        }
        return i3;
    }

    public static int computeSizeInt64List(int i3, List<Long> list, boolean z2) {
        if (list.size() == 0) {
            return 0;
        }
        int computeSizeInt64ListNoTag = computeSizeInt64ListNoTag(list);
        if (z2) {
            return CodedOutputStream.computeLengthDelimitedFieldSize(computeSizeInt64ListNoTag) + CodedOutputStream.computeTagSize(i3);
        }
        return (CodedOutputStream.computeTagSize(i3) * list.size()) + computeSizeInt64ListNoTag;
    }

    public static int computeSizeInt64ListNoTag(List<Long> list) {
        int i3;
        int size = list.size();
        int i4 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof LongArrayList) {
            LongArrayList longArrayList = (LongArrayList) list;
            i3 = 0;
            while (i4 < size) {
                i3 += CodedOutputStream.computeInt64SizeNoTag(longArrayList.getLong(i4));
                i4++;
            }
        } else {
            int i5 = 0;
            while (i4 < size) {
                i5 = i3 + CodedOutputStream.computeInt64SizeNoTag(list.get(i4).longValue());
                i4++;
            }
        }
        return i3;
    }

    public static int computeSizeMessage(int i3, Object obj, Schema schema) {
        return obj instanceof LazyFieldLite ? CodedOutputStream.computeLazyFieldSize(i3, (LazyFieldLite) obj) : CodedOutputStream.computeMessageSize(i3, (MessageLite) obj, schema);
    }

    public static int computeSizeMessageList(int i3, List<?> list) {
        int computeMessageSizeNoTag;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int computeTagSize = CodedOutputStream.computeTagSize(i3) * size;
        for (int i4 = 0; i4 < size; i4++) {
            Object obj = list.get(i4);
            if (obj instanceof LazyFieldLite) {
                computeMessageSizeNoTag = CodedOutputStream.computeLazyFieldSizeNoTag((LazyFieldLite) obj);
            } else {
                computeMessageSizeNoTag = CodedOutputStream.computeMessageSizeNoTag((MessageLite) obj);
            }
            computeTagSize = computeMessageSizeNoTag + computeTagSize;
        }
        return computeTagSize;
    }

    public static int computeSizeSInt32List(int i3, List<Integer> list, boolean z2) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int computeSizeSInt32ListNoTag = computeSizeSInt32ListNoTag(list);
        if (!z2) {
            return (CodedOutputStream.computeTagSize(i3) * size) + computeSizeSInt32ListNoTag;
        }
        return CodedOutputStream.computeLengthDelimitedFieldSize(computeSizeSInt32ListNoTag) + CodedOutputStream.computeTagSize(i3);
    }

    public static int computeSizeSInt32ListNoTag(List<Integer> list) {
        int i3;
        int size = list.size();
        int i4 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof IntArrayList) {
            IntArrayList intArrayList = (IntArrayList) list;
            i3 = 0;
            while (i4 < size) {
                i3 += CodedOutputStream.computeSInt32SizeNoTag(intArrayList.getInt(i4));
                i4++;
            }
        } else {
            int i5 = 0;
            while (i4 < size) {
                i5 = i3 + CodedOutputStream.computeSInt32SizeNoTag(list.get(i4).intValue());
                i4++;
            }
        }
        return i3;
    }

    public static int computeSizeSInt64List(int i3, List<Long> list, boolean z2) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int computeSizeSInt64ListNoTag = computeSizeSInt64ListNoTag(list);
        if (!z2) {
            return (CodedOutputStream.computeTagSize(i3) * size) + computeSizeSInt64ListNoTag;
        }
        return CodedOutputStream.computeLengthDelimitedFieldSize(computeSizeSInt64ListNoTag) + CodedOutputStream.computeTagSize(i3);
    }

    public static int computeSizeSInt64ListNoTag(List<Long> list) {
        int i3;
        int size = list.size();
        int i4 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof LongArrayList) {
            LongArrayList longArrayList = (LongArrayList) list;
            i3 = 0;
            while (i4 < size) {
                i3 += CodedOutputStream.computeSInt64SizeNoTag(longArrayList.getLong(i4));
                i4++;
            }
        } else {
            int i5 = 0;
            while (i4 < size) {
                i5 = i3 + CodedOutputStream.computeSInt64SizeNoTag(list.get(i4).longValue());
                i4++;
            }
        }
        return i3;
    }

    public static int computeSizeStringList(int i3, List<?> list) {
        int size = list.size();
        int i4 = 0;
        if (size == 0) {
            return 0;
        }
        int computeTagSize = CodedOutputStream.computeTagSize(i3) * size;
        if (list instanceof LazyStringList) {
            LazyStringList lazyStringList = (LazyStringList) list;
            while (i4 < size) {
                Object raw = lazyStringList.getRaw(i4);
                computeTagSize = (raw instanceof ByteString ? CodedOutputStream.computeBytesSizeNoTag((ByteString) raw) : CodedOutputStream.computeStringSizeNoTag((String) raw)) + computeTagSize;
                i4++;
            }
        } else {
            while (i4 < size) {
                Object obj = list.get(i4);
                computeTagSize = (obj instanceof ByteString ? CodedOutputStream.computeBytesSizeNoTag((ByteString) obj) : CodedOutputStream.computeStringSizeNoTag((String) obj)) + computeTagSize;
                i4++;
            }
        }
        return computeTagSize;
    }

    public static int computeSizeUInt32List(int i3, List<Integer> list, boolean z2) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int computeSizeUInt32ListNoTag = computeSizeUInt32ListNoTag(list);
        if (!z2) {
            return (CodedOutputStream.computeTagSize(i3) * size) + computeSizeUInt32ListNoTag;
        }
        return CodedOutputStream.computeLengthDelimitedFieldSize(computeSizeUInt32ListNoTag) + CodedOutputStream.computeTagSize(i3);
    }

    public static int computeSizeUInt32ListNoTag(List<Integer> list) {
        int i3;
        int size = list.size();
        int i4 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof IntArrayList) {
            IntArrayList intArrayList = (IntArrayList) list;
            i3 = 0;
            while (i4 < size) {
                i3 += CodedOutputStream.computeUInt32SizeNoTag(intArrayList.getInt(i4));
                i4++;
            }
        } else {
            int i5 = 0;
            while (i4 < size) {
                i5 = i3 + CodedOutputStream.computeUInt32SizeNoTag(list.get(i4).intValue());
                i4++;
            }
        }
        return i3;
    }

    public static int computeSizeUInt64List(int i3, List<Long> list, boolean z2) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int computeSizeUInt64ListNoTag = computeSizeUInt64ListNoTag(list);
        if (!z2) {
            return (CodedOutputStream.computeTagSize(i3) * size) + computeSizeUInt64ListNoTag;
        }
        return CodedOutputStream.computeLengthDelimitedFieldSize(computeSizeUInt64ListNoTag) + CodedOutputStream.computeTagSize(i3);
    }

    public static int computeSizeUInt64ListNoTag(List<Long> list) {
        int i3;
        int size = list.size();
        int i4 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof LongArrayList) {
            LongArrayList longArrayList = (LongArrayList) list;
            i3 = 0;
            while (i4 < size) {
                i3 += CodedOutputStream.computeUInt64SizeNoTag(longArrayList.getLong(i4));
                i4++;
            }
        } else {
            int i5 = 0;
            while (i4 < size) {
                i5 = i3 + CodedOutputStream.computeUInt64SizeNoTag(list.get(i4).longValue());
                i4++;
            }
        }
        return i3;
    }

    @CanIgnoreReturnValue
    public static <UT, UB> UB filterUnknownEnumList(Object obj, int i3, List<Integer> list, Internal.EnumLiteMap<?> enumLiteMap, UB ub, UnknownFieldSchema<UT, UB> unknownFieldSchema) {
        if (enumLiteMap == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i4 = 0;
            for (int i5 = 0; i5 < size; i5++) {
                Integer num = list.get(i5);
                int intValue = num.intValue();
                if (enumLiteMap.findValueByNumber(intValue) != null) {
                    if (i5 != i4) {
                        list.set(i4, num);
                    }
                    i4++;
                } else {
                    ub = storeUnknownEnum(obj, i3, intValue, ub, unknownFieldSchema);
                }
            }
            if (i4 != size) {
                list.subList(i4, size).clear();
            }
        } else {
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                int intValue2 = it.next().intValue();
                if (enumLiteMap.findValueByNumber(intValue2) == null) {
                    ub = storeUnknownEnum(obj, i3, intValue2, ub, unknownFieldSchema);
                    it.remove();
                }
            }
        }
        return ub;
    }

    private static Class<?> getGeneratedMessageClass() {
        try {
            return Class.forName("com.google.crypto.tink.shaded.protobuf.GeneratedMessageV3");
        } catch (Throwable unused) {
            return null;
        }
    }

    public static Object getMapDefaultEntry(Class<?> cls, String str) {
        try {
            Field[] declaredFields = Class.forName(cls.getName() + "$" + toCamelCase(str, true) + "DefaultEntryHolder").getDeclaredFields();
            if (declaredFields.length == 1) {
                return UnsafeUtil.getStaticObject(declaredFields[0]);
            }
            throw new IllegalStateException("Unable to look up map field default entry holder class for " + str + " in " + cls.getName());
        } catch (Throwable th) {
            throw new RuntimeException(th);
        }
    }

    private static UnknownFieldSchema<?, ?> getUnknownFieldSetSchema(boolean z2) {
        try {
            Class<?> unknownFieldSetSchemaClass = getUnknownFieldSetSchemaClass();
            if (unknownFieldSetSchemaClass == null) {
                return null;
            }
            return (UnknownFieldSchema) unknownFieldSetSchemaClass.getConstructor(new Class[]{Boolean.TYPE}).newInstance(new Object[]{Boolean.valueOf(z2)});
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class<?> getUnknownFieldSetSchemaClass() {
        try {
            return Class.forName("com.google.crypto.tink.shaded.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            return null;
        }
    }

    public static <T, FT extends FieldSet.FieldDescriptorLite<FT>> void mergeExtensions(ExtensionSchema<FT> extensionSchema, T t2, T t3) {
        FieldSet<FT> extensions = extensionSchema.getExtensions(t3);
        if (!extensions.isEmpty()) {
            extensionSchema.getMutableExtensions(t2).mergeFrom(extensions);
        }
    }

    public static <T> void mergeMap(MapFieldSchema mapFieldSchema, T t2, T t3, long j2) {
        UnsafeUtil.putObject((Object) t2, j2, mapFieldSchema.mergeFrom(UnsafeUtil.getObject((Object) t2, j2), UnsafeUtil.getObject((Object) t3, j2)));
    }

    public static <T, UT, UB> void mergeUnknownFields(UnknownFieldSchema<UT, UB> unknownFieldSchema, T t2, T t3) {
        unknownFieldSchema.setToMessage(t2, unknownFieldSchema.merge(unknownFieldSchema.getFromMessage(t2), unknownFieldSchema.getFromMessage(t3)));
    }

    public static UnknownFieldSchema<?, ?> proto2UnknownFieldSetSchema() {
        return PROTO2_UNKNOWN_FIELD_SET_SCHEMA;
    }

    public static UnknownFieldSchema<?, ?> proto3UnknownFieldSetSchema() {
        return PROTO3_UNKNOWN_FIELD_SET_SCHEMA;
    }

    public static void requireGeneratedMessage(Class<?> cls) {
        Class<?> cls2;
        if (!GeneratedMessageLite.class.isAssignableFrom(cls) && (cls2 = GENERATED_MESSAGE_CLASS) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessageV3 or GeneratedMessageLite");
        }
    }

    public static boolean safeEquals(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static boolean shouldUseTableSwitch(int i3, int i4, int i5) {
        if (i4 < 40) {
            return true;
        }
        long j2 = ((long) i4) - ((long) i3);
        long j3 = (long) i5;
        return j2 + 10 <= ((j3 + 3) * 3) + ((2 * j3) + 3);
    }

    @CanIgnoreReturnValue
    public static <UT, UB> UB storeUnknownEnum(Object obj, int i3, int i4, UB ub, UnknownFieldSchema<UT, UB> unknownFieldSchema) {
        if (ub == null) {
            ub = unknownFieldSchema.getBuilderFromMessage(obj);
        }
        unknownFieldSchema.addVarint(ub, i3, (long) i4);
        return ub;
    }

    public static String toCamelCase(String str, boolean z2) {
        StringBuilder sb = new StringBuilder();
        for (int i3 = 0; i3 < str.length(); i3++) {
            char charAt = str.charAt(i3);
            if ('a' > charAt || charAt > 'z') {
                if ('A' > charAt || charAt > 'Z') {
                    if ('0' <= charAt && charAt <= '9') {
                        sb.append(charAt);
                    }
                    z2 = true;
                } else if (i3 != 0 || z2) {
                    sb.append(charAt);
                } else {
                    sb.append((char) (charAt + ' '));
                }
            } else if (z2) {
                sb.append((char) (charAt - ' '));
            } else {
                sb.append(charAt);
            }
            z2 = false;
        }
        return sb.toString();
    }

    public static UnknownFieldSchema<?, ?> unknownFieldSetLiteSchema() {
        return UNKNOWN_FIELD_SET_LITE_SCHEMA;
    }

    public static void writeBool(int i3, boolean z2, Writer writer) throws IOException {
        if (z2) {
            writer.writeBool(i3, true);
        }
    }

    public static void writeBoolList(int i3, List<Boolean> list, Writer writer, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            writer.writeBoolList(i3, list, z2);
        }
    }

    public static void writeBytes(int i3, ByteString byteString, Writer writer) throws IOException {
        if (byteString != null && !byteString.isEmpty()) {
            writer.writeBytes(i3, byteString);
        }
    }

    public static void writeBytesList(int i3, List<ByteString> list, Writer writer) throws IOException {
        if (list != null && !list.isEmpty()) {
            writer.writeBytesList(i3, list);
        }
    }

    public static void writeDouble(int i3, double d2, Writer writer) throws IOException {
        if (Double.doubleToRawLongBits(d2) != 0) {
            writer.writeDouble(i3, d2);
        }
    }

    public static void writeDoubleList(int i3, List<Double> list, Writer writer, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            writer.writeDoubleList(i3, list, z2);
        }
    }

    public static void writeEnum(int i3, int i4, Writer writer) throws IOException {
        if (i4 != 0) {
            writer.writeEnum(i3, i4);
        }
    }

    public static void writeEnumList(int i3, List<Integer> list, Writer writer, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            writer.writeEnumList(i3, list, z2);
        }
    }

    public static void writeFixed32(int i3, int i4, Writer writer) throws IOException {
        if (i4 != 0) {
            writer.writeFixed32(i3, i4);
        }
    }

    public static void writeFixed32List(int i3, List<Integer> list, Writer writer, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            writer.writeFixed32List(i3, list, z2);
        }
    }

    public static void writeFixed64(int i3, long j2, Writer writer) throws IOException {
        if (j2 != 0) {
            writer.writeFixed64(i3, j2);
        }
    }

    public static void writeFixed64List(int i3, List<Long> list, Writer writer, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            writer.writeFixed64List(i3, list, z2);
        }
    }

    public static void writeFloat(int i3, float f2, Writer writer) throws IOException {
        if (Float.floatToRawIntBits(f2) != 0) {
            writer.writeFloat(i3, f2);
        }
    }

    public static void writeFloatList(int i3, List<Float> list, Writer writer, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            writer.writeFloatList(i3, list, z2);
        }
    }

    public static void writeGroupList(int i3, List<?> list, Writer writer) throws IOException {
        if (list != null && !list.isEmpty()) {
            writer.writeGroupList(i3, list);
        }
    }

    public static void writeInt32(int i3, int i4, Writer writer) throws IOException {
        if (i4 != 0) {
            writer.writeInt32(i3, i4);
        }
    }

    public static void writeInt32List(int i3, List<Integer> list, Writer writer, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            writer.writeInt32List(i3, list, z2);
        }
    }

    public static void writeInt64(int i3, long j2, Writer writer) throws IOException {
        if (j2 != 0) {
            writer.writeInt64(i3, j2);
        }
    }

    public static void writeInt64List(int i3, List<Long> list, Writer writer, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            writer.writeInt64List(i3, list, z2);
        }
    }

    public static void writeLazyFieldList(int i3, List<?> list, Writer writer) throws IOException {
        if (list != null && !list.isEmpty()) {
            Iterator<?> it = list.iterator();
            while (it.hasNext()) {
                ((LazyFieldLite) it.next()).writeTo(writer, i3);
            }
        }
    }

    public static void writeMessage(int i3, Object obj, Writer writer) throws IOException {
        if (obj != null) {
            writer.writeMessage(i3, obj);
        }
    }

    public static void writeMessageList(int i3, List<?> list, Writer writer) throws IOException {
        if (list != null && !list.isEmpty()) {
            writer.writeMessageList(i3, list);
        }
    }

    public static void writeSFixed32(int i3, int i4, Writer writer) throws IOException {
        if (i4 != 0) {
            writer.writeSFixed32(i3, i4);
        }
    }

    public static void writeSFixed32List(int i3, List<Integer> list, Writer writer, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            writer.writeSFixed32List(i3, list, z2);
        }
    }

    public static void writeSFixed64(int i3, long j2, Writer writer) throws IOException {
        if (j2 != 0) {
            writer.writeSFixed64(i3, j2);
        }
    }

    public static void writeSFixed64List(int i3, List<Long> list, Writer writer, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            writer.writeSFixed64List(i3, list, z2);
        }
    }

    public static void writeSInt32(int i3, int i4, Writer writer) throws IOException {
        if (i4 != 0) {
            writer.writeSInt32(i3, i4);
        }
    }

    public static void writeSInt32List(int i3, List<Integer> list, Writer writer, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            writer.writeSInt32List(i3, list, z2);
        }
    }

    public static void writeSInt64(int i3, long j2, Writer writer) throws IOException {
        if (j2 != 0) {
            writer.writeSInt64(i3, j2);
        }
    }

    public static void writeSInt64List(int i3, List<Long> list, Writer writer, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            writer.writeSInt64List(i3, list, z2);
        }
    }

    public static void writeString(int i3, Object obj, Writer writer) throws IOException {
        if (obj instanceof String) {
            writeStringInternal(i3, (String) obj, writer);
        } else {
            writeBytes(i3, (ByteString) obj, writer);
        }
    }

    private static void writeStringInternal(int i3, String str, Writer writer) throws IOException {
        if (str != null && !str.isEmpty()) {
            writer.writeString(i3, str);
        }
    }

    public static void writeStringList(int i3, List<String> list, Writer writer) throws IOException {
        if (list != null && !list.isEmpty()) {
            writer.writeStringList(i3, list);
        }
    }

    public static void writeUInt32(int i3, int i4, Writer writer) throws IOException {
        if (i4 != 0) {
            writer.writeUInt32(i3, i4);
        }
    }

    public static void writeUInt32List(int i3, List<Integer> list, Writer writer, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            writer.writeUInt32List(i3, list, z2);
        }
    }

    public static void writeUInt64(int i3, long j2, Writer writer) throws IOException {
        if (j2 != 0) {
            writer.writeUInt64(i3, j2);
        }
    }

    public static void writeUInt64List(int i3, List<Long> list, Writer writer, boolean z2) throws IOException {
        if (list != null && !list.isEmpty()) {
            writer.writeUInt64List(i3, list, z2);
        }
    }

    public static boolean shouldUseTableSwitch(FieldInfo[] fieldInfoArr) {
        if (fieldInfoArr.length == 0) {
            return false;
        }
        return shouldUseTableSwitch(fieldInfoArr[0].getFieldNumber(), fieldInfoArr[fieldInfoArr.length - 1].getFieldNumber(), fieldInfoArr.length);
    }

    public static int computeSizeGroupList(int i3, List<MessageLite> list, Schema schema) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i4 = 0;
        for (int i5 = 0; i5 < size; i5++) {
            i4 += CodedOutputStream.computeGroupSize(i3, list.get(i5), schema);
        }
        return i4;
    }

    public static void writeGroupList(int i3, List<?> list, Writer writer, Schema schema) throws IOException {
        if (list != null && !list.isEmpty()) {
            writer.writeGroupList(i3, list, schema);
        }
    }

    public static void writeMessageList(int i3, List<?> list, Writer writer, Schema schema) throws IOException {
        if (list != null && !list.isEmpty()) {
            writer.writeMessageList(i3, list, schema);
        }
    }

    public static int computeSizeMessageList(int i3, List<?> list, Schema schema) {
        int computeMessageSizeNoTag;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int computeTagSize = CodedOutputStream.computeTagSize(i3) * size;
        for (int i4 = 0; i4 < size; i4++) {
            Object obj = list.get(i4);
            if (obj instanceof LazyFieldLite) {
                computeMessageSizeNoTag = CodedOutputStream.computeLazyFieldSizeNoTag((LazyFieldLite) obj);
            } else {
                computeMessageSizeNoTag = CodedOutputStream.computeMessageSizeNoTag((MessageLite) obj, schema);
            }
            computeTagSize = computeMessageSizeNoTag + computeTagSize;
        }
        return computeTagSize;
    }

    @CanIgnoreReturnValue
    public static <UT, UB> UB filterUnknownEnumList(Object obj, int i3, List<Integer> list, Internal.EnumVerifier enumVerifier, UB ub, UnknownFieldSchema<UT, UB> unknownFieldSchema) {
        if (enumVerifier == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i4 = 0;
            for (int i5 = 0; i5 < size; i5++) {
                Integer num = list.get(i5);
                int intValue = num.intValue();
                if (enumVerifier.isInRange(intValue)) {
                    if (i5 != i4) {
                        list.set(i4, num);
                    }
                    i4++;
                } else {
                    ub = storeUnknownEnum(obj, i3, intValue, ub, unknownFieldSchema);
                }
            }
            if (i4 != size) {
                list.subList(i4, size).clear();
            }
        } else {
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                int intValue2 = it.next().intValue();
                if (!enumVerifier.isInRange(intValue2)) {
                    ub = storeUnknownEnum(obj, i3, intValue2, ub, unknownFieldSchema);
                    it.remove();
                }
            }
        }
        return ub;
    }
}
