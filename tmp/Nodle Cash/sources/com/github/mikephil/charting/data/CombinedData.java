package com.github.mikephil.charting.data;

import android.util.Log;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarLineScatterCandleBubbleDataSet;
import java.util.ArrayList;
import java.util.List;

public class CombinedData extends BarLineScatterCandleBubbleData<IBarLineScatterCandleBubbleDataSet<? extends Entry>> {
    private BarData mBarData;
    private BubbleData mBubbleData;
    private CandleData mCandleData;
    private LineData mLineData;
    private ScatterData mScatterData;

    public void calcMinMax() {
        if (this.mDataSets == null) {
            this.mDataSets = new ArrayList();
        }
        this.mDataSets.clear();
        this.mYMax = -3.4028235E38f;
        this.mYMin = Float.MAX_VALUE;
        this.mXMax = -3.4028235E38f;
        this.mXMin = Float.MAX_VALUE;
        this.mLeftAxisMax = -3.4028235E38f;
        this.mLeftAxisMin = Float.MAX_VALUE;
        this.mRightAxisMax = -3.4028235E38f;
        this.mRightAxisMin = Float.MAX_VALUE;
        for (ChartData next : getAllData()) {
            next.calcMinMax();
            this.mDataSets.addAll(next.getDataSets());
            if (next.getYMax() > this.mYMax) {
                this.mYMax = next.getYMax();
            }
            if (next.getYMin() < this.mYMin) {
                this.mYMin = next.getYMin();
            }
            if (next.getXMax() > this.mXMax) {
                this.mXMax = next.getXMax();
            }
            if (next.getXMin() < this.mXMin) {
                this.mXMin = next.getXMin();
            }
            float f2 = next.mLeftAxisMax;
            if (f2 > this.mLeftAxisMax) {
                this.mLeftAxisMax = f2;
            }
            float f3 = next.mLeftAxisMin;
            if (f3 < this.mLeftAxisMin) {
                this.mLeftAxisMin = f3;
            }
            float f4 = next.mRightAxisMax;
            if (f4 > this.mRightAxisMax) {
                this.mRightAxisMax = f4;
            }
            float f5 = next.mRightAxisMin;
            if (f5 < this.mRightAxisMin) {
                this.mRightAxisMin = f5;
            }
        }
    }

    public List<BarLineScatterCandleBubbleData> getAllData() {
        ArrayList arrayList = new ArrayList();
        LineData lineData = this.mLineData;
        if (lineData != null) {
            arrayList.add(lineData);
        }
        BarData barData = this.mBarData;
        if (barData != null) {
            arrayList.add(barData);
        }
        ScatterData scatterData = this.mScatterData;
        if (scatterData != null) {
            arrayList.add(scatterData);
        }
        CandleData candleData = this.mCandleData;
        if (candleData != null) {
            arrayList.add(candleData);
        }
        BubbleData bubbleData = this.mBubbleData;
        if (bubbleData != null) {
            arrayList.add(bubbleData);
        }
        return arrayList;
    }

    public BarData getBarData() {
        return this.mBarData;
    }

    public BubbleData getBubbleData() {
        return this.mBubbleData;
    }

    public CandleData getCandleData() {
        return this.mCandleData;
    }

    public BarLineScatterCandleBubbleData getDataByIndex(int i3) {
        return getAllData().get(i3);
    }

    public int getDataIndex(ChartData chartData) {
        return getAllData().indexOf(chartData);
    }

    public IBarLineScatterCandleBubbleDataSet<? extends Entry> getDataSetByHighlight(Highlight highlight) {
        if (highlight.getDataIndex() >= getAllData().size()) {
            return null;
        }
        BarLineScatterCandleBubbleData dataByIndex = getDataByIndex(highlight.getDataIndex());
        if (highlight.getDataSetIndex() >= dataByIndex.getDataSetCount()) {
            return null;
        }
        return (IBarLineScatterCandleBubbleDataSet) dataByIndex.getDataSets().get(highlight.getDataSetIndex());
    }

