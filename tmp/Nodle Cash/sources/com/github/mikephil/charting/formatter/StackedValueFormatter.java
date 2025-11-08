package com.github.mikephil.charting.formatter;

import com.github.mikephil.charting.data.BarEntry;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.text.DecimalFormat;
import org.apache.xerces.impl.xs.SchemaSymbols;

public class StackedValueFormatter extends ValueFormatter {
    private boolean mDrawWholeStack;
    private DecimalFormat mFormat;
    private String mSuffix;

    public StackedValueFormatter(boolean z2, String str, int i3) {
        this.mDrawWholeStack = z2;
        this.mSuffix = str;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i4 = 0; i4 < i3; i4++) {
            if (i4 == 0) {
                stringBuffer.append(JwtUtilsKt.JWT_DELIMITER);
            }
            stringBuffer.append(SchemaSymbols.ATTVAL_FALSE_0);
        }
        this.mFormat = new DecimalFormat("###,###,###,##0" + stringBuffer.toString());
    }

    public String getBarStackedLabel(float f2, BarEntry barEntry) {
        float[] yVals;
        if (this.mDrawWholeStack || (yVals = barEntry.getYVals()) == null) {
            return this.mFormat.format((double) f2) + this.mSuffix;
        } else if (yVals[yVals.length - 1] != f2) {
            return "";
        } else {
            return this.mFormat.format((double) barEntry.getY()) + this.mSuffix;
        }
    }
}
