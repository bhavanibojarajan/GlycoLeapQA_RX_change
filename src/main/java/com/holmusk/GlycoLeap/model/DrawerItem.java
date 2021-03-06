package com.holmusk.GlycoLeap.model;

import org.jetbrains.annotations.NotNull;
import org.swiften.javautilities.localizer.LocalizerType;
import org.swiften.javautilities.protocol.ClassNameProviderType;
import org.swiften.xtestkit.android.AndroidEngine;
import org.swiften.xtestkit.android.AndroidView;
import org.swiften.xtestkit.base.Engine;
import org.swiften.xtestkit.ios.IOSEngine;
import org.swiften.xtestkit.ios.IOSView;
import org.swiften.xtestkitcomponents.common.ErrorProviderType;
import org.swiften.xtestkitcomponents.platform.PlatformType;
import org.swiften.xtestkitcomponents.xpath.Attributes;
import org.swiften.xtestkitcomponents.xpath.CompoundAttribute;
import org.swiften.xtestkitcomponents.xpath.XPath;

public enum DrawerItem implements ErrorProviderType {
    SETTINGS,
    I_HAVE_A_CODE,
    SUBSCRIPTION,
    DIETITIAN_PROFILES,
    ABOUT_GLYCO,
    FEEDBACK,
    SIGN_OUT;

    /**
     * Get {@link String} value as displayed by the
     * {@link org.openqa.selenium.WebElement} corresponding to the current
     * {@link DrawerItem}.
     * @return {@link String} value.
     */
    @NotNull
    public String title() {
        switch (this) {
            case SETTINGS:
                return "drawer_title_settings";

            case I_HAVE_A_CODE:
                return "drawer_title_iHaveACode";

            case SUBSCRIPTION:
                return "drawer_title_subscription";

            case DIETITIAN_PROFILES:
                return "drawer_title_dietitianProfile";

            case ABOUT_GLYCO:
                return "drawer_title_about";

            case FEEDBACK:
                return "drawer_title_feedback";

            case SIGN_OUT:
                return "drawer_title_signOut";

            default:
                throw new RuntimeException(NOT_AVAILABLE);
        }
    }
    /**
     * Get the {@link XPath} query that can be used to locate the
     * {@link org.openqa.selenium.WebElement} corresponding to the current
     * {@link DrawerItem}.
     * @param engine {@link PlatformType} instance.
     * @return {@link XPath} instance.
     * @see Attributes#containsText(String)
     * @see #title()
     */
    @NotNull
    public XPath drawerItemXP(@NotNull Engine<?> engine) {
        LocalizerType localizer = engine.localizer();
        ClassNameProviderType param;

        if (engine instanceof AndroidEngine) {
            param = AndroidView.Type.CHECKED_TEXT_VIEW;
        } else if (engine instanceof IOSEngine) {
            param = IOSView.Type.UI_STATIC_TEXT;
        } else {
            throw new RuntimeException(NOT_AVAILABLE);
        }

        PlatformType platform = engine.platform();
        Attributes attrs = Attributes.of(platform);
        String localized = localizer.localize(title());

        CompoundAttribute cAttr = CompoundAttribute.builder()
                .addAttribute(attrs.ofClass(param))
                .addAttribute(attrs.containsText(localized))
                .build();

        return XPath.builder().addAttribute(cAttr).build();
    }


}
