package com.amcjt.ui;

import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionToolbar;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.util.ui.JBUI;
import com.intellij.xdebugger.impl.ui.TextViewer;
import org.apache.commons.io.IOUtils;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author jintao
 */
public class CenterHubMainPanel extends JPanel {
    public static final String ID_TOOL_WINDOW = "CenterHub";
    private static final String MAIN_ACTION_GROUP = "CenterHubToolBar";
    private final Project project;
    private final ToolWindow toolWindow;

    public CenterHubMainPanel(final ToolWindow toolWindow, final Project project) throws IOException {
        super(new BorderLayout());
        // 设置Border 1px
        this.setBorder(JBUI.Borders.empty(1));

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

        File file = new File("/Users/jintao/Desktop/settings.xml");
        String readString = IOUtils.toString(new FileInputStream(file), StandardCharsets.UTF_8);
        // 创建内容主体
        TextViewer textViewer = new TextViewer(readString, this.project);
        this.add(textViewer, BorderLayout.CENTER);
    }
}
