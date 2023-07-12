package kbl.test.hdj.paging;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PageObject {
    public static final int INIT_PAGE = -1;
    private int pageNo = 1;
    private int pageSize = 10;
}
