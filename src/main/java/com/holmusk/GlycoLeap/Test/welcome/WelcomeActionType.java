package com.holmusk.GlycoLeap.Test.welcome;
import com.holmusk.GlycoLeap.Test.base.BaseActionType;
import com.holmusk.GlycoLeap.Test.welcome.WelcomeValidationType;
import io.reactivex.Flowable;
import org.jetbrains.annotations.NotNull;
import org.swiften.xtestkit.base.Engine;

public interface WelcomeActionType extends BaseActionType, WelcomeValidationType {



    /**
     * Navigate to {@link com.holmusk.GlycoLeap.navigation.Screen#LOGIN}.
     * @param engine {@link Engine} instance.
     * @return {@link Flowable} instance.
     * @see #rxe_welcomeLogin(Engine)
     */
    @NotNull
    default Flowable<?> rxa_loginFromWelcome(@NotNull Engine<?> engine) {

        return rxe_welcomeLogin(engine).compose(engine.clickFn());
    }


    /**
     * Navigate to {@link com.holmusk.GlycoLeap.navigation.Screen#LOGIN}.
     * @param engine {@link Engine} instance.
     * @return {@link Flowable} instance.
     * @see #rxe_welcomeLogin(Engine)
     */
    @NotNull
    default Flowable<?> rxa_okFromWelcome(@NotNull Engine<?> engine) {
        return rxe_okwelcome(engine).compose(engine.clickFn());
    }

    /**
     * Navigate to {@link com.holmusk.GlycoLeap.navigation.Screen#REGISTER}.
     * @param engine {@link Engine} instance.
     * @return {@link Flowable} instance.
     * @see #rxe_welcomeRegister(Engine)
     */
    @NotNull
    default Flowable<?> rxa_registerFromWelcome(@NotNull Engine<?> engine) {
        return rxe_welcomeRegister(engine).compose(engine.clickFn());
    }




}
