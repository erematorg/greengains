package com.sun.jna;

import A.a;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class ELFAnalyser {
    private static final int EF_ARM_ABI_FLOAT_HARD = 1024;
    private static final int EF_ARM_ABI_FLOAT_SOFT = 512;
    private static final int EI_CLASS_64BIT = 2;
    private static final int EI_DATA_BIG_ENDIAN = 2;
    private static final byte[] ELF_MAGIC = {Byte.MAX_VALUE, 69, 76, 70};
    private static final int E_MACHINE_ARM = 40;
    private static final int SHN_UNDEF = 0;
    private static final int SHN_XINDEX = 65535;
    private boolean ELF = false;
    private boolean _64Bit = false;
    private boolean arm = false;
    private boolean armEabiAapcsVfp = false;
    private boolean armHardFloatFlag = false;
    private boolean armSoftFloatFlag = false;
    private boolean bigEndian = false;
    private final String filename;

    public static class ArmAeabiAttributesTag {
        public static final ArmAeabiAttributesTag ABI_FP_16bit_format;
        public static final ArmAeabiAttributesTag ABI_FP_denormal;
        public static final ArmAeabiAttributesTag ABI_FP_exceptions;
        public static final ArmAeabiAttributesTag ABI_FP_number_model;
        public static final ArmAeabiAttributesTag ABI_FP_optimization_goals;
        public static final ArmAeabiAttributesTag ABI_FP_rounding;
        public static final ArmAeabiAttributesTag ABI_FP_user_exceptions;
        public static final ArmAeabiAttributesTag ABI_HardFP_use;
        public static final ArmAeabiAttributesTag ABI_PCS_GOT_use;
        public static final ArmAeabiAttributesTag ABI_PCS_R9_use;
        public static final ArmAeabiAttributesTag ABI_PCS_RO_data;
        public static final ArmAeabiAttributesTag ABI_PCS_RW_data;
        public static final ArmAeabiAttributesTag ABI_PCS_wchar_t;
        public static final ArmAeabiAttributesTag ABI_VFP_args;
        public static final ArmAeabiAttributesTag ABI_WMMX_args;
        public static final ArmAeabiAttributesTag ABI_align8_preserved;
        public static final ArmAeabiAttributesTag ABI_align_needed;
        public static final ArmAeabiAttributesTag ABI_enum_size;
        public static final ArmAeabiAttributesTag ABI_optimization_goals;
        public static final ArmAeabiAttributesTag ARM_ISA_use;
        public static final ArmAeabiAttributesTag Advanced_SIMD_arch;
        public static final ArmAeabiAttributesTag CPU_arch;
        public static final ArmAeabiAttributesTag CPU_arch_profile;
        public static final ArmAeabiAttributesTag CPU_name;
        public static final ArmAeabiAttributesTag CPU_raw_name;
        public static final ArmAeabiAttributesTag CPU_unaligned_access;
        public static final ArmAeabiAttributesTag DIV_use;
        public static final ArmAeabiAttributesTag FP_HP_extension;
        public static final ArmAeabiAttributesTag FP_arch;
        public static final ArmAeabiAttributesTag File;
        public static final ArmAeabiAttributesTag MPextension_use;
        public static final ArmAeabiAttributesTag MPextension_use2;
        public static final ArmAeabiAttributesTag PCS_config;
        public static final ArmAeabiAttributesTag Section;
        public static final ArmAeabiAttributesTag Symbol;
        public static final ArmAeabiAttributesTag T2EE_use;
        public static final ArmAeabiAttributesTag THUMB_ISA_use;
        public static final ArmAeabiAttributesTag Virtualization_use;
        public static final ArmAeabiAttributesTag WMMX_arch;
        public static final ArmAeabiAttributesTag also_compatible_with;
        public static final ArmAeabiAttributesTag compatibility;
        public static final ArmAeabiAttributesTag conformance;
        private static final Map<String, ArmAeabiAttributesTag> nameMap = new HashMap();
        public static final ArmAeabiAttributesTag nodefaults;
        private static final List<ArmAeabiAttributesTag> tags = new LinkedList();
        private static final Map<Integer, ArmAeabiAttributesTag> valueMap = new HashMap();
        private final String name;
        private final ParameterType parameterType;
        private final int value;

        public enum ParameterType {
            UINT32,
            NTBS,
            ULEB128
        }

        static {
            ParameterType parameterType2 = ParameterType.UINT32;
            File = addTag(1, "File", parameterType2);
            Section = addTag(2, "Section", parameterType2);
            Symbol = addTag(3, "Symbol", parameterType2);
            ParameterType parameterType3 = ParameterType.NTBS;
            CPU_raw_name = addTag(4, "CPU_raw_name", parameterType3);
            CPU_name = addTag(5, "CPU_name", parameterType3);
            ParameterType parameterType4 = ParameterType.ULEB128;
            CPU_arch = addTag(6, "CPU_arch", parameterType4);
            CPU_arch_profile = addTag(7, "CPU_arch_profile", parameterType4);
            ARM_ISA_use = addTag(8, "ARM_ISA_use", parameterType4);
            THUMB_ISA_use = addTag(9, "THUMB_ISA_use", parameterType4);
            FP_arch = addTag(10, "FP_arch", parameterType4);
            WMMX_arch = addTag(11, "WMMX_arch", parameterType4);
            Advanced_SIMD_arch = addTag(12, "Advanced_SIMD_arch", parameterType4);
            PCS_config = addTag(13, "PCS_config", parameterType4);
            ABI_PCS_R9_use = addTag(14, "ABI_PCS_R9_use", parameterType4);
            ABI_PCS_RW_data = addTag(15, "ABI_PCS_RW_data", parameterType4);
            ABI_PCS_RO_data = addTag(16, "ABI_PCS_RO_data", parameterType4);
            ABI_PCS_GOT_use = addTag(17, "ABI_PCS_GOT_use", parameterType4);
            ABI_PCS_wchar_t = addTag(18, "ABI_PCS_wchar_t", parameterType4);
            ABI_FP_rounding = addTag(19, "ABI_FP_rounding", parameterType4);
            ABI_FP_denormal = addTag(20, "ABI_FP_denormal", parameterType4);
            ABI_FP_exceptions = addTag(21, "ABI_FP_exceptions", parameterType4);
            ABI_FP_user_exceptions = addTag(22, "ABI_FP_user_exceptions", parameterType4);
            ABI_FP_number_model = addTag(23, "ABI_FP_number_model", parameterType4);
            ABI_align_needed = addTag(24, "ABI_align_needed", parameterType4);
            ABI_align8_preserved = addTag(25, "ABI_align8_preserved", parameterType4);
            ABI_enum_size = addTag(26, "ABI_enum_size", parameterType4);
            ABI_HardFP_use = addTag(27, "ABI_HardFP_use", parameterType4);
            ABI_VFP_args = addTag(28, "ABI_VFP_args", parameterType4);
            ABI_WMMX_args = addTag(29, "ABI_WMMX_args", parameterType4);
            ABI_optimization_goals = addTag(30, "ABI_optimization_goals", parameterType4);
            ABI_FP_optimization_goals = addTag(31, "ABI_FP_optimization_goals", parameterType4);
            compatibility = addTag(32, "compatibility", parameterType3);
            CPU_unaligned_access = addTag(34, "CPU_unaligned_access", parameterType4);
            FP_HP_extension = addTag(36, "FP_HP_extension", parameterType4);
            ABI_FP_16bit_format = addTag(38, "ABI_FP_16bit_format", parameterType4);
            MPextension_use = addTag(42, "MPextension_use", parameterType4);
            DIV_use = addTag(44, "DIV_use", parameterType4);
            nodefaults = addTag(64, "nodefaults", parameterType4);
            also_compatible_with = addTag(65, "also_compatible_with", parameterType3);
            conformance = addTag(67, "conformance", parameterType3);
            T2EE_use = addTag(66, "T2EE_use", parameterType4);
            Virtualization_use = addTag(68, "Virtualization_use", parameterType4);
            MPextension_use2 = addTag(70, "MPextension_use", parameterType4);
        }

        public ArmAeabiAttributesTag(int i3, String str, ParameterType parameterType2) {
            this.value = i3;
            this.name = str;
            this.parameterType = parameterType2;
        }

        private static ArmAeabiAttributesTag addTag(int i3, String str, ParameterType parameterType2) {
            ArmAeabiAttributesTag armAeabiAttributesTag = new ArmAeabiAttributesTag(i3, str, parameterType2);
            Map<Integer, ArmAeabiAttributesTag> map = valueMap;
            if (!map.containsKey(Integer.valueOf(armAeabiAttributesTag.getValue()))) {
                map.put(Integer.valueOf(armAeabiAttributesTag.getValue()), armAeabiAttributesTag);
            }
            Map<String, ArmAeabiAttributesTag> map2 = nameMap;
            if (!map2.containsKey(armAeabiAttributesTag.getName())) {
                map2.put(armAeabiAttributesTag.getName(), armAeabiAttributesTag);
            }
            tags.add(armAeabiAttributesTag);
            return armAeabiAttributesTag;
        }

        public static ArmAeabiAttributesTag getByName(String str) {
            return nameMap.get(str);
        }

        public static ArmAeabiAttributesTag getByValue(int i3) {
            Map<Integer, ArmAeabiAttributesTag> map = valueMap;
            return map.containsKey(Integer.valueOf(i3)) ? map.get(Integer.valueOf(i3)) : new ArmAeabiAttributesTag(i3, a.k("Unknown ", i3), getParameterType(i3));
        }

        public static List<ArmAeabiAttributesTag> getTags() {
            return Collections.unmodifiableList(tags);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.value == ((ArmAeabiAttributesTag) obj).value;
        }

        public String getName() {
            return this.name;
        }

        public ParameterType getParameterType() {
            return this.parameterType;
        }

        public int getValue() {
            return this.value;
        }

        public int hashCode() {
            return 469 + this.value;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.name);
            sb.append(" (");
            return a.m(sb, ")", this.value);
        }

        private static ParameterType getParameterType(int i3) {
            ArmAeabiAttributesTag byValue = getByValue(i3);
            if (byValue != null) {
                return byValue.getParameterType();
            }
            if (i3 % 2 == 0) {
                return ParameterType.ULEB128;
            }
            return ParameterType.NTBS;
        }
    }

    public static class ELFSectionHeaderEntry {
        private final long addr;
        private final long flags;
        private final int link;
        private String name;
        private final int nameOffset;
        private final long offset;
        private final long size;
        private final int type;

        public ELFSectionHeaderEntry(boolean z2, ByteBuffer byteBuffer) {
            this.nameOffset = byteBuffer.getInt(0);
            this.type = byteBuffer.getInt(4);
            this.flags = z2 ? byteBuffer.getLong(8) : (long) byteBuffer.getInt(8);
            this.addr = z2 ? byteBuffer.getLong(16) : (long) byteBuffer.getInt(12);
            int i3 = 24;
            this.offset = z2 ? byteBuffer.getLong(24) : (long) byteBuffer.getInt(16);
            this.size = z2 ? byteBuffer.getLong(32) : (long) byteBuffer.getInt(20);
            this.link = byteBuffer.getInt(z2 ? 40 : i3);
        }

        public long getAddr() {
            return this.addr;
        }

        public long getFlags() {
            return this.flags;
        }

        public int getLink() {
            return this.link;
        }

        public String getName() {
            return this.name;
        }

        public int getNameOffset() {
            return this.nameOffset;
        }

        public long getOffset() {
            return this.offset;
        }

        public long getSize() {
            return this.size;
        }

        public int getType() {
            return this.type;
        }

        public void setName(String str) {
            this.name = str;
        }

        public String toString() {
            return String.format("ELFSectionHeaderEntry{nameOffset=%1$d (0x%1$x), name=%2$s, type=%3$d (0x%3$x), flags=%4$d (0x%4$x), addr=%5$d (0x%5$x), offset=%6$d (0x%6$x), size=%7$d (0x%7$x), link=%8$d (0x%8$x)}", new Object[]{Integer.valueOf(this.nameOffset), this.name, Integer.valueOf(this.type), Long.valueOf(this.flags), Long.valueOf(this.addr), Long.valueOf(this.offset), Long.valueOf(this.size), Integer.valueOf(this.link)});
        }
    }

    public static class ELFSectionHeaders {
        private final List<ELFSectionHeaderEntry> entries = new ArrayList();

        public ELFSectionHeaders(boolean z2, boolean z3, ByteBuffer byteBuffer, RandomAccessFile randomAccessFile) throws IOException {
            int i3;
            short s3;
            short s4;
            long j2;
            byte b3;
            if (z2) {
                j2 = byteBuffer.getLong(40);
                s4 = byteBuffer.getShort(58);
                s3 = byteBuffer.getShort(60);
                i3 = byteBuffer.getShort(62);
            } else {
                j2 = (long) byteBuffer.getInt(32);
                s4 = byteBuffer.getShort(46);
                s3 = byteBuffer.getShort(48);
                i3 = byteBuffer.getShort(50);
            }
            ByteBuffer allocate = ByteBuffer.allocate(s4);
            randomAccessFile.getChannel().read(allocate, j2);
            ELFSectionHeaderEntry eLFSectionHeaderEntry = new ELFSectionHeaderEntry(z2, allocate);
            int size = (s3 != 0 || j2 == 0) ? s3 : (int) eLFSectionHeaderEntry.getSize();
            i3 = i3 == 65535 ? eLFSectionHeaderEntry.getLink() : i3;
            int i4 = size * s4;
            if (i4 != 0 && i3 != 0) {
                ByteBuffer allocate2 = ByteBuffer.allocate(i4);
                allocate2.order(z3 ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
                randomAccessFile.getChannel().read(allocate2, j2);
                for (int i5 = 0; i5 < s3; i5++) {
                    allocate2.position(i5 * s4);
                    ByteBuffer slice = allocate2.slice();
                    slice.order(allocate2.order());
                    slice.limit(s4);
                    this.entries.add(new ELFSectionHeaderEntry(z2, slice));
                }
                ELFSectionHeaderEntry eLFSectionHeaderEntry2 = this.entries.get(i3);
                ByteBuffer allocate3 = ByteBuffer.allocate((int) eLFSectionHeaderEntry2.getSize());
                allocate3.order(z3 ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
                randomAccessFile.getChannel().read(allocate3, eLFSectionHeaderEntry2.getOffset());
                allocate3.rewind();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(20);
                for (ELFSectionHeaderEntry next : this.entries) {
                    byteArrayOutputStream.reset();
                    allocate3.position(next.getNameOffset());
                    while (allocate3.position() < allocate3.limit() && (b3 = allocate3.get()) != 0) {
                        byteArrayOutputStream.write(b3);
                    }
                    next.setName(byteArrayOutputStream.toString("ASCII"));
                }
            }
        }

        public List<ELFSectionHeaderEntry> getEntries() {
            return this.entries;
        }
    }

    private ELFAnalyser(String str) {
        this.filename = str;
    }

    public static ELFAnalyser analyse(String str) throws IOException {
        ELFAnalyser eLFAnalyser = new ELFAnalyser(str);
        eLFAnalyser.runDetection();
        return eLFAnalyser;
    }

    private static Map<Integer, Map<ArmAeabiAttributesTag, Object>> parseAEABI(ByteBuffer byteBuffer) {
        HashMap hashMap = new HashMap();
        while (byteBuffer.position() < byteBuffer.limit()) {
            int position = byteBuffer.position();
            int intValue = readULEB128(byteBuffer).intValue();
            int i3 = byteBuffer.getInt();
            if (intValue == 1) {
                hashMap.put(Integer.valueOf(intValue), parseFileAttribute(byteBuffer));
            }
            byteBuffer.position(position + i3);
        }
        return hashMap;
    }

    private static Map<Integer, Map<ArmAeabiAttributesTag, Object>> parseArmAttributes(ByteBuffer byteBuffer) {
        if (byteBuffer.get() != 65) {
            return Collections.EMPTY_MAP;
        }
        while (byteBuffer.position() < byteBuffer.limit()) {
            int position = byteBuffer.position();
            int i3 = byteBuffer.getInt();
            if (i3 <= 0) {
                break;
            } else if ("aeabi".equals(readNTBS(byteBuffer, (Integer) null))) {
                return parseAEABI(byteBuffer);
            } else {
                byteBuffer.position(position + i3);
            }
        }
        return Collections.EMPTY_MAP;
    }

    private void parseEabiAapcsVfp(ByteBuffer byteBuffer, RandomAccessFile randomAccessFile) throws IOException {
        for (ELFSectionHeaderEntry next : new ELFSectionHeaders(this._64Bit, this.bigEndian, byteBuffer, randomAccessFile).getEntries()) {
            if (".ARM.attributes".equals(next.getName())) {
                ByteBuffer allocate = ByteBuffer.allocate((int) next.getSize());
                allocate.order(this.bigEndian ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
                randomAccessFile.getChannel().read(allocate, next.getOffset());
                allocate.rewind();
                Map map = parseArmAttributes(allocate).get(1);
                if (map != null) {
                    Object obj = map.get(ArmAeabiAttributesTag.ABI_VFP_args);
                    if ((obj instanceof Integer) && ((Integer) obj).equals(1)) {
                        this.armEabiAapcsVfp = true;
                    } else if ((obj instanceof BigInteger) && ((BigInteger) obj).intValue() == 1) {
                        this.armEabiAapcsVfp = true;
                    }
                }
            }
        }
    }

    private static Map<ArmAeabiAttributesTag, Object> parseFileAttribute(ByteBuffer byteBuffer) {
        HashMap hashMap = new HashMap();
        while (byteBuffer.position() < byteBuffer.limit()) {
            ArmAeabiAttributesTag byValue = ArmAeabiAttributesTag.getByValue(readULEB128(byteBuffer).intValue());
            int ordinal = byValue.getParameterType().ordinal();
            if (ordinal == 0) {
                hashMap.put(byValue, Integer.valueOf(byteBuffer.getInt()));
            } else if (ordinal == 1) {
                hashMap.put(byValue, readNTBS(byteBuffer, (Integer) null));
            } else if (ordinal == 2) {
                hashMap.put(byValue, readULEB128(byteBuffer));
            }
        }
        return hashMap;
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x000d A[LOOP:0: B:3:0x000d->B:6:0x001b, LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String readNTBS(java.nio.ByteBuffer r2, java.lang.Integer r3) {
        /*
            if (r3 == 0) goto L_0x0009
            int r3 = r3.intValue()
            r2.position(r3)
        L_0x0009:
            int r3 = r2.position()
        L_0x000d:
            byte r0 = r2.get()
            if (r0 == 0) goto L_0x001d
            int r0 = r2.position()
            int r1 = r2.limit()
            if (r0 <= r1) goto L_0x000d
        L_0x001d:
            int r0 = r2.position()
            int r0 = r0 - r3
            int r0 = r0 + -1
            byte[] r0 = new byte[r0]
            r2.position(r3)
            r2.get(r0)
            int r3 = r2.position()
            int r3 = r3 + 1
            r2.position(r3)
            java.lang.String r2 = new java.lang.String     // Catch:{ UnsupportedEncodingException -> 0x003d }
            java.lang.String r3 = "ASCII"
            r2.<init>(r0, r3)     // Catch:{ UnsupportedEncodingException -> 0x003d }
            return r2
        L_0x003d:
            r2 = move-exception
            java.lang.RuntimeException r3 = new java.lang.RuntimeException
            r3.<init>(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.jna.ELFAnalyser.readNTBS(java.nio.ByteBuffer, java.lang.Integer):java.lang.String");
    }

    private static BigInteger readULEB128(ByteBuffer byteBuffer) {
        BigInteger bigInteger = BigInteger.ZERO;
        int i3 = 0;
        while (true) {
            byte b3 = byteBuffer.get();
            bigInteger = bigInteger.or(BigInteger.valueOf((long) (b3 & Byte.MAX_VALUE)).shiftLeft(i3));
            if ((b3 & 128) == 0) {
                return bigInteger;
            }
            i3 += 7;
        }
    }

    private void runDetection() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(this.filename, "r");
        try {
            boolean z2 = true;
            if (randomAccessFile.length() > 4) {
                byte[] bArr = new byte[4];
                randomAccessFile.seek(0);
                randomAccessFile.read(bArr);
                if (Arrays.equals(bArr, ELF_MAGIC)) {
                    this.ELF = true;
                }
            }
            if (!this.ELF) {
                try {
                    randomAccessFile.close();
                } catch (IOException unused) {
                }
            } else {
                randomAccessFile.seek(4);
                byte readByte = randomAccessFile.readByte();
                byte readByte2 = randomAccessFile.readByte();
                this._64Bit = readByte == 2;
                this.bigEndian = readByte2 == 2;
                randomAccessFile.seek(0);
                ByteBuffer allocate = ByteBuffer.allocate(this._64Bit ? 64 : 52);
                randomAccessFile.getChannel().read(allocate, 0);
                allocate.order(this.bigEndian ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
                boolean z3 = allocate.get(18) == 40;
                this.arm = z3;
                if (z3) {
                    int i3 = allocate.getInt(this._64Bit ? 48 : 36);
                    this.armHardFloatFlag = (i3 & 1024) == 1024;
                    if ((i3 & 512) != 512) {
                        z2 = false;
                    }
                    this.armSoftFloatFlag = z2;
                    parseEabiAapcsVfp(allocate, randomAccessFile);
                }
                try {
                    randomAccessFile.close();
                } catch (IOException unused2) {
                }
            }
        } catch (Throwable th) {
            try {
                randomAccessFile.close();
            } catch (IOException unused3) {
            }
            throw th;
        }
    }

    public String getFilename() {
        return this.filename;
    }

    public boolean is64Bit() {
        return this._64Bit;
    }

    public boolean isArm() {
        return this.arm;
    }

    public boolean isArmEabiAapcsVfp() {
        return this.armEabiAapcsVfp;
    }

    public boolean isArmHardFloat() {
        return isArmEabiAapcsVfp() || isArmHardFloatFlag();
    }

    public boolean isArmHardFloatFlag() {
        return this.armHardFloatFlag;
    }

    public boolean isArmSoftFloatFlag() {
        return this.armSoftFloatFlag;
    }

    public boolean isBigEndian() {
        return this.bigEndian;
    }

    public boolean isELF() {
        return this.ELF;
    }
}
