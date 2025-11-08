package com.github.mikephil.charting.data;

import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import java.util.List;

public class ScatterData extends BarLineScatterCandleBubbleData<IScatterDataSet> {
    public ScatterData() {
    }

    public float getGreatestShapeSize() {
        float f2 = 0.0f;
        for (T scatterShapeSize : this.mDataSets) {
            float scatterShapeSize2 = scatterShapeSize.getScatterShapeSize();
            if (scatterShapeSize2 > f2) {
                f2 = scatterShapeSize2;
            }
        }
        return f2;
    }

    public ScatterData(List<IScatterDataSet> list) {
        super(list);
    }

    public ScatterData(IScatterDataSet... iScatterDataSetArr) {
        super((T[]) iScatterDataSetArr);
    }
}
