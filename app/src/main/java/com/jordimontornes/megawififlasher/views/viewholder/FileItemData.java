package com.jordimontornes.megawififlasher.views.viewholder;

/**
 * Created by jordimontornes on 21/04/2017.
 */

public class FileItemData {
    private String name;
    private boolean directory;

    public FileItemData(String name, boolean directory) {
        this.name = name;
        this.directory = directory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDirectory() {
        return directory;
    }

    public void setDirectory(boolean directory) {
        this.directory = directory;
    }
}
