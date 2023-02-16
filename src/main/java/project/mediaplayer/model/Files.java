package project.mediaplayer.model;

import java.io.File;
import java.util.ArrayList;

public class Files {
  ArrayList<File> files = new ArrayList<>();

//    public Files(ArrayList<File> files){
//        this.files = files;
//    }

  public ArrayList<File> getFiles() {
    return this.files;
  }

  public void addFile(File file) {
    this.files.add(file);
  }

  public void removeFile(File file) {
    this.files.remove(file);
  }


  public static String splitFileName(String path) {
    String result = "";
    int a = path.lastIndexOf("\\");
    result = path.substring(a + 1);
    return result;
  }
}