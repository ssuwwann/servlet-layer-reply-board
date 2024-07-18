package file;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet("/file/*")
public class FileController extends HttpServlet {
}
