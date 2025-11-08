package com.google.zxing.multi.qrcode.detector;

import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.detector.FinderPattern;
import com.google.zxing.qrcode.detector.FinderPatternFinder;
import com.google.zxing.qrcode.detector.FinderPatternInfo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

public final class MultiFinderPatternFinder extends FinderPatternFinder {
    private static final float DIFF_MODSIZE_CUTOFF = 0.5f;
    private static final float DIFF_MODSIZE_CUTOFF_PERCENT = 0.05f;
    private static final FinderPattern[][] EMPTY_FP_2D_ARRAY = new FinderPattern[0][];
    private static final FinderPattern[] EMPTY_FP_ARRAY = new FinderPattern[0];
    private static final FinderPatternInfo[] EMPTY_RESULT_ARRAY = new FinderPatternInfo[0];
    private static final float MAX_MODULE_COUNT_PER_EDGE = 180.0f;
    private static final float MIN_MODULE_COUNT_PER_EDGE = 9.0f;

    public static final class ModuleSizeComparator implements Comparator<FinderPattern>, Serializable {
        private ModuleSizeComparator() {
        }

        public int compare(FinderPattern finderPattern, FinderPattern finderPattern2) {
            double estimatedModuleSize = (double) (finderPattern2.getEstimatedModuleSize() - finderPattern.getEstimatedModuleSize());
            if (estimatedModuleSize < 0.0d) {
                return -1;
            }
            return estimatedModuleSize > 0.0d ? 1 : 0;
        }
    }

    public MultiFinderPatternFinder(BitMatrix bitMatrix, ResultPointCallback resultPointCallback) {
        super(bitMatrix, resultPointCallback);
    }

    private FinderPattern[][] selectMultipleBestPatterns() throws NotFoundException {
        ArrayList arrayList = new ArrayList();
        for (FinderPattern next : getPossibleCenters()) {
            if (next.getCount() >= 2) {
                arrayList.add(next);
            }
        }
        int size = arrayList.size();
        if (size < 3) {
            throw NotFoundException.getNotFoundInstance();
        } else if (size == 3) {
            return new FinderPattern[][]{(FinderPattern[]) arrayList.toArray(EMPTY_FP_ARRAY)};
        } else {
            Collections.sort(arrayList, new ModuleSizeComparator());
            ArrayList arrayList2 = new ArrayList();
            for (int i3 = 0; i3 < size - 2; i3++) {
                FinderPattern finderPattern = (FinderPattern) arrayList.get(i3);
                if (finderPattern != null) {
                    for (int i4 = i3 + 1; i4 < size - 1; i4++) {
                        FinderPattern finderPattern2 = (FinderPattern) arrayList.get(i4);
                        if (finderPattern2 != null) {
                            float estimatedModuleSize = (finderPattern.getEstimatedModuleSize() - finderPattern2.getEstimatedModuleSize()) / Math.min(finderPattern.getEstimatedModuleSize(), finderPattern2.getEstimatedModuleSize());
                            float f2 = 0.5f;
                            int i5 = (Math.abs(finderPattern.getEstimatedModuleSize() - finderPattern2.getEstimatedModuleSize()) > 0.5f ? 1 : (Math.abs(finderPattern.getEstimatedModuleSize() - finderPattern2.getEstimatedModuleSize()) == 0.5f ? 0 : -1));
                            float f3 = DIFF_MODSIZE_CUTOFF_PERCENT;
                            if (i5 > 0 && estimatedModuleSize >= DIFF_MODSIZE_CUTOFF_PERCENT) {
                                break;
                            }
                            int i6 = i4 + 1;
                            while (i6 < size) {
                                FinderPattern finderPattern3 = (FinderPattern) arrayList.get(i6);
                                if (finderPattern3 != null) {
                                    float estimatedModuleSize2 = (finderPattern2.getEstimatedModuleSize() - finderPattern3.getEstimatedModuleSize()) / Math.min(finderPattern2.getEstimatedModuleSize(), finderPattern3.getEstimatedModuleSize());
                                    if (Math.abs(finderPattern2.getEstimatedModuleSize() - finderPattern3.getEstimatedModuleSize()) > f2 && estimatedModuleSize2 >= f3) {
                                        break;
                                    }
                                    FinderPattern[] finderPatternArr = {finderPattern, finderPattern2, finderPattern3};
                                    ResultPoint.orderBestPatterns(finderPatternArr);
                                    FinderPatternInfo finderPatternInfo = new FinderPatternInfo(finderPatternArr);
                                    float distance = ResultPoint.distance(finderPatternInfo.getTopLeft(), finderPatternInfo.getBottomLeft());
                                    float distance2 = ResultPoint.distance(finderPatternInfo.getTopRight(), finderPatternInfo.getBottomLeft());
                                    float distance3 = ResultPoint.distance(finderPatternInfo.getTopLeft(), finderPatternInfo.getTopRight());
                                    float estimatedModuleSize3 = (distance + distance3) / (finderPattern.getEstimatedModuleSize() * 2.0f);
                                    if (estimatedModuleSize3 <= 180.0f && estimatedModuleSize3 >= MIN_MODULE_COUNT_PER_EDGE && Math.abs((distance - distance3) / Math.min(distance, distance3)) < 0.1f) {
                                        double d2 = (double) distance;
                                        double d3 = (double) distance3;
                                        float sqrt = (float) Math.sqrt((d3 * d3) + (d2 * d2));
                                        if (Math.abs((distance2 - sqrt) / Math.min(distance2, sqrt)) < 0.1f) {
                                            arrayList2.add(finderPatternArr);
                                        }
                                    }
                                }
                                i6++;
                                f2 = 0.5f;
                                f3 = DIFF_MODSIZE_CUTOFF_PERCENT;
                            }
                        }
                    }
                }
            }
            if (!arrayList2.isEmpty()) {
                return (FinderPattern[][]) arrayList2.toArray(EMPTY_FP_2D_ARRAY);
            }
            throw NotFoundException.getNotFoundInstance();
        }
    }

