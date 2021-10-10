package com.amcjt.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import org.jetbrains.annotations.NotNull;

/**
 * @author jintao
 */
public class PrePageAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        JBPopupFactory.getInstance().createMessage("PrePageAction").showInFocusCenter();
    }
}
