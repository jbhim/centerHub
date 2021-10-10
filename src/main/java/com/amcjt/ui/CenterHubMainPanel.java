package com.amcjt.ui;

import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionToolbar;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.util.ui.JBUI;

import javax.swing.*;
import java.awt.*;

public class CenterHubMainPanel extends JPanel {
    public static final String ID_TOOL_WINDOW = "CenterHub";
    private static final String MAIN_ACTION_GROUP = "CenterHubToolBar";
    private final Project project;
    private final ToolWindow toolWindow;

    public CenterHubMainPanel(final ToolWindow toolWindow, final Project project) {
        super(new BorderLayout());

        this.toolWindow = toolWindow;
        this.project = project;
        // 创建toolBar
        final ActionGroup mainActionGroup = (ActionGroup)
                ActionManager.getInstance().getAction(MAIN_ACTION_GROUP);
        final ActionToolbar mainToolbar = ActionManager.getInstance().createActionToolbar(
                ID_TOOL_WINDOW, mainActionGroup, true);
        final Box toolBarBox = Box.createVerticalBox();
        toolBarBox.add(mainToolbar.getComponent());
        // toolBarBox 放上面
        add(toolBarBox, BorderLayout.NORTH);
        this.setBorder(JBUI.Borders.empty(1));
        // 创建内容主体
        JTextPane comp = new JTextPane();
        comp.setText("test11111111");
        this.add(comp, BorderLayout.CENTER);
    }
}
