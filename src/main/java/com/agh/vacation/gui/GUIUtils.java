package com.agh.vacation.gui;

import java.awt.*;

/**
 * @author Filip Piwosz
 */
public class GUIUtils {
    private GUIUtils() {
    }

    public static int scaledWidth(Component component, float scale) {
        return (int) (component.getWidth() * scale);
    }

    public static int scaledHeight(Component component, float scale) {
        return (int) (component.getHeight() * scale);
    }
}
