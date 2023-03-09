package model.Files;

import java.awt.desktop.FilesEvent;
import java.io.File;
import java.util.UUID;

/**
 * Building a file using the builder pattern.
 */
public class Files {
    private String file_id;
    private File file_name;
    private int file_size;
    private File file_data;
    private String file_notes;

    public String getFile_id() {
        return file_id;
    }

    public File getFile_name() {
        return file_name;
    }

    public int getFile_size() {
        return file_size;
    }

    public File getFile_data() {
        return file_data;
    }

    public String getFile_notes() {
        return file_notes;
    }

    private Files (Filesbuilder builder){
        this.file_id = UUID.randomUUID().toString();
        this.file_name = builder.file_name;
        this.file_size = builder.file_size;
        this.file_notes = builder.file_notes;
        this.file_data = builder.file_data;
    }

    public static class Filesbuilder{
        private String file_id;
        private File file_name;
        private int file_size;
        private File file_data;
        private String file_notes;

        public Filesbuilder(){
            this.file_id = UUID.randomUUID().toString();
        }

        public Filesbuilder setFile_notes(String file_notes) {
            this.file_notes = file_notes;
            return this;
        }

        public Filesbuilder setFile_data(File file_data) {
            this.file_data = file_data;
            return this;
        }

        public Filesbuilder setFile_size(int file_size) {
            this.file_size = file_size;
            return this;
        }

        public Filesbuilder setFile_name(File file_name) {
            this.file_name = file_name;
            return this;
        }

        public Files build(){
            return new Files(this);
        }
    }

}
