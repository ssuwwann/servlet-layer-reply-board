package util.paging;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Paging {
  private int pageSize;
  private int currentPage;
  private int startNum;
  private int totalPages;
  private int startPage;
  private int endPage;
  private int totalElements;
  private boolean nextPage;
  private boolean previousPage;

  public Paging(int pageSize, int currentPage) {
    this.pageSize = pageSize;
    this.currentPage = currentPage;

  }

  public void setTotalElements(int totalElements) {
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
