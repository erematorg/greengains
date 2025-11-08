package com.google.android.material.carousel;

import android.support.v4.media.session.a;
import androidx.annotation.NonNull;
import androidx.core.math.MathUtils;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.carousel.KeylineState;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class KeylineStateList {
    private static final int NO_INDEX = -1;
    private final KeylineState defaultState;
    private final float endShiftRange;
    private final List<KeylineState> endStateSteps;
    private final float[] endStateStepsInterpolationPoints;
    private final float startShiftRange;
    private final List<KeylineState> startStateSteps;
    private final float[] startStateStepsInterpolationPoints;

    private KeylineStateList(@NonNull KeylineState keylineState, List<KeylineState> list, List<KeylineState> list2) {
        this.defaultState = keylineState;
        this.startStateSteps = Collections.unmodifiableList(list);
        this.endStateSteps = Collections.unmodifiableList(list2);
        float f2 = ((KeylineState) a.h(list, 1)).getFirstKeyline().loc - keylineState.getFirstKeyline().loc;
        this.startShiftRange = f2;
        float f3 = keylineState.getLastKeyline().loc - ((KeylineState) a.h(list2, 1)).getLastKeyline().loc;
        this.endShiftRange = f3;
        this.startStateStepsInterpolationPoints = getStateStepInterpolationPoints(f2, list, true);
        this.endStateStepsInterpolationPoints = getStateStepInterpolationPoints(f3, list2, false);
    }

    private KeylineState closestStateStepFromInterpolation(List<KeylineState> list, float f2, float[] fArr) {
        float[] stateStepsRange = getStateStepsRange(list, f2, fArr);
        return stateStepsRange[0] >= 0.5f ? list.get((int) stateStepsRange[2]) : list.get((int) stateStepsRange[1]);
    }

    private static int findFirstIndexAfterLastFocalKeylineWithMask(KeylineState keylineState, float f2) {
        for (int lastFocalKeylineIndex = keylineState.getLastFocalKeylineIndex(); lastFocalKeylineIndex < keylineState.getKeylines().size(); lastFocalKeylineIndex++) {
            if (f2 == keylineState.getKeylines().get(lastFocalKeylineIndex).mask) {
                return lastFocalKeylineIndex;
            }
        }
        return keylineState.getKeylines().size() - 1;
    }

    private static int findFirstNonAnchorKeylineIndex(KeylineState keylineState) {
        for (int i3 = 0; i3 < keylineState.getKeylines().size(); i3++) {
            if (!keylineState.getKeylines().get(i3).isAnchor) {
                return i3;
            }
        }
        return -1;
    }

    private static int findLastIndexBeforeFirstFocalKeylineWithMask(KeylineState keylineState, float f2) {
        for (int firstFocalKeylineIndex = keylineState.getFirstFocalKeylineIndex() - 1; firstFocalKeylineIndex >= 0; firstFocalKeylineIndex--) {
            if (f2 == keylineState.getKeylines().get(firstFocalKeylineIndex).mask) {
                return firstFocalKeylineIndex;
            }
        }
        return 0;
    }

    private static int findLastNonAnchorKeylineIndex(KeylineState keylineState) {
        for (int size = keylineState.getKeylines().size() - 1; size >= 0; size--) {
            if (!keylineState.getKeylines().get(size).isAnchor) {
                return size;
            }
        }
        return -1;
    }

    public static KeylineStateList from(Carousel carousel, KeylineState keylineState, float f2, float f3, float f4) {
        return new KeylineStateList(keylineState, getStateStepsStart(carousel, keylineState, f2, f3), getStateStepsEnd(carousel, keylineState, f2, f4));
    }

    private static float[] getStateStepInterpolationPoints(float f2, List<KeylineState> list, boolean z2) {
        int size = list.size();
        float[] fArr = new float[size];
        int i3 = 1;
        while (i3 < size) {
            int i4 = i3 - 1;
            KeylineState keylineState = list.get(i4);
            KeylineState keylineState2 = list.get(i3);
            fArr[i3] = i3 == size + -1 ? 1.0f : fArr[i4] + ((z2 ? keylineState2.getFirstKeyline().loc - keylineState.getFirstKeyline().loc : keylineState.getLastKeyline().loc - keylineState2.getLastKeyline().loc) / f2);
            i3++;
        }
        return fArr;
    }

    private static List<KeylineState> getStateStepsEnd(Carousel carousel, KeylineState keylineState, float f2, float f3) {
        KeylineState keylineState2 = keylineState;
        float f4 = f2;
        float f5 = f3;
        ArrayList arrayList = new ArrayList();
        arrayList.add(keylineState2);
        int findLastNonAnchorKeylineIndex = findLastNonAnchorKeylineIndex(keylineState);
        float containerWidth = (float) (carousel.isHorizontal() ? carousel.getContainerWidth() : carousel.getContainerHeight());
        if (isLastFocalItemVisibleAtRightOfContainer(carousel, keylineState) || findLastNonAnchorKeylineIndex == -1) {
            if (f5 > 0.0f) {
                arrayList.add(shiftKeylineStateForPadding(keylineState2, f5, containerWidth, false, f4));
            }
            return arrayList;
        }
        int lastFocalKeylineIndex = findLastNonAnchorKeylineIndex - keylineState.getLastFocalKeylineIndex();
        float f6 = keylineState.getFirstKeyline().locOffset - (keylineState.getFirstKeyline().maskedItemSize / 2.0f);
        if (lastFocalKeylineIndex > 0 || keylineState.getLastFocalKeyline().cutoff <= 0.0f) {
            float f7 = 0.0f;
            int i3 = 0;
            while (i3 < lastFocalKeylineIndex) {
                KeylineState keylineState3 = (KeylineState) androidx.compose.ui.autofill.a.h(arrayList, 1);
                int i4 = findLastNonAnchorKeylineIndex - i3;
                float f8 = f7 + keylineState.getKeylines().get(i4).cutoff;
                int i5 = i4 + 1;
                int i6 = i3;
                KeylineState moveKeylineAndCreateKeylineState = moveKeylineAndCreateKeylineState(keylineState3, findLastNonAnchorKeylineIndex, i5 < keylineState.getKeylines().size() ? findLastIndexBeforeFirstFocalKeylineWithMask(keylineState3, keylineState.getKeylines().get(i5).mask) + 1 : 0, f6 - f8, keylineState.getFirstFocalKeylineIndex() + i3 + 1, keylineState.getLastFocalKeylineIndex() + i3 + 1, containerWidth);
                if (i6 == lastFocalKeylineIndex - 1 && f5 > 0.0f) {
                    moveKeylineAndCreateKeylineState = shiftKeylineStateForPadding(moveKeylineAndCreateKeylineState, f5, containerWidth, false, f4);
                }
                arrayList.add(moveKeylineAndCreateKeylineState);
                i3 = i6 + 1;
                f7 = f8;
            }
            return arrayList;
        }
        arrayList.add(shiftKeylinesAndCreateKeylineState(keylineState2, f6 - keylineState.getLastFocalKeyline().cutoff, containerWidth));
        return arrayList;
    }

    private static float[] getStateStepsRange(List<KeylineState> list, float f2, float[] fArr) {
        int size = list.size();
        float f3 = fArr[0];
        int i3 = 1;
        while (i3 < size) {
            float f4 = fArr[i3];
            if (f2 <= f4) {
                return new float[]{AnimationUtils.lerp(0.0f, 1.0f, f3, f4, f2), (float) (i3 - 1), (float) i3};
            }
            i3++;
            f3 = f4;
        }
        return new float[]{0.0f, 0.0f, 0.0f};
    }

    private static List<KeylineState> getStateStepsStart(Carousel carousel, KeylineState keylineState, float f2, float f3) {
        KeylineState keylineState2 = keylineState;
        float f4 = f2;
        float f5 = f3;
        ArrayList arrayList = new ArrayList();
        arrayList.add(keylineState2);
        int findFirstNonAnchorKeylineIndex = findFirstNonAnchorKeylineIndex(keylineState);
        float containerWidth = (float) (carousel.isHorizontal() ? carousel.getContainerWidth() : carousel.getContainerHeight());
        int i3 = 1;
        if (isFirstFocalItemAtLeftOfContainer(keylineState) || findFirstNonAnchorKeylineIndex == -1) {
            if (f5 > 0.0f) {
                arrayList.add(shiftKeylineStateForPadding(keylineState2, f5, containerWidth, true, f4));
            }
            return arrayList;
        }
        int firstFocalKeylineIndex = keylineState.getFirstFocalKeylineIndex() - findFirstNonAnchorKeylineIndex;
        float f6 = keylineState.getFirstKeyline().locOffset - (keylineState.getFirstKeyline().maskedItemSize / 2.0f);
        if (firstFocalKeylineIndex > 0 || keylineState.getFirstFocalKeyline().cutoff <= 0.0f) {
            int i4 = 0;
            float f7 = 0.0f;
            while (i4 < firstFocalKeylineIndex) {
                KeylineState keylineState3 = (KeylineState) androidx.compose.ui.autofill.a.h(arrayList, i3);
                int i5 = findFirstNonAnchorKeylineIndex + i4;
                int size = keylineState.getKeylines().size() - i3;
                float f8 = f7 + keylineState.getKeylines().get(i5).cutoff;
                int i6 = i5 - i3;
                int findFirstIndexAfterLastFocalKeylineWithMask = i6 >= 0 ? findFirstIndexAfterLastFocalKeylineWithMask(keylineState3, keylineState.getKeylines().get(i6).mask) - i3 : size;
                int i7 = i4;
                KeylineState moveKeylineAndCreateKeylineState = moveKeylineAndCreateKeylineState(keylineState3, findFirstNonAnchorKeylineIndex, findFirstIndexAfterLastFocalKeylineWithMask, f6 + f8, (keylineState.getFirstFocalKeylineIndex() - i4) - 1, (keylineState.getLastFocalKeylineIndex() - i4) - 1, containerWidth);
                if (i7 == firstFocalKeylineIndex - 1 && f5 > 0.0f) {
                    moveKeylineAndCreateKeylineState = shiftKeylineStateForPadding(moveKeylineAndCreateKeylineState, f5, containerWidth, true, f4);
                }
                arrayList.add(moveKeylineAndCreateKeylineState);
                i4 = i7 + 1;
                f7 = f8;
                i3 = 1;
            }
            return arrayList;
        }
        arrayList.add(shiftKeylinesAndCreateKeylineState(keylineState2, f6 + keylineState.getFirstFocalKeyline().cutoff, containerWidth));
        return arrayList;
    }

    private static boolean isFirstFocalItemAtLeftOfContainer(KeylineState keylineState) {
        return keylineState.getFirstFocalKeyline().locOffset - (keylineState.getFirstFocalKeyline().maskedItemSize / 2.0f) >= 0.0f && keylineState.getFirstFocalKeyline() == keylineState.getFirstNonAnchorKeyline();
    }

    private static boolean isLastFocalItemVisibleAtRightOfContainer(Carousel carousel, KeylineState keylineState) {
        int containerHeight = carousel.getContainerHeight();
        if (carousel.isHorizontal()) {
            containerHeight = carousel.getContainerWidth();
        }
        return (keylineState.getLastFocalKeyline().maskedItemSize / 2.0f) + keylineState.getLastFocalKeyline().locOffset <= ((float) containerHeight) && keylineState.getLastFocalKeyline() == keylineState.getLastNonAnchorKeyline();
    }

    private static KeylineState lerp(List<KeylineState> list, float f2, float[] fArr) {
        float[] stateStepsRange = getStateStepsRange(list, f2, fArr);
        return KeylineState.lerp(list.get((int) stateStepsRange[1]), list.get((int) stateStepsRange[2]), stateStepsRange[0]);
    }

    private static KeylineState moveKeylineAndCreateKeylineState(KeylineState keylineState, int i3, int i4, float f2, int i5, int i6, float f3) {
        ArrayList arrayList = new ArrayList(keylineState.getKeylines());
        arrayList.add(i4, (KeylineState.Keyline) arrayList.remove(i3));
        KeylineState.Builder builder = new KeylineState.Builder(keylineState.getItemSize(), f3);
        int i7 = 0;
        while (i7 < arrayList.size()) {
            KeylineState.Keyline keyline = (KeylineState.Keyline) arrayList.get(i7);
            float f4 = keyline.maskedItemSize;
            builder.addKeyline((f4 / 2.0f) + f2, keyline.mask, f4, i7 >= i5 && i7 <= i6, keyline.isAnchor, keyline.cutoff);
            f2 += keyline.maskedItemSize;
            i7++;
        }
        return builder.build();
    }

    private static KeylineState shiftKeylineStateForPadding(KeylineState keylineState, float f2, float f3, boolean z2, float f4) {
        ArrayList arrayList = new ArrayList(keylineState.getKeylines());
        KeylineState.Builder builder = new KeylineState.Builder(keylineState.getItemSize(), f3);
        float numberOfNonAnchorKeylines = f2 / ((float) keylineState.getNumberOfNonAnchorKeylines());
        float f5 = z2 ? f2 : 0.0f;
        int i3 = 0;
        while (i3 < arrayList.size()) {
            KeylineState.Keyline keyline = (KeylineState.Keyline) arrayList.get(i3);
            if (keyline.isAnchor) {
                builder.addKeyline(keyline.locOffset, keyline.mask, keyline.maskedItemSize, false, true, keyline.cutoff);
            } else {
                boolean z3 = i3 >= keylineState.getFirstFocalKeylineIndex() && i3 <= keylineState.getLastFocalKeylineIndex();
                float f6 = keyline.maskedItemSize - numberOfNonAnchorKeylines;
                float childMaskPercentage = CarouselStrategy.getChildMaskPercentage(f6, keylineState.getItemSize(), f4);
                float f7 = (f6 / 2.0f) + f5;
                float f8 = f7 - keyline.locOffset;
                builder.addKeyline(f7, childMaskPercentage, f6, z3, false, keyline.cutoff, z2 ? f8 : 0.0f, z2 ? 0.0f : f8);
                f5 += f6;
            }
            i3++;
        }
        return builder.build();
    }

    private static KeylineState shiftKeylinesAndCreateKeylineState(KeylineState keylineState, float f2, float f3) {
        return moveKeylineAndCreateKeylineState(keylineState, 0, 0, f2, keylineState.getFirstFocalKeylineIndex(), keylineState.getLastFocalKeylineIndex(), f3);
    }

    public KeylineState getDefaultState() {
        return this.defaultState;
    }

    public KeylineState getEndState() {
        return (KeylineState) a.h(this.endStateSteps, 1);
    }

    public Map<Integer, KeylineState> getKeylineStateForPositionMap(int i3, int i4, int i5, boolean z2) {
        float itemSize = this.defaultState.getItemSize();
        HashMap hashMap = new HashMap();
        int i6 = 0;
        int i7 = 0;
        while (true) {
            int i8 = -1;
            if (i6 >= i3) {
                break;
            }
            int i9 = z2 ? (i3 - i6) - 1 : i6;
            float f2 = ((float) i9) * itemSize;
            if (!z2) {
                i8 = 1;
            }
            if (f2 * ((float) i8) > ((float) i5) - this.endShiftRange || i6 >= i3 - this.endStateSteps.size()) {
                Integer valueOf = Integer.valueOf(i9);
                List<KeylineState> list = this.endStateSteps;
                hashMap.put(valueOf, list.get(MathUtils.clamp(i7, 0, list.size() - 1)));
                i7++;
            }
            i6++;
        }
        int i10 = 0;
        for (int i11 = i3 - 1; i11 >= 0; i11--) {
            int i12 = z2 ? (i3 - i11) - 1 : i11;
            if (((float) i12) * itemSize * ((float) (z2 ? -1 : 1)) < ((float) i4) + this.startShiftRange || i11 < this.startStateSteps.size()) {
                Integer valueOf2 = Integer.valueOf(i12);
                List<KeylineState> list2 = this.startStateSteps;
                hashMap.put(valueOf2, list2.get(MathUtils.clamp(i10, 0, list2.size() - 1)));
                i10++;
            }
        }
        return hashMap;
    }

    public KeylineState getShiftedState(float f2, float f3, float f4) {
        return getShiftedState(f2, f3, f4, false);
    }

    public KeylineState getStartState() {
        return (KeylineState) a.h(this.startStateSteps, 1);
    }

    public KeylineState getShiftedState(float f2, float f3, float f4, boolean z2) {
        float[] fArr;
        List<KeylineState> list;
        float f5;
        float f6 = this.startShiftRange + f3;
        float f7 = f4 - this.endShiftRange;
        float f8 = getStartState().getFirstFocalKeyline().leftOrTopPaddingShift;
        float f9 = getEndState().getLastFocalKeyline().rightOrBottomPaddingShift;
        if (this.startShiftRange == f8) {
            f6 += f8;
        }
        if (this.endShiftRange == f9) {
            f7 -= f9;
        }
        if (f2 < f6) {
            f5 = AnimationUtils.lerp(1.0f, 0.0f, f3, f6, f2);
            list = this.startStateSteps;
            fArr = this.startStateStepsInterpolationPoints;
        } else if (f2 <= f7) {
            return this.defaultState;
        } else {
            f5 = AnimationUtils.lerp(0.0f, 1.0f, f7, f4, f2);
            list = this.endStateSteps;
            fArr = this.endStateStepsInterpolationPoints;
        }
        if (z2) {
            return closestStateStepFromInterpolation(list, f5, fArr);
        }
        return lerp(list, f5, fArr);
    }
}
