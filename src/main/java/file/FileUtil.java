package file;

import com.google.gson.Gson;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class FileUtil {
  private String filePath;
  private SimpleDateFormat sdf;

  public FileUtil() {
    sdf = new SimpleDateFormat("yyyy-MM-dd");
    filePath = "C:" + File.separator + "files" + File.separator + sdf.format(System.currentTimeMillis());
  }


  public List<AttachFile> fileSave(Collection<Part> parts) {
    File f = new File(filePath);
    List<AttachFile> attachFileList = new ArrayList<>();
    if (!f.exists()) {
      f.mkdirs();
    }
    for (Part part : parts) {
      String originalName = part.getSubmittedFileName();
      String saveFileName = UUID.randomUUID().toString().substring(0, 8) + "_" + originalName;
      if (part.getSubmittedFileName() != null) {
        try {
          part.write(f.getAbsolutePath() + File.separator + saveFileName);
          attachFileList.add(new AttachFile(originalName, saveFileName, f.getAbsolutePath()));
        } catch (IOException ie) {
          ie.printStackTrace();
        }
      }
    }
    return attachFileList;
  }

}
