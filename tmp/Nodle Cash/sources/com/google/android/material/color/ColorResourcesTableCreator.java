package com.google.android.material.color;

import android.content.Context;
import android.support.v4.media.session.a;
import android.util.Pair;
import androidx.annotation.ColorInt;
import androidx.constraintlayout.core.state.b;
import com.amplitude.api.DeviceInfo;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

final class ColorResourcesTableCreator {
    private static final byte ANDROID_PACKAGE_ID = 1;
    private static final PackageInfo ANDROID_PACKAGE_INFO = new PackageInfo(1, DeviceInfo.OS_NAME);
    private static final byte APPLICATION_PACKAGE_ID = Byte.MAX_VALUE;
    /* access modifiers changed from: private */
    public static final Comparator<ColorResource> COLOR_RESOURCE_COMPARATOR = new Comparator<ColorResource>() {
        public int compare(ColorResource colorResource, ColorResource colorResource2) {
            return colorResource.entryId - colorResource2.entryId;
        }
    };
    private static final short HEADER_TYPE_PACKAGE = 512;
    private static final short HEADER_TYPE_RES_TABLE = 2;
    private static final short HEADER_TYPE_STRING_POOL = 1;
    private static final short HEADER_TYPE_TYPE = 513;
    private static final short HEADER_TYPE_TYPE_SPEC = 514;
    private static final String RESOURCE_TYPE_NAME_COLOR = "color";
    /* access modifiers changed from: private */
    public static byte typeIdColor;

    public static class ColorResource {
        /* access modifiers changed from: private */
        public final short entryId;
        /* access modifiers changed from: private */
        public final String name;
        /* access modifiers changed from: private */
        public final byte packageId;
        /* access modifiers changed from: private */
        public final byte typeId;
        /* access modifiers changed from: private */
        @ColorInt
        public final int value;

        public ColorResource(int i3, String str, int i4) {
            this.name = str;
            this.value = i4;
            this.entryId = (short) (65535 & i3);
            this.typeId = (byte) ((i3 >> 16) & 255);
            this.packageId = (byte) ((i3 >> 24) & 255);
        }
    }

    public static class PackageChunk {
        private static final short HEADER_SIZE = 288;
        private static final int PACKAGE_NAME_MAX_LENGTH = 128;
        private final ResChunkHeader header;
        private final StringPoolChunk keyStrings;
        private final PackageInfo packageInfo;
        private final TypeSpecChunk typeSpecChunk;
        private final StringPoolChunk typeStrings = new StringPoolChunk(false, "?1", "?2", "?3", "?4", "?5", "color");

        public PackageChunk(PackageInfo packageInfo2, List<ColorResource> list) {
            this.packageInfo = packageInfo2;
            String[] strArr = new String[list.size()];
            for (int i3 = 0; i3 < list.size(); i3++) {
                strArr[i3] = list.get(i3).name;
            }
            this.keyStrings = new StringPoolChunk(true, strArr);
            this.typeSpecChunk = new TypeSpecChunk(list);
            this.header = new ResChunkHeader(512, HEADER_SIZE, getChunkSize());
        }

        public int getChunkSize() {
            return this.typeStrings.getChunkSize() + 288 + this.keyStrings.getChunkSize() + this.typeSpecChunk.getChunkSizeWithTypeChunk();
        }

        public void writeTo(ByteArrayOutputStream byteArrayOutputStream) throws IOException {
            this.header.writeTo(byteArrayOutputStream);
            byteArrayOutputStream.write(ColorResourcesTableCreator.intToByteArray(this.packageInfo.id));
            char[] charArray = this.packageInfo.name.toCharArray();
            for (int i3 = 0; i3 < 128; i3++) {
                if (i3 < charArray.length) {
                    byteArrayOutputStream.write(ColorResourcesTableCreator.charToByteArray(charArray[i3]));
                } else {
                    byteArrayOutputStream.write(ColorResourcesTableCreator.charToByteArray(0));
                }
            }
            byteArrayOutputStream.write(ColorResourcesTableCreator.intToByteArray(288));
            byteArrayOutputStream.write(ColorResourcesTableCreator.intToByteArray(0));
            byteArrayOutputStream.write(ColorResourcesTableCreator.intToByteArray(this.typeStrings.getChunkSize() + 288));
            byteArrayOutputStream.write(ColorResourcesTableCreator.intToByteArray(0));
            byteArrayOutputStream.write(ColorResourcesTableCreator.intToByteArray(0));
            this.typeStrings.writeTo(byteArrayOutputStream);
            this.keyStrings.writeTo(byteArrayOutputStream);
            this.typeSpecChunk.writeTo(byteArrayOutputStream);
        }
    }

