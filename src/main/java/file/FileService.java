package file;

import java.io.File;
import java.net.URLDecoder;

public class FileService {
  private static FileService instance = new FileService();
  private FileDAO fileDAO;

  private FileService() {
    fileDAO = new FileDAO();
  }

  public static FileService getInstance() {
    return instance;
  }

  public void addFile(AttachFile file) {
    fileDAO.insertFile(file);
  }

  // AttachFile이랑 .. 뭐 꼬인듯 이름이
  public int removeFile(FileRequestDTO dto) {
    String decodePath = URLDecoder.decode(dto.getFilePath());
    String decodeName = URLDecoder.decode(dto.getSaveName());
    int result = fileDAO.deleteFile(new FileRequestDTO(decodePath, decodeName));

    if (result > 0) {
      File file = new File(decodePath, decodeName);
      if (file.exists()) {
        file.delete();
        return 1;
      }
    }
    return 0;
  }
}
