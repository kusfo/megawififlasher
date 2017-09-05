package com.jordimontornes.megawififlasher.views.viewholder;

/**
 * Created by jordimontornes on 21/04/2017.
 */

public class FileItemData {
    private String name;
    private String path;
    private boolean directory;

    public FileItemData(String name, String path, boolean directory) {
        this.name = name;
        this.path = path;
        this.directory = directory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isDirectory() {
        return directory;
    }

    public void setDirectory(boolean directory) {
        this.directory = directory;
    }

    public String getFullPath() {
        return path + "/" + name;
    }

    public boolean isSegaRom() {
        if(name.contains(".bin")) {
            return true;
        } else {
            return false;
        }
    }
}
