package com.github.mikephil.charting.formatter;

import java.text.DecimalFormat;
import org.komputing.kbip44.BIP44Kt;

public class LargeValueFormatter extends ValueFormatter {
    private DecimalFormat mFormat;
    private int mMaxLength;
    private String[] mSuffix;
    private String mText;

    public LargeValueFormatter() {
        this.mSuffix = new String[]{"", "k", BIP44Kt.BIP44_PREFIX, "b", "t"};
        this.mMaxLength = 5;
        this.mText = "";
        this.mFormat = new DecimalFormat("###E00");
    }

    private String makePretty(double d2) {
        String format = this.mFormat.format(d2);
        int numericValue = Character.getNumericValue(format.charAt(format.length() - 1));
        String replaceAll = format.replaceAll("E[0-9][0-9]", this.mSuffix[Integer.valueOf(Character.getNumericValue(format.charAt(format.length() - 2)) + "" + numericValue).intValue() / 3]);
        while (true) {
            if (replaceAll.length() <= this.mMaxLength && !replaceAll.matches("[0-9]+\\.[a-z]")) {
                return replaceAll;
            }
            replaceAll = replaceAll.substring(0, replaceAll.length() - 2) + replaceAll.substring(replaceAll.length() - 1);
        }
    }

    public int getDecimalDigits() {
        return 0;
    }

    public String getFormattedValue(float f2) {
        return makePretty((double) f2) + this.mText;
    }

    public void setAppendix(String str) {
        this.mText = str;
    }

    public void setMaxLength(int i3) {
        this.mMaxLength = i3;
    }

    public void setSuffix(String[] strArr) {
        this.mSuffix = strArr;
    }

    public LargeValueFormatter(String str) {
        this();
        this.mText = str;
    }
}
