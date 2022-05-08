package com.example.fithub.main.storage;

/** Enum class containing Fithub save files for serialized objects and structures. */
public enum Savefile {
  EXPERIENCE_BAR_SAVEFILE("Demo.json");

  private String file;

  private Savefile(String file) {
    this.file = file;
  }

  @Override
  public String toString() {
    return "Savefile{" + "file='" + file + '\'' + '}';
  }

  public String getFile() {
    return file;
  }

  public void setFile(String file) {
    this.file = file;
  }
}
