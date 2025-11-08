package com.google.zxing.oned.rss.expanded.decoders;

import android.support.v4.media.session.a;
import com.google.zxing.NotFoundException;
import java.util.HashMap;
import java.util.Map;

final class FieldParser {
    private static final Map<String, DataLength> FOUR_DIGIT_DATA_LENGTH;
    private static final Map<String, DataLength> THREE_DIGIT_DATA_LENGTH;
    private static final Map<String, DataLength> THREE_DIGIT_PLUS_DIGIT_DATA_LENGTH = new HashMap();
    private static final Map<String, DataLength> TWO_DIGIT_DATA_LENGTH;

    public static final class DataLength {
        final int length;
        final boolean variable;

        private DataLength(boolean z2, int i3) {
            this.variable = z2;
            this.length = i3;
        }

        public static DataLength fixed(int i3) {
            return new DataLength(false, i3);
        }

        public static DataLength variable(int i3) {
            return new DataLength(true, i3);
        }
    }

    static {
        HashMap hashMap = new HashMap();
        TWO_DIGIT_DATA_LENGTH = hashMap;
        hashMap.put("00", DataLength.fixed(18));
        hashMap.put("01", DataLength.fixed(14));
        hashMap.put("02", DataLength.fixed(14));
        hashMap.put("10", DataLength.variable(20));
        hashMap.put("11", DataLength.fixed(6));
        hashMap.put("12", DataLength.fixed(6));
        hashMap.put("13", DataLength.fixed(6));
        hashMap.put("15", DataLength.fixed(6));
        hashMap.put("16", DataLength.fixed(6));
        hashMap.put("17", DataLength.fixed(6));
        hashMap.put("20", DataLength.fixed(2));
        hashMap.put("21", DataLength.variable(20));
        hashMap.put("22", DataLength.variable(29));
        hashMap.put("30", DataLength.variable(8));
        hashMap.put("37", DataLength.variable(8));
        for (int i3 = 90; i3 <= 99; i3++) {
            TWO_DIGIT_DATA_LENGTH.put(String.valueOf(i3), DataLength.variable(30));
        }
        HashMap hashMap2 = new HashMap();
        THREE_DIGIT_DATA_LENGTH = hashMap2;
        hashMap2.put("235", DataLength.variable(28));
        hashMap2.put("240", DataLength.variable(30));
        hashMap2.put("241", DataLength.variable(30));
        hashMap2.put("242", DataLength.variable(6));
        hashMap2.put("243", DataLength.variable(20));
        hashMap2.put("250", DataLength.variable(30));
        hashMap2.put("251", DataLength.variable(30));
        hashMap2.put("253", DataLength.variable(30));
        hashMap2.put("254", DataLength.variable(20));
        hashMap2.put("255", DataLength.variable(25));
        hashMap2.put("400", DataLength.variable(30));
        hashMap2.put("401", DataLength.variable(30));
        hashMap2.put("402", DataLength.fixed(17));
        hashMap2.put("403", DataLength.variable(30));
        hashMap2.put("410", DataLength.fixed(13));
        hashMap2.put("411", DataLength.fixed(13));
        hashMap2.put("412", DataLength.fixed(13));
        hashMap2.put("413", DataLength.fixed(13));
        hashMap2.put("414", DataLength.fixed(13));
        hashMap2.put("415", DataLength.fixed(13));
        hashMap2.put("416", DataLength.fixed(13));
        hashMap2.put("417", DataLength.fixed(13));
        hashMap2.put("420", DataLength.variable(20));
        hashMap2.put("421", DataLength.variable(15));
        hashMap2.put("422", DataLength.fixed(3));
        hashMap2.put("423", DataLength.variable(15));
        hashMap2.put("424", DataLength.fixed(3));
        hashMap2.put("425", DataLength.variable(15));
        hashMap2.put("426", DataLength.fixed(3));
        hashMap2.put("427", DataLength.variable(3));
        hashMap2.put("710", DataLength.variable(20));
        hashMap2.put("711", DataLength.variable(20));
        hashMap2.put("712", DataLength.variable(20));
        hashMap2.put("713", DataLength.variable(20));
        hashMap2.put("714", DataLength.variable(20));
        hashMap2.put("715", DataLength.variable(20));
        for (int i4 = 310; i4 <= 316; i4++) {
            THREE_DIGIT_PLUS_DIGIT_DATA_LENGTH.put(String.valueOf(i4), DataLength.fixed(6));
        }
        for (int i5 = DilithiumEngine.DilithiumPolyT1PackedBytes; i5 <= 337; i5++) {
            THREE_DIGIT_PLUS_DIGIT_DATA_LENGTH.put(String.valueOf(i5), DataLength.fixed(6));
        }
        for (int i6 = 340; i6 <= 357; i6++) {
            THREE_DIGIT_PLUS_DIGIT_DATA_LENGTH.put(String.valueOf(i6), DataLength.fixed(6));
        }
        for (int i7 = 360; i7 <= 369; i7++) {
            THREE_DIGIT_PLUS_DIGIT_DATA_LENGTH.put(String.valueOf(i7), DataLength.fixed(6));
        }
        Map<String, DataLength> map = THREE_DIGIT_PLUS_DIGIT_DATA_LENGTH;
        map.put("390", DataLength.variable(15));
        map.put("391", DataLength.variable(18));
        map.put("392", DataLength.variable(15));
        map.put("393", DataLength.variable(18));
        map.put("394", DataLength.fixed(4));
        map.put("395", DataLength.fixed(6));
        map.put("703", DataLength.variable(30));
        map.put("723", DataLength.variable(30));
        HashMap hashMap3 = new HashMap();
        FOUR_DIGIT_DATA_LENGTH = hashMap3;
        hashMap3.put("4300", DataLength.variable(35));
        hashMap3.put("4301", DataLength.variable(35));
        hashMap3.put("4302", DataLength.variable(70));
        hashMap3.put("4303", DataLength.variable(70));
        hashMap3.put("4304", DataLength.variable(70));
        hashMap3.put("4305", DataLength.variable(70));
        hashMap3.put("4306", DataLength.variable(70));
        hashMap3.put("4307", DataLength.fixed(2));
        hashMap3.put("4308", DataLength.variable(30));
        hashMap3.put("4309", DataLength.fixed(20));
        hashMap3.put("4310", DataLength.variable(35));
        hashMap3.put("4311", DataLength.variable(35));
        hashMap3.put("4312", DataLength.variable(70));
        hashMap3.put("4313", DataLength.variable(70));
        hashMap3.put("4314", DataLength.variable(70));
        hashMap3.put("4315", DataLength.variable(70));
        hashMap3.put("4316", DataLength.variable(70));
        hashMap3.put("4317", DataLength.fixed(2));
        hashMap3.put("4318", DataLength.variable(20));
        hashMap3.put("4319", DataLength.variable(30));
        hashMap3.put("4320", DataLength.variable(35));
        hashMap3.put("4321", DataLength.fixed(1));
        hashMap3.put("4322", DataLength.fixed(1));
        hashMap3.put("4323", DataLength.fixed(1));
        hashMap3.put("4324", DataLength.fixed(10));
        hashMap3.put("4325", DataLength.fixed(10));
        hashMap3.put("4326", DataLength.fixed(6));
        hashMap3.put("7001", DataLength.fixed(13));
        hashMap3.put("7002", DataLength.variable(30));
        hashMap3.put("7003", DataLength.fixed(10));
        hashMap3.put("7004", DataLength.variable(4));
        hashMap3.put("7005", DataLength.variable(12));
        hashMap3.put("7006", DataLength.fixed(6));
        hashMap3.put("7007", DataLength.variable(12));
        hashMap3.put("7008", DataLength.variable(3));
        hashMap3.put("7009", DataLength.variable(10));
        hashMap3.put("7010", DataLength.variable(2));
        hashMap3.put("7011", DataLength.variable(10));
        hashMap3.put("7020", DataLength.variable(20));
        hashMap3.put("7021", DataLength.variable(20));
        hashMap3.put("7022", DataLength.variable(20));
        hashMap3.put("7023", DataLength.variable(30));
        hashMap3.put("7040", DataLength.fixed(4));
        hashMap3.put("7240", DataLength.variable(20));
        hashMap3.put("8001", DataLength.fixed(14));
        hashMap3.put("8002", DataLength.variable(20));
        hashMap3.put("8003", DataLength.variable(30));
        hashMap3.put("8004", DataLength.variable(30));
        hashMap3.put("8005", DataLength.fixed(6));
        hashMap3.put("8006", DataLength.fixed(18));
        hashMap3.put("8007", DataLength.variable(34));
        hashMap3.put("8008", DataLength.variable(12));
        hashMap3.put("8009", DataLength.variable(50));
        hashMap3.put("8010", DataLength.variable(30));
        hashMap3.put("8011", DataLength.variable(12));
        hashMap3.put("8012", DataLength.variable(20));
        hashMap3.put("8013", DataLength.variable(25));
        hashMap3.put("8017", DataLength.fixed(18));
        hashMap3.put("8018", DataLength.fixed(18));
        hashMap3.put("8019", DataLength.variable(10));
        hashMap3.put("8020", DataLength.variable(25));
        hashMap3.put("8026", DataLength.fixed(18));
        hashMap3.put("8100", DataLength.fixed(6));
        hashMap3.put("8101", DataLength.fixed(10));
        hashMap3.put("8102", DataLength.fixed(2));
        hashMap3.put("8110", DataLength.variable(70));
        hashMap3.put("8111", DataLength.fixed(4));
        hashMap3.put("8112", DataLength.variable(70));
        hashMap3.put("8200", DataLength.variable(70));
    }

