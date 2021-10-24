package com.amcjt.ui;

import com.amcjt.core.Chapter;
import com.amcjt.core.TxtScanner;
import com.amcjt.service.CenterHubSetting;
import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionToolbar;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.EditorTextField;
import com.intellij.util.ui.JBUI;
import com.intellij.xdebugger.impl.ui.TextViewer;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * @author jintao
 */
public class CenterHubMainPanel extends JPanel {
    public static final String ID_TOOL_WINDOW = "CenterHub";
    private static final String MAIN_ACTION_GROUP = "CenterHubToolBar";
    private final Project project;
    private final ToolWindow toolWindow;

    public CenterHubMainPanel(final ToolWindow toolWindow, final Project project) {
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

        String content = "";
        if (StringUtils.isNotBlank(CenterHubSetting.getInstance().getFilePath())) {
            TxtScanner txtScanner = new TxtScanner(CenterHubSetting.getInstance().getFilePath());
            txtScanner.analysis();
            Chapter chapter = txtScanner.getChapter(0);
            content = chapter.getContent();
        }

        // 创建内容主体
        EditorTextField editorTextField = new EditorTextField(content);
        this.add(editorTextField, BorderLayout.CENTER);
    }
}