    public static class PackageInfo {
        /* access modifiers changed from: private */
        public final int id;
        /* access modifiers changed from: private */
        public final String name;

        public PackageInfo(int i3, String str) {
            this.id = i3;
            this.name = str;
        }
    }

    public static class ResChunkHeader {
        private final int chunkSize;
        private final short headerSize;
        private final short type;

        public ResChunkHeader(short s3, short s4, int i3) {
            this.type = s3;
            this.headerSize = s4;
            this.chunkSize = i3;
        }

        public void writeTo(ByteArrayOutputStream byteArrayOutputStream) throws IOException {
            byteArrayOutputStream.write(ColorResourcesTableCreator.shortToByteArray(this.type));
            byteArrayOutputStream.write(ColorResourcesTableCreator.shortToByteArray(this.headerSize));
            byteArrayOutputStream.write(ColorResourcesTableCreator.intToByteArray(this.chunkSize));
        }
    }

    public static class ResEntry {
        private static final byte DATA_TYPE_AARRGGBB = 28;
        private static final short ENTRY_SIZE = 8;
        private static final short FLAG_PUBLIC = 2;
        private static final int SIZE = 16;
        private static final short VALUE_SIZE = 8;
        private final int data;
        private final int keyStringIndex;

        public ResEntry(int i3, @ColorInt int i4) {
            this.keyStringIndex = i3;
            this.data = i4;
        }

        public void writeTo(ByteArrayOutputStream byteArrayOutputStream) throws IOException {
            byteArrayOutputStream.write(ColorResourcesTableCreator.shortToByteArray(8));
            byteArrayOutputStream.write(ColorResourcesTableCreator.shortToByteArray(2));
            byteArrayOutputStream.write(ColorResourcesTableCreator.intToByteArray(this.keyStringIndex));
            byteArrayOutputStream.write(ColorResourcesTableCreator.shortToByteArray(8));
            byteArrayOutputStream.write(new byte[]{0, 28});
            byteArrayOutputStream.write(ColorResourcesTableCreator.intToByteArray(this.data));
        }
    }

    public static class ResTable {
        private static final short HEADER_SIZE = 12;
        private final ResChunkHeader header;
        private final List<PackageChunk> packageChunks = new ArrayList();
        private final int packageCount;
        private final StringPoolChunk stringPool;

        public ResTable(Map<PackageInfo, List<ColorResource>> map) {
            this.packageCount = map.size();
            this.stringPool = new StringPoolChunk(new String[0]);
            for (Map.Entry next : map.entrySet()) {
                List list = (List) next.getValue();
                Collections.sort(list, ColorResourcesTableCreator.COLOR_RESOURCE_COMPARATOR);
                this.packageChunks.add(new PackageChunk((PackageInfo) next.getKey(), list));
            }
            this.header = new ResChunkHeader(2, 12, getOverallSize());
        }

        private int getOverallSize() {
            int i3 = 0;
            for (PackageChunk chunkSize : this.packageChunks) {
                i3 += chunkSize.getChunkSize();
            }
            return this.stringPool.getChunkSize() + 12 + i3;
        }

        public void writeTo(ByteArrayOutputStream byteArrayOutputStream) throws IOException {
            this.header.writeTo(byteArrayOutputStream);
            byteArrayOutputStream.write(ColorResourcesTableCreator.intToByteArray(this.packageCount));
            this.stringPool.writeTo(byteArrayOutputStream);
            for (PackageChunk writeTo : this.packageChunks) {
                writeTo.writeTo(byteArrayOutputStream);
            }
        }
    }

    public static class StringPoolChunk {
        private static final int FLAG_UTF8 = 256;
        private static final short HEADER_SIZE = 28;
        private static final int STYLED_SPAN_LIST_END = -1;
        private final int chunkSize;
        private final ResChunkHeader header;
        private final int stringCount;
        private final List<Integer> stringIndex;
        private final List<byte[]> strings;
        private final int stringsPaddingSize;
        private final int stringsStart;
        private final int styledSpanCount;
        private final List<Integer> styledSpanIndex;
        private final List<List<StringStyledSpan>> styledSpans;
        private final int styledSpansStart;
        private final boolean utf8Encode;

