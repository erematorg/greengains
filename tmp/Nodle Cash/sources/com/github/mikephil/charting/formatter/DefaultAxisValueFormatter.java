package com.github.mikephil.charting.formatter;

import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.text.DecimalFormat;
import org.apache.xerces.impl.xs.SchemaSymbols;

public class DefaultAxisValueFormatter extends ValueFormatter {
    protected int digits;
    protected DecimalFormat mFormat;

    public DefaultAxisValueFormatter(int i3) {
        this.digits = i3;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i4 = 0; i4 < i3; i4++) {
            if (i4 == 0) {
                stringBuffer.append(JwtUtilsKt.JWT_DELIMITER);
            }
            stringBuffer.append(SchemaSymbols.ATTVAL_FALSE_0);
        }
        this.mFormat = new DecimalFormat("###,###,###,##0" + stringBuffer.toString());
    }

    public int getDecimalDigits() {
        return this.digits;
    }

    public String getFormattedValue(float f2) {
        return this.mFormat.format((double) f2);
    }
}
