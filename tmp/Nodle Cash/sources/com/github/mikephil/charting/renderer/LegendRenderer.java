package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.utils.FSize;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.ArrayList;
import java.util.List;

public class LegendRenderer extends Renderer {
    protected List<LegendEntry> computedEntries = new ArrayList(16);
    protected Paint.FontMetrics legendFontMetrics = new Paint.FontMetrics();
    protected Legend mLegend;
    protected Paint mLegendFormPaint;
    protected Paint mLegendLabelPaint;
    private Path mLineFormPath = new Path();

    /* renamed from: com.github.mikephil.charting.renderer.LegendRenderer$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$github$mikephil$charting$components$Legend$LegendForm;
        static final /* synthetic */ int[] $SwitchMap$com$github$mikephil$charting$components$Legend$LegendHorizontalAlignment;
        static final /* synthetic */ int[] $SwitchMap$com$github$mikephil$charting$components$Legend$LegendOrientation;
        static final /* synthetic */ int[] $SwitchMap$com$github$mikephil$charting$components$Legend$LegendVerticalAlignment;

        /* JADX WARNING: Can't wrap try/catch for region: R(32:0|(2:1|2)|3|(2:5|6)|7|9|10|11|12|13|14|15|16|17|19|20|(2:21|22)|23|25|26|27|28|29|30|31|33|34|35|36|37|38|40) */
        /* JADX WARNING: Can't wrap try/catch for region: R(34:0|1|2|3|(2:5|6)|7|9|10|11|12|13|14|15|16|17|19|20|21|22|23|25|26|27|28|29|30|31|33|34|35|36|37|38|40) */
        /* JADX WARNING: Can't wrap try/catch for region: R(35:0|1|2|3|5|6|7|9|10|11|12|13|14|15|16|17|19|20|21|22|23|25|26|27|28|29|30|31|33|34|35|36|37|38|40) */
        /* JADX WARNING: Code restructure failed: missing block: B:41:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0033 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x005a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0075 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x007f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x009a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x00a4 */
        static {
            /*
                com.github.mikephil.charting.components.Legend$LegendForm[] r0 = com.github.mikephil.charting.components.Legend.LegendForm.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$github$mikephil$charting$components$Legend$LegendForm = r0
                r1 = 1
                com.github.mikephil.charting.components.Legend$LegendForm r2 = com.github.mikephil.charting.components.Legend.LegendForm.NONE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$com$github$mikephil$charting$components$Legend$LegendForm     // Catch:{ NoSuchFieldError -> 0x001d }
                com.github.mikephil.charting.components.Legend$LegendForm r3 = com.github.mikephil.charting.components.Legend.LegendForm.EMPTY     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$com$github$mikephil$charting$components$Legend$LegendForm     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.github.mikephil.charting.components.Legend$LegendForm r4 = com.github.mikephil.charting.components.Legend.LegendForm.DEFAULT     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r3 = $SwitchMap$com$github$mikephil$charting$components$Legend$LegendForm     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.github.mikephil.charting.components.Legend$LegendForm r4 = com.github.mikephil.charting.components.Legend.LegendForm.CIRCLE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r5 = 4
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r3 = $SwitchMap$com$github$mikephil$charting$components$Legend$LegendForm     // Catch:{ NoSuchFieldError -> 0x003e }
                com.github.mikephil.charting.components.Legend$LegendForm r4 = com.github.mikephil.charting.components.Legend.LegendForm.SQUARE     // Catch:{ NoSuchFieldError -> 0x003e }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r5 = 5
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r3 = $SwitchMap$com$github$mikephil$charting$components$Legend$LegendForm     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.github.mikephil.charting.components.Legend$LegendForm r4 = com.github.mikephil.charting.components.Legend.LegendForm.LINE     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r5 = 6
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                com.github.mikephil.charting.components.Legend$LegendOrientation[] r3 = com.github.mikephil.charting.components.Legend.LegendOrientation.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                $SwitchMap$com$github$mikephil$charting$components$Legend$LegendOrientation = r3
                com.github.mikephil.charting.components.Legend$LegendOrientation r4 = com.github.mikephil.charting.components.Legend.LegendOrientation.HORIZONTAL     // Catch:{ NoSuchFieldError -> 0x005a }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x005a }
                r3[r4] = r1     // Catch:{ NoSuchFieldError -> 0x005a }
            L_0x005a:
                int[] r3 = $SwitchMap$com$github$mikephil$charting$components$Legend$LegendOrientation     // Catch:{ NoSuchFieldError -> 0x0064 }
                com.github.mikephil.charting.components.Legend$LegendOrientation r4 = com.github.mikephil.charting.components.Legend.LegendOrientation.VERTICAL     // Catch:{ NoSuchFieldError -> 0x0064 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0064 }
                r3[r4] = r0     // Catch:{ NoSuchFieldError -> 0x0064 }
            L_0x0064:
                com.github.mikephil.charting.components.Legend$LegendVerticalAlignment[] r3 = com.github.mikephil.charting.components.Legend.LegendVerticalAlignment.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                $SwitchMap$com$github$mikephil$charting$components$Legend$LegendVerticalAlignment = r3
                com.github.mikephil.charting.components.Legend$LegendVerticalAlignment r4 = com.github.mikephil.charting.components.Legend.LegendVerticalAlignment.TOP     // Catch:{ NoSuchFieldError -> 0x0075 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0075 }
                r3[r4] = r1     // Catch:{ NoSuchFieldError -> 0x0075 }
            L_0x0075:
                int[] r3 = $SwitchMap$com$github$mikephil$charting$components$Legend$LegendVerticalAlignment     // Catch:{ NoSuchFieldError -> 0x007f }
                com.github.mikephil.charting.components.Legend$LegendVerticalAlignment r4 = com.github.mikephil.charting.components.Legend.LegendVerticalAlignment.BOTTOM     // Catch:{ NoSuchFieldError -> 0x007f }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x007f }
                r3[r4] = r0     // Catch:{ NoSuchFieldError -> 0x007f }
            L_0x007f:
                int[] r3 = $SwitchMap$com$github$mikephil$charting$components$Legend$LegendVerticalAlignment     // Catch:{ NoSuchFieldError -> 0x0089 }
                com.github.mikephil.charting.components.Legend$LegendVerticalAlignment r4 = com.github.mikephil.charting.components.Legend.LegendVerticalAlignment.CENTER     // Catch:{ NoSuchFieldError -> 0x0089 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0089 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0089 }
            L_0x0089:
                com.github.mikephil.charting.components.Legend$LegendHorizontalAlignment[] r3 = com.github.mikephil.charting.components.Legend.LegendHorizontalAlignment.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                $SwitchMap$com$github$mikephil$charting$components$Legend$LegendHorizontalAlignment = r3
                com.github.mikephil.charting.components.Legend$LegendHorizontalAlignment r4 = com.github.mikephil.charting.components.Legend.LegendHorizontalAlignment.LEFT     // Catch:{ NoSuchFieldError -> 0x009a }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x009a }
                r3[r4] = r1     // Catch:{ NoSuchFieldError -> 0x009a }
            L_0x009a:
                int[] r1 = $SwitchMap$com$github$mikephil$charting$components$Legend$LegendHorizontalAlignment     // Catch:{ NoSuchFieldError -> 0x00a4 }
                com.github.mikephil.charting.components.Legend$LegendHorizontalAlignment r3 = com.github.mikephil.charting.components.Legend.LegendHorizontalAlignment.RIGHT     // Catch:{ NoSuchFieldError -> 0x00a4 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a4 }
                r1[r3] = r0     // Catch:{ NoSuchFieldError -> 0x00a4 }
            L_0x00a4:
                int[] r0 = $SwitchMap$com$github$mikephil$charting$components$Legend$LegendHorizontalAlignment     // Catch:{ NoSuchFieldError -> 0x00ae }
                com.github.mikephil.charting.components.Legend$LegendHorizontalAlignment r1 = com.github.mikephil.charting.components.Legend.LegendHorizontalAlignment.CENTER     // Catch:{ NoSuchFieldError -> 0x00ae }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00ae }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00ae }
            L_0x00ae:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.github.mikephil.charting.renderer.LegendRenderer.AnonymousClass1.<clinit>():void");
        }
    }

    public LegendRenderer(ViewPortHandler viewPortHandler, Legend legend) {
        super(viewPortHandler);
        this.mLegend = legend;
        Paint paint = new Paint(1);
        this.mLegendLabelPaint = paint;
        paint.setTextSize(Utils.convertDpToPixel(9.0f));
        this.mLegendLabelPaint.setTextAlign(Paint.Align.LEFT);
        Paint paint2 = new Paint(1);
        this.mLegendFormPaint = paint2;
        paint2.setStyle(Paint.Style.FILL);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v17, resolved type: com.github.mikephil.charting.data.ChartData<?>} */
    /* JADX WARNING: type inference failed for: r7v2, types: [com.github.mikephil.charting.interfaces.datasets.IDataSet] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void computeLegend(com.github.mikephil.charting.data.ChartData<?> r19) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            com.github.mikephil.charting.components.Legend r2 = r0.mLegend
            boolean r2 = r2.isLegendCustom()
            if (r2 != 0) goto L_0x01c1
            java.util.List<com.github.mikephil.charting.components.LegendEntry> r2 = r0.computedEntries
            r2.clear()
            r3 = 0
        L_0x0012:
            int r4 = r19.getDataSetCount()
            if (r3 >= r4) goto L_0x01a7
            com.github.mikephil.charting.interfaces.datasets.IDataSet r4 = r1.getDataSetByIndex(r3)
            java.util.List r5 = r4.getColors()
            int r6 = r4.getEntryCount()
            boolean r7 = r4 instanceof com.github.mikephil.charting.interfaces.datasets.IBarDataSet
            if (r7 == 0) goto L_0x0096
            r7 = r4
            com.github.mikephil.charting.interfaces.datasets.IBarDataSet r7 = (com.github.mikephil.charting.interfaces.datasets.IBarDataSet) r7
            boolean r8 = r7.isStacked()
            if (r8 == 0) goto L_0x0096
            java.lang.String[] r6 = r7.getStackLabels()
            r8 = 0
        L_0x0036:
            int r9 = r5.size()
            if (r8 >= r9) goto L_0x0074
            int r9 = r7.getStackSize()
            if (r8 >= r9) goto L_0x0074
            java.util.List<com.github.mikephil.charting.components.LegendEntry> r9 = r0.computedEntries
            com.github.mikephil.charting.components.LegendEntry r15 = new com.github.mikephil.charting.components.LegendEntry
            int r10 = r6.length
            int r10 = r8 % r10
            r11 = r6[r10]
            com.github.mikephil.charting.components.Legend$LegendForm r12 = r4.getForm()
            float r13 = r4.getFormSize()
            float r14 = r4.getFormLineWidth()
            android.graphics.DashPathEffect r16 = r4.getFormLineDashEffect()
            java.lang.Object r10 = r5.get(r8)
            java.lang.Integer r10 = (java.lang.Integer) r10
            int r17 = r10.intValue()
            r10 = r15
            r2 = r15
            r15 = r16
            r16 = r17
            r10.<init>(r11, r12, r13, r14, r15, r16)
            r9.add(r2)
            int r8 = r8 + 1
            goto L_0x0036
        L_0x0074:
            java.lang.String r2 = r7.getLabel()
            if (r2 == 0) goto L_0x0093
            java.util.List<com.github.mikephil.charting.components.LegendEntry> r2 = r0.computedEntries
            com.github.mikephil.charting.components.LegendEntry r12 = new com.github.mikephil.charting.components.LegendEntry
            java.lang.String r6 = r4.getLabel()
            com.github.mikephil.charting.components.Legend$LegendForm r7 = com.github.mikephil.charting.components.Legend.LegendForm.NONE
            r10 = 0
            r11 = 1122867(0x112233, float:1.573472E-39)
            r8 = 2143289344(0x7fc00000, float:NaN)
            r9 = 2143289344(0x7fc00000, float:NaN)
            r5 = r12
            r5.<init>(r6, r7, r8, r9, r10, r11)
            r2.add(r12)
        L_0x0093:
            r2 = r1
            goto L_0x01a2
        L_0x0096:
            boolean r2 = r4 instanceof com.github.mikephil.charting.interfaces.datasets.IPieDataSet
            if (r2 == 0) goto L_0x0100
            r2 = r4
            com.github.mikephil.charting.interfaces.datasets.IPieDataSet r2 = (com.github.mikephil.charting.interfaces.datasets.IPieDataSet) r2
            r7 = 0
        L_0x009e:
            int r8 = r5.size()
            if (r7 >= r8) goto L_0x00dd
            if (r7 >= r6) goto L_0x00dd
            java.util.List<com.github.mikephil.charting.components.LegendEntry> r8 = r0.computedEntries
            com.github.mikephil.charting.components.LegendEntry r15 = new com.github.mikephil.charting.components.LegendEntry
            com.github.mikephil.charting.data.Entry r9 = r2.getEntryForIndex(r7)
            com.github.mikephil.charting.data.PieEntry r9 = (com.github.mikephil.charting.data.PieEntry) r9
            java.lang.String r10 = r9.getLabel()
            com.github.mikephil.charting.components.Legend$LegendForm r11 = r4.getForm()
            float r12 = r4.getFormSize()
            float r13 = r4.getFormLineWidth()
            android.graphics.DashPathEffect r14 = r4.getFormLineDashEffect()
            java.lang.Object r9 = r5.get(r7)
            java.lang.Integer r9 = (java.lang.Integer) r9
            int r16 = r9.intValue()
            r9 = r15
            r1 = r15
            r15 = r16
            r9.<init>(r10, r11, r12, r13, r14, r15)
            r8.add(r1)
            int r7 = r7 + 1
            r1 = r19
            goto L_0x009e
        L_0x00dd:
            java.lang.String r1 = r2.getLabel()
            if (r1 == 0) goto L_0x00fc
            java.util.List<com.github.mikephil.charting.components.LegendEntry> r1 = r0.computedEntries
            com.github.mikephil.charting.components.LegendEntry r2 = new com.github.mikephil.charting.components.LegendEntry
            java.lang.String r6 = r4.getLabel()
            com.github.mikephil.charting.components.Legend$LegendForm r7 = com.github.mikephil.charting.components.Legend.LegendForm.NONE
            r10 = 0
            r11 = 1122867(0x112233, float:1.573472E-39)
            r8 = 2143289344(0x7fc00000, float:NaN)
            r9 = 2143289344(0x7fc00000, float:NaN)
            r5 = r2
            r5.<init>(r6, r7, r8, r9, r10, r11)
            r1.add(r2)
        L_0x00fc:
            r2 = r19
            goto L_0x01a2
        L_0x0100:
            boolean r1 = r4 instanceof com.github.mikephil.charting.interfaces.datasets.ICandleDataSet
            if (r1 == 0) goto L_0x0155
            r1 = r4
            com.github.mikephil.charting.interfaces.datasets.ICandleDataSet r1 = (com.github.mikephil.charting.interfaces.datasets.ICandleDataSet) r1
            int r2 = r1.getDecreasingColor()
            r7 = 1122867(0x112233, float:1.573472E-39)
            if (r2 == r7) goto L_0x0155
            int r14 = r1.getDecreasingColor()
            int r1 = r1.getIncreasingColor()
            java.util.List<com.github.mikephil.charting.components.LegendEntry> r2 = r0.computedEntries
            com.github.mikephil.charting.components.LegendEntry r5 = new com.github.mikephil.charting.components.LegendEntry
            com.github.mikephil.charting.components.Legend$LegendForm r10 = r4.getForm()
            float r11 = r4.getFormSize()
            float r12 = r4.getFormLineWidth()
            android.graphics.DashPathEffect r13 = r4.getFormLineDashEffect()
            r9 = 0
            r8 = r5
            r8.<init>(r9, r10, r11, r12, r13, r14)
            r2.add(r5)
            java.util.List<com.github.mikephil.charting.components.LegendEntry> r2 = r0.computedEntries
            com.github.mikephil.charting.components.LegendEntry r12 = new com.github.mikephil.charting.components.LegendEntry
            java.lang.String r6 = r4.getLabel()
            com.github.mikephil.charting.components.Legend$LegendForm r7 = r4.getForm()
            float r8 = r4.getFormSize()
            float r9 = r4.getFormLineWidth()
            android.graphics.DashPathEffect r10 = r4.getFormLineDashEffect()
            r5 = r12
            r11 = r1
            r5.<init>(r6, r7, r8, r9, r10, r11)
            r2.add(r12)
            goto L_0x00fc
        L_0x0155:
            r1 = 0
        L_0x0156:
            int r2 = r5.size()
            if (r1 >= r2) goto L_0x00fc
            if (r1 >= r6) goto L_0x00fc
            int r2 = r5.size()
            int r2 = r2 + -1
            if (r1 >= r2) goto L_0x016f
            int r2 = r6 + -1
            if (r1 >= r2) goto L_0x016f
            r2 = 0
            r9 = r2
            r2 = r19
            goto L_0x017a
        L_0x016f:
            r2 = r19
            com.github.mikephil.charting.interfaces.datasets.IDataSet r7 = r2.getDataSetByIndex(r3)
            java.lang.String r7 = r7.getLabel()
            r9 = r7
        L_0x017a:
            java.util.List<com.github.mikephil.charting.components.LegendEntry> r7 = r0.computedEntries
            com.github.mikephil.charting.components.LegendEntry r15 = new com.github.mikephil.charting.components.LegendEntry
            com.github.mikephil.charting.components.Legend$LegendForm r10 = r4.getForm()
            float r11 = r4.getFormSize()
            float r12 = r4.getFormLineWidth()
            android.graphics.DashPathEffect r13 = r4.getFormLineDashEffect()
            java.lang.Object r8 = r5.get(r1)
            java.lang.Integer r8 = (java.lang.Integer) r8
            int r14 = r8.intValue()
            r8 = r15
            r8.<init>(r9, r10, r11, r12, r13, r14)
            r7.add(r15)
            int r1 = r1 + 1
            goto L_0x0156
        L_0x01a2:
            int r3 = r3 + 1
            r1 = r2
            goto L_0x0012
        L_0x01a7:
            com.github.mikephil.charting.components.Legend r1 = r0.mLegend
            com.github.mikephil.charting.components.LegendEntry[] r1 = r1.getExtraEntries()
            if (r1 == 0) goto L_0x01ba
            java.util.List<com.github.mikephil.charting.components.LegendEntry> r1 = r0.computedEntries
            com.github.mikephil.charting.components.Legend r2 = r0.mLegend
            com.github.mikephil.charting.components.LegendEntry[] r2 = r2.getExtraEntries()
            java.util.Collections.addAll(r1, r2)
        L_0x01ba:
            com.github.mikephil.charting.components.Legend r1 = r0.mLegend
            java.util.List<com.github.mikephil.charting.components.LegendEntry> r2 = r0.computedEntries
            r1.setEntries(r2)
        L_0x01c1:
            com.github.mikephil.charting.components.Legend r1 = r0.mLegend
            android.graphics.Typeface r1 = r1.getTypeface()
            if (r1 == 0) goto L_0x01ce
            android.graphics.Paint r2 = r0.mLegendLabelPaint
            r2.setTypeface(r1)
        L_0x01ce:
            android.graphics.Paint r1 = r0.mLegendLabelPaint
            com.github.mikephil.charting.components.Legend r2 = r0.mLegend
            float r2 = r2.getTextSize()
            r1.setTextSize(r2)
            android.graphics.Paint r1 = r0.mLegendLabelPaint
            com.github.mikephil.charting.components.Legend r2 = r0.mLegend
            int r2 = r2.getTextColor()
            r1.setColor(r2)
            com.github.mikephil.charting.components.Legend r1 = r0.mLegend
            android.graphics.Paint r2 = r0.mLegendLabelPaint
            com.github.mikephil.charting.utils.ViewPortHandler r0 = r0.mViewPortHandler
            r1.calculateDimensions(r2, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.mikephil.charting.renderer.LegendRenderer.computeLegend(com.github.mikephil.charting.data.ChartData):void");
    }

    public void drawForm(Canvas canvas, float f2, float f3, LegendEntry legendEntry, Legend legend) {
        int i3 = legendEntry.formColor;
        if (i3 != 1122868 && i3 != 1122867 && i3 != 0) {
            int save = canvas.save();
            Legend.LegendForm legendForm = legendEntry.form;
            if (legendForm == Legend.LegendForm.DEFAULT) {
                legendForm = legend.getForm();
            }
            this.mLegendFormPaint.setColor(legendEntry.formColor);
            float convertDpToPixel = Utils.convertDpToPixel(Float.isNaN(legendEntry.formSize) ? legend.getFormSize() : legendEntry.formSize);
            float f4 = convertDpToPixel / 2.0f;
            int i4 = AnonymousClass1.$SwitchMap$com$github$mikephil$charting$components$Legend$LegendForm[legendForm.ordinal()];
            if (i4 == 3 || i4 == 4) {
                this.mLegendFormPaint.setStyle(Paint.Style.FILL);
                canvas.drawCircle(f2 + f4, f3, f4, this.mLegendFormPaint);
            } else if (i4 == 5) {
                this.mLegendFormPaint.setStyle(Paint.Style.FILL);
                canvas.drawRect(f2, f3 - f4, f2 + convertDpToPixel, f3 + f4, this.mLegendFormPaint);
            } else if (i4 == 6) {
                float convertDpToPixel2 = Utils.convertDpToPixel(Float.isNaN(legendEntry.formLineWidth) ? legend.getFormLineWidth() : legendEntry.formLineWidth);
                DashPathEffect dashPathEffect = legendEntry.formLineDashEffect;
                if (dashPathEffect == null) {
                    dashPathEffect = legend.getFormLineDashEffect();
                }
                this.mLegendFormPaint.setStyle(Paint.Style.STROKE);
                this.mLegendFormPaint.setStrokeWidth(convertDpToPixel2);
                this.mLegendFormPaint.setPathEffect(dashPathEffect);
                this.mLineFormPath.reset();
                this.mLineFormPath.moveTo(f2, f3);
                this.mLineFormPath.lineTo(f2 + convertDpToPixel, f3);
                canvas.drawPath(this.mLineFormPath, this.mLegendFormPaint);
            }
            canvas.restoreToCount(save);
        }
    }

    public void drawLabel(Canvas canvas, float f2, float f3, String str) {
        canvas.drawText(str, f2, f3, this.mLegendLabelPaint);
    }

    public Paint getFormPaint() {
        return this.mLegendFormPaint;
    }

    public Paint getLabelPaint() {
        return this.mLegendLabelPaint;
    }

    public void renderLegend(Canvas canvas) {
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        List<Boolean> list;
        List<FSize> list2;
        int i3;
        float f7;
        float f8;
        float f9;
        float f10;
        float f11;
        float f12;
        float f13;
        float f14;
        float f15;
        Legend.LegendDirection legendDirection;
        LegendEntry legendEntry;
        double d2;
        Canvas canvas2 = canvas;
        if (this.mLegend.isEnabled()) {
            Typeface typeface = this.mLegend.getTypeface();
            if (typeface != null) {
                this.mLegendLabelPaint.setTypeface(typeface);
            }
            this.mLegendLabelPaint.setTextSize(this.mLegend.getTextSize());
            this.mLegendLabelPaint.setColor(this.mLegend.getTextColor());
            float lineHeight = Utils.getLineHeight(this.mLegendLabelPaint, this.legendFontMetrics);
            float convertDpToPixel = Utils.convertDpToPixel(this.mLegend.getYEntrySpace()) + Utils.getLineSpacing(this.mLegendLabelPaint, this.legendFontMetrics);
            float calcTextHeight = lineHeight - (((float) Utils.calcTextHeight(this.mLegendLabelPaint, "ABC")) / 2.0f);
            LegendEntry[] entries = this.mLegend.getEntries();
            float convertDpToPixel2 = Utils.convertDpToPixel(this.mLegend.getFormToTextSpace());
            float convertDpToPixel3 = Utils.convertDpToPixel(this.mLegend.getXEntrySpace());
            Legend.LegendOrientation orientation = this.mLegend.getOrientation();
            Legend.LegendHorizontalAlignment horizontalAlignment = this.mLegend.getHorizontalAlignment();
            Legend.LegendVerticalAlignment verticalAlignment = this.mLegend.getVerticalAlignment();
            Legend.LegendDirection direction = this.mLegend.getDirection();
            float convertDpToPixel4 = Utils.convertDpToPixel(this.mLegend.getFormSize());
            float convertDpToPixel5 = Utils.convertDpToPixel(this.mLegend.getStackSpace());
            float yOffset = this.mLegend.getYOffset();
            float xOffset = this.mLegend.getXOffset();
            int i4 = AnonymousClass1.$SwitchMap$com$github$mikephil$charting$components$Legend$LegendHorizontalAlignment[horizontalAlignment.ordinal()];
            float f16 = convertDpToPixel5;
            float f17 = convertDpToPixel3;
            if (i4 == 1) {
                f3 = lineHeight;
                f2 = convertDpToPixel;
                if (orientation != Legend.LegendOrientation.VERTICAL) {
                    xOffset += this.mViewPortHandler.contentLeft();
                }
                f4 = direction == Legend.LegendDirection.RIGHT_TO_LEFT ? xOffset + this.mLegend.mNeededWidth : xOffset;
            } else if (i4 == 2) {
                f3 = lineHeight;
                f2 = convertDpToPixel;
                f4 = (orientation == Legend.LegendOrientation.VERTICAL ? this.mViewPortHandler.getChartWidth() : this.mViewPortHandler.contentRight()) - xOffset;
                if (direction == Legend.LegendDirection.LEFT_TO_RIGHT) {
                    f4 -= this.mLegend.mNeededWidth;
                }
            } else if (i4 != 3) {
                f3 = lineHeight;
                f2 = convertDpToPixel;
                f4 = 0.0f;
            } else {
                Legend.LegendOrientation legendOrientation = Legend.LegendOrientation.VERTICAL;
                float chartWidth = orientation == legendOrientation ? this.mViewPortHandler.getChartWidth() / 2.0f : this.mViewPortHandler.contentLeft() + (this.mViewPortHandler.contentWidth() / 2.0f);
                Legend.LegendDirection legendDirection2 = Legend.LegendDirection.LEFT_TO_RIGHT;
                f2 = convertDpToPixel;
                f4 = chartWidth + (direction == legendDirection2 ? xOffset : -xOffset);
                if (orientation == legendOrientation) {
                    double d3 = (double) f4;
                    if (direction == legendDirection2) {
                        f3 = lineHeight;
                        d2 = (((double) (-this.mLegend.mNeededWidth)) / 2.0d) + ((double) xOffset);
                    } else {
                        f3 = lineHeight;
                        d2 = (((double) this.mLegend.mNeededWidth) / 2.0d) - ((double) xOffset);
                    }
                    f4 = (float) (d3 + d2);
                } else {
                    f3 = lineHeight;
                }
            }
            int i5 = AnonymousClass1.$SwitchMap$com$github$mikephil$charting$components$Legend$LegendOrientation[orientation.ordinal()];
            if (i5 == 1) {
                Canvas canvas3 = canvas;
                float f18 = f4;
                float f19 = calcTextHeight;
                float f20 = f16;
                Legend.LegendDirection legendDirection3 = direction;
                List<FSize> calculatedLineSizes = this.mLegend.getCalculatedLineSizes();
                List<FSize> calculatedLabelSizes = this.mLegend.getCalculatedLabelSizes();
                List<Boolean> calculatedLabelBreakPoints = this.mLegend.getCalculatedLabelBreakPoints();
                int i6 = AnonymousClass1.$SwitchMap$com$github$mikephil$charting$components$Legend$LegendVerticalAlignment[verticalAlignment.ordinal()];
                if (i6 != 1) {
                    yOffset = i6 != 2 ? i6 != 3 ? 0.0f : yOffset + ((this.mViewPortHandler.getChartHeight() - this.mLegend.mNeededHeight) / 2.0f) : (this.mViewPortHandler.getChartHeight() - yOffset) - this.mLegend.mNeededHeight;
                }
                int length = entries.length;
                float f21 = f18;
                int i7 = 0;
                int i8 = 0;
                while (i7 < length) {
                    float f22 = f20;
                    LegendEntry legendEntry2 = entries[i7];
                    float f23 = f21;
                    int i9 = length;
                    boolean z2 = legendEntry2.form != Legend.LegendForm.NONE;
                    float convertDpToPixel6 = Float.isNaN(legendEntry2.formSize) ? convertDpToPixel4 : Utils.convertDpToPixel(legendEntry2.formSize);
                    if (i7 >= calculatedLabelBreakPoints.size() || !calculatedLabelBreakPoints.get(i7).booleanValue()) {
                        f6 = f23;
                        f5 = yOffset;
                    } else {
                        f5 = f3 + f2 + yOffset;
                        f6 = f18;
                    }
                    if (f6 == f18 && horizontalAlignment == Legend.LegendHorizontalAlignment.CENTER && i8 < calculatedLineSizes.size()) {
                        f6 += (legendDirection3 == Legend.LegendDirection.RIGHT_TO_LEFT ? calculatedLineSizes.get(i8).width : -calculatedLineSizes.get(i8).width) / 2.0f;
                        i8++;
                    }
                    int i10 = i8;
                    boolean z3 = legendEntry2.label == null;
                    if (z2) {
                        if (legendDirection3 == Legend.LegendDirection.RIGHT_TO_LEFT) {
                            f6 -= convertDpToPixel6;
                        }
                        float f24 = f6;
                        list2 = calculatedLineSizes;
                        i3 = i7;
                        list = calculatedLabelBreakPoints;
                        drawForm(canvas, f24, f5 + f19, legendEntry2, this.mLegend);
                        f6 = legendDirection3 == Legend.LegendDirection.LEFT_TO_RIGHT ? f24 + convertDpToPixel6 : f24;
                    } else {
                        list = calculatedLabelBreakPoints;
                        list2 = calculatedLineSizes;
                        i3 = i7;
                    }
                    if (!z3) {
                        if (z2) {
                            f6 += legendDirection3 == Legend.LegendDirection.RIGHT_TO_LEFT ? -convertDpToPixel2 : convertDpToPixel2;
                        }
                        Legend.LegendDirection legendDirection4 = Legend.LegendDirection.RIGHT_TO_LEFT;
                        if (legendDirection3 == legendDirection4) {
                            f6 -= calculatedLabelSizes.get(i3).width;
                        }
                        drawLabel(canvas3, f6, f5 + f3, legendEntry2.label);
                        if (legendDirection3 == Legend.LegendDirection.LEFT_TO_RIGHT) {
                            f6 += calculatedLabelSizes.get(i3).width;
                        }
                        if (legendDirection3 == legendDirection4) {
                            f8 = f17;
                            f10 = -f8;
                        } else {
                            f8 = f17;
                            f10 = f8;
                        }
                        f21 = f6 + f10;
                        f7 = f22;
                    } else {
                        f8 = f17;
                        if (legendDirection3 == Legend.LegendDirection.RIGHT_TO_LEFT) {
                            f7 = f22;
                            f9 = -f7;
                        } else {
                            f7 = f22;
                            f9 = f7;
                        }
                        f21 = f6 + f9;
                    }
                    f17 = f8;
                    f20 = f7;
                    i7 = i3 + 1;
                    yOffset = f5;
                    length = i9;
                    i8 = i10;
                    calculatedLineSizes = list2;
                    calculatedLabelBreakPoints = list;
                }
            } else if (i5 == 2) {
                int i11 = AnonymousClass1.$SwitchMap$com$github$mikephil$charting$components$Legend$LegendVerticalAlignment[verticalAlignment.ordinal()];
                if (i11 == 1) {
                    f11 = (horizontalAlignment == Legend.LegendHorizontalAlignment.CENTER ? 0.0f : this.mViewPortHandler.contentTop()) + yOffset;
                } else if (i11 == 2) {
                    f11 = (horizontalAlignment == Legend.LegendHorizontalAlignment.CENTER ? this.mViewPortHandler.getChartHeight() : this.mViewPortHandler.contentBottom()) - (this.mLegend.mNeededHeight + yOffset);
                } else if (i11 != 3) {
                    f11 = 0.0f;
                } else {
                    Legend legend = this.mLegend;
                    f11 = legend.getYOffset() + ((this.mViewPortHandler.getChartHeight() / 2.0f) - (legend.mNeededHeight / 2.0f));
                }
                float f25 = f11;
                float f26 = 0.0f;
                boolean z4 = false;
                int i12 = 0;
                while (i12 < entries.length) {
                    LegendEntry legendEntry3 = entries[i12];
                    boolean z5 = legendEntry3.form != Legend.LegendForm.NONE;
                    float convertDpToPixel7 = Float.isNaN(legendEntry3.formSize) ? convertDpToPixel4 : Utils.convertDpToPixel(legendEntry3.formSize);
                    if (z5) {
                        Legend.LegendDirection legendDirection5 = Legend.LegendDirection.LEFT_TO_RIGHT;
                        f14 = direction == legendDirection5 ? f4 + f26 : f4 - (convertDpToPixel7 - f26);
                        Legend.LegendDirection legendDirection6 = legendDirection5;
                        float f27 = f25 + calcTextHeight;
                        f13 = calcTextHeight;
                        f15 = f16;
                        LegendEntry legendEntry4 = legendEntry3;
                        f12 = f4;
                        legendDirection = direction;
                        drawForm(canvas, f14, f27, legendEntry3, this.mLegend);
                        if (legendDirection == legendDirection6) {
                            f14 += convertDpToPixel7;
                        }
                        legendEntry = legendEntry4;
                    } else {
                        f12 = f4;
                        f13 = calcTextHeight;
                        f15 = f16;
                        legendDirection = direction;
                        legendEntry = legendEntry3;
                        f14 = f12;
                    }
                    String str = legendEntry.label;
                    if (str != null) {
                        if (z5 && !z4) {
                            f14 += legendDirection == Legend.LegendDirection.LEFT_TO_RIGHT ? convertDpToPixel2 : -convertDpToPixel2;
                        } else if (z4) {
                            f14 = f12;
                        }
                        if (legendDirection == Legend.LegendDirection.RIGHT_TO_LEFT) {
                            f14 -= (float) Utils.calcTextWidth(this.mLegendLabelPaint, str);
                        }
                        float f28 = f14;
                        if (!z4) {
                            drawLabel(canvas, f28, f25 + f3, legendEntry.label);
                        } else {
                            f25 += f3 + f2;
                            drawLabel(canvas, f28, f25 + f3, legendEntry.label);
                        }
                        f25 = f3 + f2 + f25;
                        f26 = 0.0f;
                    } else {
                        Canvas canvas4 = canvas;
                        f26 = convertDpToPixel7 + f15 + f26;
                        z4 = true;
                    }
                    i12++;
                    direction = legendDirection;
                    f16 = f15;
                    calcTextHeight = f13;
                    f4 = f12;
                }
            }
        }
    }
}
