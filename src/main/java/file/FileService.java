package file;

import java.util.List;

public class FileService {
  private static FileService instance = new FileService();
  private FileDAO fileDAO;

  private FileService() {
    fileDAO = new FileDAO();
  }

  public static FileService getInstance() {
    return instance;
  }

  public void addFile(List<AttachFile> files) {
    fileDAO.insertFile(files);
  }
}
