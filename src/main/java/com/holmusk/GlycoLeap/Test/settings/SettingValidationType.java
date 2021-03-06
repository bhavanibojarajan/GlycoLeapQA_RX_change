package com.holmusk.GlycoLeap.Test.settings;

import com.holmusk.GlycoLeap.Test.base.BaseValidationType;
import com.holmusk.HMUITestKit.model.UnitSystem;
import io.reactivex.Flowable;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;
import org.swiften.javautilities.object.HPObjects;
import org.swiften.xtestkit.android.AndroidEngine;
import org.swiften.xtestkit.base.Engine;
import org.swiften.xtestkit.base.model.InputHelperType;
import org.swiften.xtestkit.ios.IOSEngine;
import org.swiften.xtestkitcomponents.xpath.XPath;
import com.holmusk.GlycoLeap.model.Setting;

public interface SettingValidationType extends BaseValidationType  {


    @NotNull
    default Flowable<WebElement> rxe_setting(@NotNull Engine<?> engine,
                                             @NotNull Setting setting) {
        XPath xpath = setting.settingXP(engine);
        return engine.rxe_withXPath(xpath).firstElement().toFlowable();
    }

    /**
     * Validate {@link com.holmusk.GlycoLeap.navigation.Screen#SETTINGS}.
     * @param ENGINE {@link Engine} instance.
     * @return {@link Flowable} instance.
     * @see HPObjects#nonNull(Object)
     * @see Setting#values()
     * @see #rxe_backButton(Engine)
     * @see #rxe_setting(Engine, Setting)
     */
    @NotNull
    @SuppressWarnings("unchecked")
    default Flowable<?> rxv_settings(@NotNull final Engine<?> ENGINE) {
        final SettingValidationType THIS = this;

        return Flowable.mergeArray(
                Flowable.fromArray(Setting.values())
                        .flatMap(a -> THIS.rxe_setting(ENGINE, a)),

                rxe_backButton(ENGINE)
        ).all(HPObjects::nonNull).toFlowable();
    }

    /**
     * Get {@link Integer} index for {@link Setting#UNITS}.
     * @param engine {@link Engine} instance.
     * @param unit {@link UnitSystem} instance.
     * @return {@link Integer} value.
     * @see UnitSystem#IMPERIAL
     * @see UnitSystem#METRIC
     * @see #NOT_AVAILABLE
     */
    default int unitSystemSettingIndex(@NotNull Engine<?> engine,
                                       @NotNull UnitSystem unit) {
        switch (unit) {
            case IMPERIAL:
                if (engine instanceof AndroidEngine) {
                    return 0;
                } else {
                    return 1;
                }

            case METRIC:
                if (engine instanceof IOSEngine) {
                    return 0;
                } else {
                    return 1;
                }

            default:
                throw new RuntimeException(NOT_AVAILABLE);
        }
    }
}