    /* JADX WARNING: Removed duplicated region for block: B:9:0x003d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.github.mikephil.charting.data.Entry getEntryForHighlight(com.github.mikephil.charting.highlight.Highlight r5) {
        /*
            r4 = this;
            int r0 = r5.getDataIndex()
            java.util.List r1 = r4.getAllData()
            int r1 = r1.size()
            r2 = 0
            if (r0 < r1) goto L_0x0010
            return r2
        L_0x0010:
            int r0 = r5.getDataIndex()
            com.github.mikephil.charting.data.BarLineScatterCandleBubbleData r4 = r4.getDataByIndex(r0)
            int r0 = r5.getDataSetIndex()
            int r1 = r4.getDataSetCount()
            if (r0 < r1) goto L_0x0023
            return r2
        L_0x0023:
            int r0 = r5.getDataSetIndex()
            com.github.mikephil.charting.interfaces.datasets.IDataSet r4 = r4.getDataSetByIndex(r0)
            float r0 = r5.getX()
            java.util.List r4 = r4.getEntriesForXValue(r0)
            java.util.Iterator r4 = r4.iterator()
        L_0x0037:
            boolean r0 = r4.hasNext()
            if (r0 == 0) goto L_0x005a
            java.lang.Object r0 = r4.next()
            com.github.mikephil.charting.data.Entry r0 = (com.github.mikephil.charting.data.Entry) r0
            float r1 = r0.getY()
            float r3 = r5.getY()
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 == 0) goto L_0x0059
            float r1 = r5.getY()
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 == 0) goto L_0x0037
        L_0x0059:
            return r0
        L_0x005a:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.mikephil.charting.data.CombinedData.getEntryForHighlight(com.github.mikephil.charting.highlight.Highlight):com.github.mikephil.charting.data.Entry");
    }

    public LineData getLineData() {
        return this.mLineData;
    }

    public ScatterData getScatterData() {
        return this.mScatterData;
    }

    public void notifyDataChanged() {
        LineData lineData = this.mLineData;
        if (lineData != null) {
            lineData.notifyDataChanged();
        }
        BarData barData = this.mBarData;
        if (barData != null) {
            barData.notifyDataChanged();
        }
        CandleData candleData = this.mCandleData;
        if (candleData != null) {
            candleData.notifyDataChanged();
        }
        ScatterData scatterData = this.mScatterData;
        if (scatterData != null) {
            scatterData.notifyDataChanged();
        }
        BubbleData bubbleData = this.mBubbleData;
        if (bubbleData != null) {
            bubbleData.notifyDataChanged();
        }
        calcMinMax();
    }

    @Deprecated
    public boolean removeEntry(Entry entry, int i3) {
        Log.e(Chart.LOG_TAG, "removeEntry(...) not supported for CombinedData");
        return false;
    }

    public void setData(LineData lineData) {
        this.mLineData = lineData;
        notifyDataChanged();
    }

    /* JADX WARNING: Removed duplicated region for block: B:1:0x0009 A[LOOP:0: B:1:0x0009->B:4:0x0019, LOOP_START, PHI: r0 
      PHI: (r0v1 boolean) = (r0v0 boolean), (r0v5 boolean) binds: [B:0:0x0000, B:4:0x0019] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean removeDataSet(com.github.mikephil.charting.interfaces.datasets.IBarLineScatterCandleBubbleDataSet<? extends com.github.mikephil.charting.data.Entry> r3) {
        /*
            r2 = this;
            java.util.List r2 = r2.getAllData()
            java.util.Iterator r2 = r2.iterator()
            r0 = 0
        L_0x0009:
            boolean r1 = r2.hasNext()
            if (r1 == 0) goto L_0x001b
            java.lang.Object r0 = r2.next()
            com.github.mikephil.charting.data.ChartData r0 = (com.github.mikephil.charting.data.ChartData) r0
            boolean r0 = r0.removeDataSet(r3)
            if (r0 == 0) goto L_0x0009
        L_0x001b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.mikephil.charting.data.CombinedData.removeDataSet(com.github.mikephil.charting.interfaces.datasets.IBarLineScatterCandleBubbleDataSet):boolean");
    }

    @Deprecated
    public boolean removeEntry(float f2, int i3) {
        Log.e(Chart.LOG_TAG, "removeEntry(...) not supported for CombinedData");
        return false;
    }

    public void setData(BarData barData) {
        this.mBarData = barData;
        notifyDataChanged();
    }

    @Deprecated
    public boolean removeDataSet(int i3) {
        Log.e(Chart.LOG_TAG, "removeDataSet(int index) not supported for CombinedData");
        return false;
    }

    public void setData(ScatterData scatterData) {
        this.mScatterData = scatterData;
        notifyDataChanged();
    }

    public void setData(CandleData candleData) {
        this.mCandleData = candleData;
        notifyDataChanged();
    }

    public void setData(BubbleData bubbleData) {
        this.mBubbleData = bubbleData;
        notifyDataChanged();
    }
}