    private FieldParser() {
    }

    public static String parseFieldsInGeneralPurpose(String str) throws NotFoundException {
        if (str.isEmpty()) {
            return null;
        }
        if (str.length() >= 2) {
            DataLength dataLength = TWO_DIGIT_DATA_LENGTH.get(str.substring(0, 2));
            if (dataLength != null) {
                return dataLength.variable ? processVariableAI(2, dataLength.length, str) : processFixedAI(2, dataLength.length, str);
            }
            if (str.length() >= 3) {
                String substring = str.substring(0, 3);
                DataLength dataLength2 = THREE_DIGIT_DATA_LENGTH.get(substring);
                if (dataLength2 != null) {
                    return dataLength2.variable ? processVariableAI(3, dataLength2.length, str) : processFixedAI(3, dataLength2.length, str);
                }
                if (str.length() >= 4) {
                    DataLength dataLength3 = THREE_DIGIT_PLUS_DIGIT_DATA_LENGTH.get(substring);
                    if (dataLength3 != null) {
                        return dataLength3.variable ? processVariableAI(4, dataLength3.length, str) : processFixedAI(4, dataLength3.length, str);
                    }
                    DataLength dataLength4 = FOUR_DIGIT_DATA_LENGTH.get(str.substring(0, 4));
                    if (dataLength4 != null) {
                        return dataLength4.variable ? processVariableAI(4, dataLength4.length, str) : processFixedAI(4, dataLength4.length, str);
                    }
                    throw NotFoundException.getNotFoundInstance();
                }
                throw NotFoundException.getNotFoundInstance();
            }
            throw NotFoundException.getNotFoundInstance();
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static String processFixedAI(int i3, int i4, String str) throws NotFoundException {
        if (str.length() >= i3) {
            String substring = str.substring(0, i3);
            int i5 = i4 + i3;
            if (str.length() >= i5) {
                String substring2 = str.substring(i3, i5);
                String str2 = "(" + substring + ')' + substring2;
                String parseFieldsInGeneralPurpose = parseFieldsInGeneralPurpose(str.substring(i5));
                return parseFieldsInGeneralPurpose == null ? str2 : a.m(str2, parseFieldsInGeneralPurpose);
            }
            throw NotFoundException.getNotFoundInstance();
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static String processVariableAI(int i3, int i4, String str) throws NotFoundException {
        String substring = str.substring(0, i3);
        int min = Math.min(str.length(), i4 + i3);
        String substring2 = str.substring(i3, min);
        String str2 = "(" + substring + ')' + substring2;
        String parseFieldsInGeneralPurpose = parseFieldsInGeneralPurpose(str.substring(min));
        return parseFieldsInGeneralPurpose == null ? str2 : a.m(str2, parseFieldsInGeneralPurpose);
    }
}
