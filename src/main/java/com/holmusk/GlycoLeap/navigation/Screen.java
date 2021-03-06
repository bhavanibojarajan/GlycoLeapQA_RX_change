package com.holmusk.GlycoLeap.navigation;

import com.holmusk.GlycoLeap.model.CardType;
import com.holmusk.GlycoLeap.navigation.type.ScreenInitType;
import io.reactivex.Flowable;
import org.jetbrains.annotations.NotNull;
import org.swiften.xtestkit.base.Engine;
import org.swiften.xtestkit.navigation.NavigationSupplier;




public enum Screen implements ScreenInitType{

    SPLASH,
    OK,
    WELCOME,
    LOGIN,
    FORGOT_PASSWORD,
    REGISTER,
     PERSONAL_INFO,
    USE_APP_NOW,
    DASHBOARD_TUTORIAL,
    DASHBOARD,
    SEARCH,
    ADD_CARD,
    PHOTO_PICKER,
    MEAL_ENTRY,
    MEAL_PAGE,
    CHAT,
    WEIGHT_VALUE,
    WEIGHT_ENTRY,
    WEIGHT_PAGE,
    GLUCOSE_VALUE,
    GLUCOSE_ENTRY,
    GLUCOSE_PAGE,
    HBA1C_VALUE,
    HBA1C_ENTRY,
    HBAIC_PAGE,
    ACTIVITY_VALUE,
    ACTIVITY_ENTRY,
    SETTINGS;


    /**
     * Get the {@link Flowable} to perform work once the current {@link Screen}
     * has been fully initialized.
     * @param ENGINE {@link Engine} instance.
     * @return {@link Flowable} instance.
     * @see #rxn_dashboardTutorialInitialized(Engine)
     * @see #rxn_mealPageInitialized(Engine)
     * @see #//rxn_photoPickerInitialized(Engine)
     * @see #rxn_useAppInitialized(Engine)
     * @see #rxn_weightEntryInitialized(Engine)
     */
    @NotNull
    public NavigationSupplier rxa_onInitialized(@NotNull final Engine<?> ENGINE) {
        final Screen THIS = this;

        switch (this) {
            case DASHBOARD_TUTORIAL:
                return a -> THIS.rxn_dashboardTutorialInitialized(ENGINE);

           /* case MEAL_PAGE:
                return a -> THIS.rxn_mealPageInitialized(ENGINE);

            case PHOTO_PICKER:
                return a -> THIS.rxn_photoPickerInitialized(ENGINE);*/

            case WEIGHT_ENTRY:
                return a -> THIS.rxn_weightEntryInitialized(ENGINE);


            /*case USE_APP_NOW:
                return a -> THIS.rxn_useAppInitialized(ENGINE);*/

            default:
                return a -> Flowable.just(true);
        }
    }
}