        public StringPoolChunk(String... strArr) {
            this(false, strArr);
        }

        private Pair<byte[], List<StringStyledSpan>> processString(String str) {
            return new Pair<>(this.utf8Encode ? ColorResourcesTableCreator.stringToByteArrayUtf8(str) : ColorResourcesTableCreator.stringToByteArray(str), Collections.emptyList());
        }

        public int getChunkSize() {
            return this.chunkSize;
        }

        public void writeTo(ByteArrayOutputStream byteArrayOutputStream) throws IOException {
            this.header.writeTo(byteArrayOutputStream);
            byteArrayOutputStream.write(ColorResourcesTableCreator.intToByteArray(this.stringCount));
            byteArrayOutputStream.write(ColorResourcesTableCreator.intToByteArray(this.styledSpanCount));
            byteArrayOutputStream.write(ColorResourcesTableCreator.intToByteArray(this.utf8Encode ? 256 : 0));
            byteArrayOutputStream.write(ColorResourcesTableCreator.intToByteArray(this.stringsStart));
            byteArrayOutputStream.write(ColorResourcesTableCreator.intToByteArray(this.styledSpansStart));
            for (Integer intValue : this.stringIndex) {
                byteArrayOutputStream.write(ColorResourcesTableCreator.intToByteArray(intValue.intValue()));
            }
            for (Integer intValue2 : this.styledSpanIndex) {
                byteArrayOutputStream.write(ColorResourcesTableCreator.intToByteArray(intValue2.intValue()));
            }
            for (byte[] write : this.strings) {
                byteArrayOutputStream.write(write);
            }
            int i3 = this.stringsPaddingSize;
            if (i3 > 0) {
                byteArrayOutputStream.write(new byte[i3]);
            }
            for (List<StringStyledSpan> it : this.styledSpans) {
                for (StringStyledSpan writeTo : it) {
                    writeTo.writeTo(byteArrayOutputStream);
                }
                byteArrayOutputStream.write(ColorResourcesTableCreator.intToByteArray(-1));
            }
        }

        public StringPoolChunk(boolean z2, String... strArr) {
            this.stringIndex = new ArrayList();
            this.styledSpanIndex = new ArrayList();
            this.strings = new ArrayList();
            this.styledSpans = new ArrayList();
            this.utf8Encode = z2;
            int i3 = 0;
            int i4 = 0;
            for (String processString : strArr) {
                Pair<byte[], List<StringStyledSpan>> processString2 = processString(processString);
                this.stringIndex.add(Integer.valueOf(i4));
                Object obj = processString2.first;
                i4 += ((byte[]) obj).length;
                this.strings.add((byte[]) obj);
                this.styledSpans.add((List) processString2.second);
            }
            int i5 = 0;
            for (List<StringStyledSpan> next : this.styledSpans) {
                for (StringStyledSpan stringStyledSpan : next) {
                    this.stringIndex.add(Integer.valueOf(i4));
                    i4 += stringStyledSpan.styleString.length;
                    this.strings.add(stringStyledSpan.styleString);
                }
                this.styledSpanIndex.add(Integer.valueOf(i5));
                i5 += (next.size() * 12) + 4;
            }
            int i6 = i4 % 4;
            int i7 = i6 == 0 ? 0 : 4 - i6;
            this.stringsPaddingSize = i7;
            int size = this.strings.size();
            this.stringCount = size;
            this.styledSpanCount = this.strings.size() - strArr.length;
            boolean z3 = this.strings.size() - strArr.length > 0;
            if (!z3) {
                this.styledSpanIndex.clear();
                this.styledSpans.clear();
            }
            int c3 = b.c(this.styledSpanIndex, 4, (size * 4) + 28);
            this.stringsStart = c3;
            int i8 = i4 + i7;
            this.styledSpansStart = z3 ? c3 + i8 : 0;
            int i9 = c3 + i8 + (z3 ? i5 : i3);
            this.chunkSize = i9;
            this.header = new ResChunkHeader(1, 28, i9);
        }
    }

    public static class StringStyledSpan {
        private int firstCharacterIndex;
        private int lastCharacterIndex;
        private int nameReference;
        /* access modifiers changed from: private */
        public byte[] styleString;

        private StringStyledSpan() {
        }

