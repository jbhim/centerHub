package com.amcjt.config;

import com.amcjt.service.CenterHubSetting;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.ui.TextBrowseFolderListener;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.util.ui.FormBuilder;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author jintao
 * application 级别的properties Component
 */
public class CenterHubSettingConfigurable implements Configurable {
    private final TextFieldWithBrowseButton textFieldWithBrowseButton;
    private final JPanel mainPanel;
    private final CenterHubSetting centerHubSetting = CenterHubSetting.getInstance();


    public CenterHubSettingConfigurable() {
        this.textFieldWithBrowseButton = new TextFieldWithBrowseButton(new JTextField(centerHubSetting.getFilePath()));
        textFieldWithBrowseButton.addBrowseFolderListener(new TextBrowseFolderListener(new FileChooserDescriptor(true, false, false, false, false, false)));
        FormBuilder formBuilder = FormBuilder
                .createFormBuilder()
                .addLabeledComponent("文件路径", textFieldWithBrowseButton, 1, false)
                // 将剩余空间用空JPanel填充
                .addComponentFillVertically(new JPanel(), 0);
        this.mainPanel = formBuilder.getPanel();
    }

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "CenterHub";
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        return mainPanel;
    }

    @Override
    public boolean isModified() {
        return !StringUtils.equals(textFieldWithBrowseButton.getText(), this.centerHubSetting.getFilePath());
    }

    @Override
    public void apply() {
        this.centerHubSetting.setFilePath(textFieldWithBrowseButton.getText());
    }

    @Override
    public void reset() {
        textFieldWithBrowseButton.setText(centerHubSetting.getFilePath());
    }
}