    public FinderPatternInfo[] findMulti(Map<DecodeHintType, ?> map) throws NotFoundException {
        boolean z2 = map != null && map.containsKey(DecodeHintType.TRY_HARDER);
        BitMatrix image = getImage();
        int height = image.getHeight();
        int width = image.getWidth();
        int i3 = (height * 3) / 388;
        if (i3 < 3 || z2) {
            i3 = 3;
        }
        int[] iArr = new int[5];
        for (int i4 = i3 - 1; i4 < height; i4 += i3) {
            FinderPatternFinder.doClearCounts(iArr);
            int i5 = 0;
            for (int i6 = 0; i6 < width; i6++) {
                if (image.get(i6, i4)) {
                    if ((i5 & 1) == 1) {
                        i5++;
                    }
                    iArr[i5] = iArr[i5] + 1;
                } else if ((i5 & 1) != 0) {
                    iArr[i5] = iArr[i5] + 1;
                } else if (i5 != 4) {
                    i5++;
                    iArr[i5] = iArr[i5] + 1;
                } else if (!FinderPatternFinder.foundPatternCross(iArr) || !handlePossibleCenter(iArr, i4, i6)) {
                    FinderPatternFinder.doShiftCounts2(iArr);
                    i5 = 3;
                } else {
                    FinderPatternFinder.doClearCounts(iArr);
                    i5 = 0;
                }
            }
            if (FinderPatternFinder.foundPatternCross(iArr)) {
                handlePossibleCenter(iArr, i4, width);
            }
        }
        FinderPattern[][] selectMultipleBestPatterns = selectMultipleBestPatterns();
        ArrayList arrayList = new ArrayList();
        for (FinderPattern[] finderPatternArr : selectMultipleBestPatterns) {
            ResultPoint.orderBestPatterns(finderPatternArr);
            arrayList.add(new FinderPatternInfo(finderPatternArr));
        }
        return arrayList.isEmpty() ? EMPTY_RESULT_ARRAY : (FinderPatternInfo[]) arrayList.toArray(EMPTY_RESULT_ARRAY);
    }
}
