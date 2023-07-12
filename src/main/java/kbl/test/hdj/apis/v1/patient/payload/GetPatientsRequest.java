package kbl.test.hdj.apis.v1.patient.payload;

import kbl.test.hdj.enums.SearchType;
import kbl.test.hdj.paging.PageObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class GetPatientsRequest extends PageObject {
    private final SearchType searchType;
    private final String searchKeyword;

    public GetPatientsRequest(int pageNo, int pageSize, SearchType searchType, String searchKeyword) {
        super(pageNo, pageSize);
        this.searchType = searchType;
        this.searchKeyword = searchKeyword;
    }
}
