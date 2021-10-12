package com.amcjt.view;

import com.amcjt.ui.CenterHubMainPanel;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * @author jintao
 */
public class CenterHubToolWindow implements ToolWindowFactory {
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content;
        try {
            content = contentFactory.createContent(new CenterHubMainPanel(toolWindow, project),
                    CenterHubMainPanel.ID_TOOL_WINDOW, false);
            toolWindow.getContentManager().addContent(content);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }
}
