package file;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FileRequestDTO {
  private String filePath;
  private String saveName;
}
