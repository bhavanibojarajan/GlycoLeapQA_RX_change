package com.holmusk.GlycoLeap.Test.welcome;

import io.reactivex.Flowable;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;
import org.swiften.javautilities.object.HPObjects;
import org.swiften.xtestkit.base.Engine;
import com.holmusk.GlycoLeap.Test.base.BaseValidationType;

public interface WelcomeValidationType extends BaseValidationType {

    /**
     * Get the register button on the welcome screen.
     * @param engine {@link Engine} instance.
     * @return {@link Flowable} instance.
     * @see Engine#rxe_containsText(String...)
     */
    @NotNull
    default Flowable<WebElement> rxe_welcomeRegister(@NotNull Engine<?> engine) {
        return engine
                .rxe_containsText("welcome_title_register")
                .firstElement()
                .toFlowable();
    }

    /**
     * Get the sign in button on the welcome screen.
     * @param engine {@link Engine} instance.
     * @return {@link Flowable} instance.
     * @see Engine#rxe_containsText(String...)
     */
    @NotNull
    default Flowable<WebElement> rxe_welcomeLogin(@NotNull Engine<?> engine) {
        return engine
                .rxe_containsText("welcome_title_signIn")
                .firstElement()
                .toFlowable();
    }

    /**
     * Get the sign in button on the welcome screen.
     * @param engine {@link Engine} instance.
     * @return {@link Flowable} instance.
     * @see Engine#rxe_containsText(String...)
     */
    @NotNull
    default Flowable<WebElement> rxe_okwelcome(@NotNull Engine<?> engine) {
        return engine
                .rxe_containsText("welcome_title_ok")
                .firstElement()
                .toFlowable();
    }




    /**
     * Validate that all views are present in splash screen.
     * @param engine {@link Engine} instance.
     * @return {@link Flowable} instance.
     * @see #rxe_welcomeLogin(Engine)
     * @see #rxe_welcomeRegister(Engine)
     */
    @SuppressWarnings("unchecked")
    default Flowable<?> rxv_welcomeScreen(@NotNull Engine<?> engine) {
        return Flowable
                .concat(rxe_welcomeLogin(engine), rxe_welcomeRegister(engine))
                .all(HPObjects::nonNull)
                .toFlowable();
    }
}
