package com.cubecode.api.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public abstract class GSONManager {
    protected final transient File file;
    protected final transient Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public GSONManager(File file) {
        this.file = file;

        try {
            this.file.createNewFile();
        } catch (IOException ignored) {

        }
    }

    public File getFile() {
        return this.file;
    }

    public void writeJSON(Object clazz) {
        try (FileWriter writer = new FileWriter(this.file)) {
            this.gson.toJson(clazz, writer);
        } catch (IOException ignored) {}
    }

    public Object readJSON(Class<Object> clazz) {
        try (FileReader reader = new FileReader(this.file)) {
            return this.gson.fromJson(reader, clazz);
        } catch (IOException ignored) {
            return null;
        }
    }
}
