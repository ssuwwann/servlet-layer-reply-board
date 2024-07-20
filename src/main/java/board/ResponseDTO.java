package board;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ResponseDTO<E> {
  private List<E> list;
  private int pageSize, currentPage, startNum, totalPages, startPage, endPage, totalElements;
  private boolean nextPage, previousPage;

  @Builder(builderMethodName = "withAll")
  public ResponseDTO(List<E> list, int pageSize, int currentPage, int totalElements) {
    this.list = list;
    this.pageSize = pageSize;
    this.currentPage = currentPage;
    this.totalElements = totalElements;

    totalPages = (int) (Math.ceil((double) totalElements / (double) pageSize));

    startNum = (currentPage - 1) * pageSize;
    startNum = startNum < 0 ? 0 : startNum;

    endPage = ((currentPage - 1) / pageSize + 1) * pageSize;
    if (endPage > totalPages) endPage = totalPages;

    startPage = currentPage / pageSize * pageSize + 1;
    if (currentPage % pageSize == 0) {
      startPage -= pageSize;
    }

    if (startPage < 0) startPage = 1;
    nextPage = endPage < totalPages ? true : false;
    previousPage = currentPage > pageSize ? true : false;
  }
}
