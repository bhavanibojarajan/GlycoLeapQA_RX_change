package com.holmusk.GlycoLeap.Test.dashboard;


import com.holmusk.GlycoLeap.model.CardType;
import com.holmusk.GlycoLeap.model.UserMode;
import com.holmusk.GlycoLeap.navigation.Screen;
import com.holmusk.GlycoLeap.Test.base.UIBaseTestType;
import com.holmusk.GlycoLeap.Test.card.CardItemHelperType;
import io.reactivex.Flowable;
import io.reactivex.subscribers.TestSubscriber;
import org.jetbrains.annotations.NotNull;
import org.swiften.javautilities.object.HPObjects;
import org.swiften.javautilities.rx.CustomTestSubscriber;
import org.swiften.javautilities.test.HPTestNGs;
import org.swiften.xtestkit.base.Engine;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;

public interface UIDashboardTestType extends UIBaseTestType,
        DashboardActionType,
        CardItemHelperType {


    /**
            * Provide {@link CardType} that can be deleted.
            * @return {@link Iterator} instance.
     */
    @NotNull
    @DataProvider
    static Iterator<Object[]> deletableCardProvider() {
        return HPTestNGs.oneFromEach(
                new Object[] {
                        UserMode.GENERAL
//                UserMode.TEEN_A18
                },

                new Object[] {
//                CardType.MEAL
                        CardType.WEIGHT
                }
        ).iterator();
    }

    /**
     * Delete all cards for a particular {@link CardType}.
     * If we want to use this method as an utility method, we should run it
     * with {@link org.swiften.xtestkit.mobile.Platform#ANDROID} since it's
     * much faster.
     * @param mode {@link UserMode} instance.
     * @param card {@link CardType} instance.
     * @see #deletableCardProvider()
     * @see #engine()
     * @see #rxa_navigate(UserMode, Screen...)
     * @see #rxa_revealAndDeleteCardItems(Engine, CardType)
     */
    @SuppressWarnings("unchecked")
    @Test(
            dataProviderClass = UIDashboardTestType.class,
            dataProvider = "deletableCardProvider"
    )
    default void test_deleteCards_shouldWork(@NotNull UserMode mode,
                                             @NotNull CardType card) {
        // Setup
        Engine<?> engine = engine();
        TestSubscriber subscriber = CustomTestSubscriber.create();

        // When
        Flowable.concatArray(
                rxa_navigate(mode, Screen.SPLASH, Screen.DASHBOARD),
                rxa_revealAndDeleteCardItems(engine, card)
        ).all(HPObjects::nonNull).toFlowable().subscribe(subscriber);

        subscriber.awaitTerminalEvent();

        // Then
        assertCorrectness(subscriber);
    }
}
