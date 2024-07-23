package file;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.*;
import java.util.Collection;
import java.util.List;

@WebServlet("/file/*")
@MultipartConfig(
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5
)
public class FileController extends HttpServlet {
  private FileUtil fileUtil;
  private FileService fileService;

  public FileController() {
    this.fileUtil = new FileUtil();
    fileService = FileService.getInstance();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    responseFile(req, res);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String uri = req.getRequestURI();
    System.out.println("uri: " + uri);
    if (uri.equals("/file")) saveFile(req, res);

    if (uri.startsWith("/file/")) removeFile(req, res);

  }

  private void responseFile(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String filepath = req.getParameter("filepath");
    String filename = req.getParameter("filename");
    File f = new File(filepath + File.separator + filename);
    if (f.exists()) {
      String mimeType = getServletContext().getMimeType(f.getName());
      if (mimeType == null) {
        mimeType = "application/octet-stream";
      }
      res.setContentType(mimeType);
      res.setContentLength((int) f.length()); // 파일 크기 설정?

      // 파일을 바이트 스트림으로 전송
      try (FileInputStream fis = new FileInputStream(f); OutputStream os = res.getOutputStream()) {
        byte[] buffer = new byte[4096];
        int bytesRead = 0;
        while ((bytesRead = fis.read(buffer)) != -1) {
          os.write(buffer, 0, bytesRead);
        }
      }
    }
  }

  private void saveFile(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    PrintWriter out = res.getWriter();
    Gson gson = new Gson();
    Collection<Part> part = req.getParts();
    List<AttachFile> attachFiles = fileUtil.fileSave(part);
    out.print(gson.toJson(attachFiles));
  }

  private void removeFile(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    Gson gson = new Gson();
    PrintWriter out = res.getWriter();
    BufferedReader reader = req.getReader();
    StringBuilder sb = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
      sb.append(line);
    }
    String jsonData = sb.toString();
    System.out.println(jsonData);
    FileRequestDTO dto = gson.fromJson(jsonData, FileRequestDTO.class);

    int result = fileService.removeFile(dto);
    out.print("{\"result\":" + result + "}");

  }


}
