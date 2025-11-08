package com.github.mikephil.charting.formatter;

import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.text.DecimalFormat;
import org.apache.xerces.impl.xs.SchemaSymbols;

public class DefaultValueFormatter extends ValueFormatter {
    protected int mDecimalDigits;
    protected DecimalFormat mFormat;

    public DefaultValueFormatter(int i3) {
        setup(i3);
    }

    public int getDecimalDigits() {
        return this.mDecimalDigits;
    }

    public String getFormattedValue(float f2) {
        return this.mFormat.format((double) f2);
    }

    public void setup(int i3) {
        this.mDecimalDigits = i3;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i4 = 0; i4 < i3; i4++) {
            if (i4 == 0) {
                stringBuffer.append(JwtUtilsKt.JWT_DELIMITER);
            }
            stringBuffer.append(SchemaSymbols.ATTVAL_FALSE_0);
        }
        this.mFormat = new DecimalFormat("###,###,###,##0" + stringBuffer.toString());
    }
}
