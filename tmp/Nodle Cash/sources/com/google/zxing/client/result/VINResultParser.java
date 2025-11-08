package com.google.zxing.client.result;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import java.util.regex.Pattern;

public final class VINResultParser extends ResultParser {
    private static final Pattern AZ09 = Pattern.compile("[A-Z0-9]{17}");
    private static final Pattern IOQ = Pattern.compile("[IOQ]");

    private static char checkChar(int i3) {
        if (i3 < 10) {
            return (char) (i3 + 48);
        }
        if (i3 == 10) {
            return 'X';
        }
        throw new IllegalArgumentException();
    }

    private static boolean checkChecksum(CharSequence charSequence) {
        int i3 = 0;
        int i4 = 0;
        while (i3 < charSequence.length()) {
            int i5 = i3 + 1;
            i4 += vinPositionWeight(i5) * vinCharValue(charSequence.charAt(i3));
            i3 = i5;
        }
        return charSequence.charAt(8) == checkChar(i4 % 11);
    }

    private static String countryCode(CharSequence charSequence) {
        char charAt = charSequence.charAt(0);
        char charAt2 = charSequence.charAt(1);
        if (charAt != '9') {
            if (charAt != 'S') {
                if (charAt != 'Z') {
                    switch (charAt) {
                        case '1':
                        case '4':
                        case '5':
                            return "US";
                        case '2':
                            return "CA";
                        case '3':
                            if (charAt2 < 'A' || charAt2 > 'W') {
                                return null;
                            }
                            return "MX";
                        default:
                            switch (charAt) {
                                case 'J':
                                    if (charAt2 < 'A' || charAt2 > 'T') {
                                        return null;
                                    }
                                    return "JP";
                                case 'K':
                                    if (charAt2 < 'L' || charAt2 > 'R') {
                                        return null;
                                    }
                                    return "KO";
                                case 'L':
                                    return "CN";
                                case 'M':
                                    if (charAt2 < 'A' || charAt2 > 'E') {
                                        return null;
                                    }
                                    return "IN";
                                default:
                                    switch (charAt) {
                                        case 'V':
                                            if (charAt2 >= 'F' && charAt2 <= 'R') {
                                                return "FR";
                                            }
                                            if (charAt2 < 'S' || charAt2 > 'W') {
                                                return null;
                                            }
                                            return "ES";
                                        case 'W':
                                            return "DE";
                                        case 'X':
                                            if (charAt2 == '0') {
                                                return "RU";
                                            }
                                            if (charAt2 < '3' || charAt2 > '9') {
                                                return null;
                                            }
                                            return "RU";
                                        default:
                                            return null;
                                    }
                            }
                    }
                } else if (charAt2 < 'A' || charAt2 > 'R') {
                    return null;
                } else {
                    return "IT";
                }
            } else if (charAt2 >= 'A' && charAt2 <= 'M') {
                return "UK";
            } else {
                if (charAt2 < 'N' || charAt2 > 'T') {
                    return null;
                }
                return "DE";
            }
        } else if (charAt2 >= 'A' && charAt2 <= 'E') {
            return "BR";
        } else {
            if (charAt2 < '3' || charAt2 > '9') {
                return null;
            }
            return "BR";
        }
    }

    private static int modelYear(char c3) {
        if (c3 >= 'E' && c3 <= 'H') {
            return c3 + 1915;
        }
        if (c3 >= 'J' && c3 <= 'N') {
            return c3 + 1914;
        }
        if (c3 == 'P') {
            return 1993;
        }
        if (c3 >= 'R' && c3 <= 'T') {
            return c3 + 1912;
        }
        if (c3 >= 'V' && c3 <= 'Y') {
            return c3 + 1911;
        }
        if (c3 >= '1' && c3 <= '9') {
            return c3 + 1952;
        }
        if (c3 >= 'A' && c3 <= 'D') {
            return c3 + 1945;
        }
        throw new IllegalArgumentException();
    }

    private static int vinCharValue(char c3) {
        if (c3 >= 'A' && c3 <= 'I') {
            return c3 - '@';
        }
        if (c3 >= 'J' && c3 <= 'R') {
            return c3 - 'I';
        }
        if (c3 >= 'S' && c3 <= 'Z') {
            return c3 - 'Q';
        }
        if (c3 >= '0' && c3 <= '9') {
            return c3 - '0';
        }
        throw new IllegalArgumentException();
    }

    private static int vinPositionWeight(int i3) {
        if (i3 >= 1 && i3 <= 7) {
            return 9 - i3;
        }
        if (i3 == 8) {
            return 10;
        }
        if (i3 == 9) {
            return 0;
        }
        if (i3 >= 10 && i3 <= 17) {
            return 19 - i3;
        }
        throw new IllegalArgumentException();
    }

    public VINParsedResult parse(Result result) {
        if (result.getBarcodeFormat() != BarcodeFormat.CODE_39) {
            return null;
        }
        String trim = IOQ.matcher(result.getText()).replaceAll("").trim();
        if (!AZ09.matcher(trim).matches()) {
            return null;
        }
        try {
            if (!checkChecksum(trim)) {
                return null;
            }
            String substring = trim.substring(0, 3);
            return new VINParsedResult(trim, substring, trim.substring(3, 9), trim.substring(9, 17), countryCode(substring), trim.substring(3, 8), modelYear(trim.charAt(9)), trim.charAt(10), trim.substring(11));
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }
}