        public void writeTo(ByteArrayOutputStream byteArrayOutputStream) throws IOException {
            byteArrayOutputStream.write(ColorResourcesTableCreator.intToByteArray(this.nameReference));
            byteArrayOutputStream.write(ColorResourcesTableCreator.intToByteArray(this.firstCharacterIndex));
            byteArrayOutputStream.write(ColorResourcesTableCreator.intToByteArray(this.lastCharacterIndex));
        }
    }

    public static class TypeChunk {
        private static final byte CONFIG_SIZE = 64;
        private static final short HEADER_SIZE = 84;
        private static final int OFFSET_NO_ENTRY = -1;
        private final byte[] config;
        private final int entryCount;
        private final ResChunkHeader header;
        private final int[] offsetTable;
        private final ResEntry[] resEntries;

        public TypeChunk(List<ColorResource> list, Set<Short> set, int i3) {
            byte[] bArr = new byte[64];
            this.config = bArr;
            this.entryCount = i3;
            bArr[0] = 64;
            this.resEntries = new ResEntry[list.size()];
            for (int i4 = 0; i4 < list.size(); i4++) {
                this.resEntries[i4] = new ResEntry(i4, list.get(i4).value);
            }
            this.offsetTable = new int[i3];
            int i5 = 0;
            for (short s3 = 0; s3 < i3; s3 = (short) (s3 + 1)) {
                if (set.contains(Short.valueOf(s3))) {
                    this.offsetTable[s3] = i5;
                    i5 += 16;
                } else {
                    this.offsetTable[s3] = -1;
                }
            }
            this.header = new ResChunkHeader(ColorResourcesTableCreator.HEADER_TYPE_TYPE, HEADER_SIZE, getChunkSize());
        }

        private int getEntryStart() {
            return getOffsetTableSize() + 84;
        }

        private int getOffsetTableSize() {
            return this.offsetTable.length * 4;
        }

        public int getChunkSize() {
            return (this.resEntries.length * 16) + getEntryStart();
        }

        public void writeTo(ByteArrayOutputStream byteArrayOutputStream) throws IOException {
            this.header.writeTo(byteArrayOutputStream);
            byteArrayOutputStream.write(new byte[]{ColorResourcesTableCreator.typeIdColor, 0, 0, 0});
            byteArrayOutputStream.write(ColorResourcesTableCreator.intToByteArray(this.entryCount));
            byteArrayOutputStream.write(ColorResourcesTableCreator.intToByteArray(getEntryStart()));
            byteArrayOutputStream.write(this.config);
            for (int access$500 : this.offsetTable) {
                byteArrayOutputStream.write(ColorResourcesTableCreator.intToByteArray(access$500));
            }
            for (ResEntry writeTo : this.resEntries) {
                writeTo.writeTo(byteArrayOutputStream);
            }
        }
    }

    public static class TypeSpecChunk {
        private static final short HEADER_SIZE = 16;
        private static final int SPEC_PUBLIC = 1073741824;
        private final int entryCount;
        private final int[] entryFlags;
        private final ResChunkHeader header;
        private final TypeChunk typeChunk;

        public TypeSpecChunk(List<ColorResource> list) {
            this.entryCount = ((ColorResource) a.h(list, 1)).entryId + 1;
            HashSet hashSet = new HashSet();
            for (ColorResource access$000 : list) {
                hashSet.add(Short.valueOf(access$000.entryId));
            }
            this.entryFlags = new int[this.entryCount];
            for (short s3 = 0; s3 < this.entryCount; s3 = (short) (s3 + 1)) {
                if (hashSet.contains(Short.valueOf(s3))) {
                    this.entryFlags[s3] = 1073741824;
                }
            }
            this.header = new ResChunkHeader(ColorResourcesTableCreator.HEADER_TYPE_TYPE_SPEC, 16, getChunkSize());
            this.typeChunk = new TypeChunk(list, hashSet, this.entryCount);
        }

        private int getChunkSize() {
            return (this.entryCount * 4) + 16;
        }

        public int getChunkSizeWithTypeChunk() {
            return getChunkSize() + this.typeChunk.getChunkSize();
        }

