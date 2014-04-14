package Model;

import java.io.File;

public class Bitmap {
    private final File file;
    private final String name;

    public Bitmap(String name, File file) {
        this.name=name;
        this.file=file;
    }
}