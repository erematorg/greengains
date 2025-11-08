package com.appsamurai.storyly;

import androidx.annotation.Keep;
import com.google.android.gms.common.internal.ImagesContract;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\u000e\n\u0002\b\r\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0007\u0010\bR\u0019\u0010\u0003\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e¨\u0006\u000f"}, d2 = {"Lcom/appsamurai/storyly/StorylyDataSource;", "", "", "value", "Ljava/lang/String;", "getValue", "()Ljava/lang/String;", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "API", "MomentsAPI", "Local", "UserData", "ProductData", "ConditionData", "storyly_release"}, k = 1, mv = {1, 5, 1})
@Keep
public enum StorylyDataSource {
    API("api"),
    MomentsAPI("moments_api"),
    Local(ImagesContract.LOCAL),
    UserData("user_data"),
    ProductData("product_data"),
    ConditionData("condition_data");
    
    @NotNull
    private final String value;

    private StorylyDataSource(String str) {
        this.value = str;
    }

    @NotNull
    public final String getValue() {
        return this.value;
    }
}
