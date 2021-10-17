package com.amcjt.config;

import com.amcjt.service.CenterHubSetting;
import com.intellij.openapi.options.Configurable;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author jintao
 * application 级别的properties Component
 */
public class CenterHubSettingConfigurable implements Configurable {
    private final JTextField textField;
    private final JPanel mainPanel;
    private final JFileChooser jFileChooser;
    private final CenterHubSetting centerHubSetting = CenterHubSetting.getInstance();


    public CenterHubSettingConfigurable() {
        this.mainPanel = new JPanel();
        this.textField = new JTextField(centerHubSetting.getFilePath());
        this.jFileChooser = new JFileChooser();
        jFileChooser.addActionListener(e -> {
            String path = jFileChooser.getSelectedFile().getPath();
            this.textField.setText(path);
        });
        Box verticalBox = Box.createVerticalBox();
        verticalBox.add(textField);
        verticalBox.add(jFileChooser);
        this.mainPanel.add(verticalBox);
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
        return !StringUtils.equals(textField.getText(), this.centerHubSetting.getFilePath());
    }

    @Override
    public void apply() {
        this.centerHubSetting.setFilePath(textField.getText());
    }

    @Override
    public void reset() {
        textField.setText(centerHubSetting.getFilePath());
    }
}
