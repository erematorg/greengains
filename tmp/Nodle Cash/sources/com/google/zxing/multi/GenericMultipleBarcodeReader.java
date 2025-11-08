package com.google.zxing.multi;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class GenericMultipleBarcodeReader implements MultipleBarcodeReader {
    static final Result[] EMPTY_RESULT_ARRAY = new Result[0];
    private static final int MAX_DEPTH = 4;
    private static final int MIN_DIMENSION_TO_RECUR = 100;
    private final Reader delegate;

    public GenericMultipleBarcodeReader(Reader reader) {
        this.delegate = reader;
    }

    private void doDecodeMultiple(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map, List<Result> list, int i3, int i4, int i5) {
        float f2;
        float f3;
        float f4;
        int i6;
        BinaryBitmap binaryBitmap2 = binaryBitmap;
        int i7 = i3;
        int i8 = i4;
        int i9 = i5;
        if (i9 <= 4) {
            try {
                Result decode = this.delegate.decode(binaryBitmap2, map);
                Iterator<Result> it = list.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (it.next().getText().equals(decode.getText())) {
                            List<Result> list2 = list;
                            break;
                        }
                    } else {
                        list.add(translateResultPoints(decode, i7, i8));
                        break;
                    }
                }
                ResultPoint[] resultPoints = decode.getResultPoints();
                if (resultPoints != null && resultPoints.length != 0) {
                    int width = binaryBitmap.getWidth();
                    int height = binaryBitmap.getHeight();
                    float f5 = (float) width;
                    float f6 = 0.0f;
                    float f7 = (float) height;
                    float f8 = 0.0f;
                    for (ResultPoint resultPoint : resultPoints) {
                        if (resultPoint != null) {
                            float x2 = resultPoint.getX();
                            float y2 = resultPoint.getY();
                            if (x2 < f5) {
                                f5 = x2;
                            }
                            if (y2 < f7) {
                                f7 = y2;
                            }
                            if (x2 > f6) {
                                f6 = x2;
                            }
                            if (y2 > f8) {
                                f8 = y2;
                            }
                        }
                    }
                    if (f5 > 100.0f) {
                        f4 = f8;
                        f3 = f6;
                        f2 = f7;
                        i6 = 0;
                        doDecodeMultiple(binaryBitmap2.crop(0, 0, (int) f5, height), map, list, i3, i4, i9 + 1);
                    } else {
                        f4 = f8;
                        f3 = f6;
                        f2 = f7;
                        i6 = 0;
                    }
                    if (f2 > 100.0f) {
                        doDecodeMultiple(binaryBitmap2.crop(i6, i6, width, (int) f2), map, list, i3, i4, i9 + 1);
                    }
                    if (f3 < ((float) (width - 100))) {
                        int i10 = (int) f3;
                        doDecodeMultiple(binaryBitmap2.crop(i10, i6, width - i10, height), map, list, i7 + i10, i4, i9 + 1);
                    }
                    if (f4 < ((float) (height - 100))) {
                        int i11 = (int) f4;
                        doDecodeMultiple(binaryBitmap2.crop(i6, i11, width, height - i11), map, list, i3, i4 + i11, i9 + 1);
                    }
                }
            } catch (ReaderException unused) {
            }
        }
    }

    private static Result translateResultPoints(Result result, int i3, int i4) {
        ResultPoint[] resultPoints = result.getResultPoints();
        if (resultPoints == null) {
            return result;
        }
        ResultPoint[] resultPointArr = new ResultPoint[resultPoints.length];
        for (int i5 = 0; i5 < resultPoints.length; i5++) {
            ResultPoint resultPoint = resultPoints[i5];
            if (resultPoint != null) {
                resultPointArr[i5] = new ResultPoint(resultPoint.getX() + ((float) i3), resultPoint.getY() + ((float) i4));
            }
        }
        Result result2 = new Result(result.getText(), result.getRawBytes(), result.getNumBits(), resultPointArr, result.getBarcodeFormat(), result.getTimestamp());
        result2.putAllMetadata(result.getResultMetadata());
        return result2;
    }

    public Result[] decodeMultiple(BinaryBitmap binaryBitmap) throws NotFoundException {
        return decodeMultiple(binaryBitmap, (Map<DecodeHintType, ?>) null);
    }

    public Result[] decodeMultiple(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) throws NotFoundException {
        ArrayList arrayList = new ArrayList();
        doDecodeMultiple(binaryBitmap, map, arrayList, 0, 0, 0);
        if (!arrayList.isEmpty()) {
            return (Result[]) arrayList.toArray(EMPTY_RESULT_ARRAY);
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