        public void writeTo(ByteArrayOutputStream byteArrayOutputStream) throws IOException {
            this.header.writeTo(byteArrayOutputStream);
            byteArrayOutputStream.write(new byte[]{ColorResourcesTableCreator.typeIdColor, 0, 0, 0});
            byteArrayOutputStream.write(ColorResourcesTableCreator.intToByteArray(this.entryCount));
            for (int access$500 : this.entryFlags) {
                byteArrayOutputStream.write(ColorResourcesTableCreator.intToByteArray(access$500));
            }
            this.typeChunk.writeTo(byteArrayOutputStream);
        }
    }

    private ColorResourcesTableCreator() {
    }

    /* access modifiers changed from: private */
    public static byte[] charToByteArray(char c3) {
        return new byte[]{(byte) (c3 & 255), (byte) ((c3 >> 8) & 255)};
    }

    public static byte[] create(Context context, Map<Integer, Integer> map) throws IOException {
        PackageInfo packageInfo;
        if (!map.entrySet().isEmpty()) {
            PackageInfo packageInfo2 = new PackageInfo(127, context.getPackageName());
            HashMap hashMap = new HashMap();
            ColorResource colorResource = null;
            for (Map.Entry next : map.entrySet()) {
                ColorResource colorResource2 = new ColorResource(((Integer) next.getKey()).intValue(), context.getResources().getResourceName(((Integer) next.getKey()).intValue()), ((Integer) next.getValue()).intValue());
                if (context.getResources().getResourceTypeName(((Integer) next.getKey()).intValue()).equals("color")) {
                    if (colorResource2.packageId == 1) {
                        packageInfo = ANDROID_PACKAGE_INFO;
                    } else if (colorResource2.packageId == Byte.MAX_VALUE) {
                        packageInfo = packageInfo2;
                    } else {
                        throw new IllegalArgumentException("Not supported with unknown package id: " + colorResource2.packageId);
                    }
                    if (!hashMap.containsKey(packageInfo)) {
                        hashMap.put(packageInfo, new ArrayList());
                    }
                    ((List) hashMap.get(packageInfo)).add(colorResource2);
                    colorResource = colorResource2;
                } else {
                    StringBuilder sb = new StringBuilder("Non color resource found: name=");
                    sb.append(colorResource2.name);
                    sb.append(", typeId=");
                    throw new IllegalArgumentException(a.j(colorResource2.typeId & 255, sb));
                }
            }
            byte access$200 = colorResource.typeId;
            typeIdColor = access$200;
            if (access$200 != 0) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                new ResTable(hashMap).writeTo(byteArrayOutputStream);
                return byteArrayOutputStream.toByteArray();
            }
            throw new IllegalArgumentException("No color resources found for harmonization.");
        }
        throw new IllegalArgumentException("No color resources provided for harmonization.");
    }

    /* access modifiers changed from: private */
    public static byte[] intToByteArray(int i3) {
        return new byte[]{(byte) (i3 & 255), (byte) ((i3 >> 8) & 255), (byte) ((i3 >> 16) & 255), (byte) ((i3 >> 24) & 255)};
    }

    /* access modifiers changed from: private */
    public static byte[] shortToByteArray(short s3) {
        return new byte[]{(byte) (s3 & 255), (byte) ((s3 >> 8) & 255)};
    }

    /* access modifiers changed from: private */
    public static byte[] stringToByteArray(String str) {
        char[] charArray = str.toCharArray();
        int length = charArray.length * 2;
        byte[] bArr = new byte[(length + 4)];
        byte[] shortToByteArray = shortToByteArray((short) charArray.length);
        bArr[0] = shortToByteArray[0];
        bArr[1] = shortToByteArray[1];
        for (int i3 = 0; i3 < charArray.length; i3++) {
            byte[] charToByteArray = charToByteArray(charArray[i3]);
            int i4 = i3 * 2;
            bArr[i4 + 2] = charToByteArray[0];
            bArr[i4 + 3] = charToByteArray[1];
        }
        bArr[length + 2] = 0;
        bArr[length + 3] = 0;
        return bArr;
    }

    /* access modifiers changed from: private */
    public static byte[] stringToByteArrayUtf8(String str) {
        byte[] bytes = str.getBytes(Charset.forName("UTF-8"));
        byte length = (byte) bytes.length;
        int length2 = bytes.length;
        byte[] bArr = new byte[(length2 + 3)];
        System.arraycopy(bytes, 0, bArr, 2, length);
        bArr[1] = length;
        bArr[0] = length;
        bArr[length2 + 2] = 0;
        return bArr;
    }
}
