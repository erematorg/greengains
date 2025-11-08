package com.appsamurai.storyly;

import androidx.annotation.Keep;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;

@Keep
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\b\n\u0002\b\b\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0006\u0010\u0007R\u0016\u0010\u0005\u001a\u00020\u00028@@\u0000X\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/appsamurai/storyly/StorylyLayoutDirection;", "", "", "getLayoutDirection$storyly_release", "()I", "layoutDirection", "<init>", "(Ljava/lang/String;I)V", "LTR", "RTL", "storyly_release"}, k = 1, mv = {1, 5, 1})
public enum StorylyLayoutDirection {
    LTR,
    RTL;

    public /* synthetic */ class a {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] iArr = new int[StorylyLayoutDirection.values().length];
            iArr[StorylyLayoutDirection.LTR.ordinal()] = 1;
            iArr[StorylyLayoutDirection.RTL.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public final int getLayoutDirection$storyly_release() {
        int i3 = a.$EnumSwitchMapping$0[ordinal()];
        if (i3 == 1) {
            return 0;
        }
        if (i3 == 2) {
            return 1;
        }
        throw new NoWhenBranchMatchedException();
    }
}
