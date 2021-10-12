package com.amcjt.service;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author jintao
 */
@State(name = "CenterHubSetting", storages = @Storage("center-bub-setting.xml"))
public class CenterHubSetting implements PersistentStateComponent<CenterHubSetting> {
    public String filePath;

    public CenterHubSetting() {
        this.filePath = "";
    }

    public static CenterHubSetting getInstance() {
        return ServiceManager.getService(CenterHubSetting.class);
    }

    @Override
    public @Nullable CenterHubSetting getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull CenterHubSetting state) {
        this.filePath = state.filePath;
    }
}
