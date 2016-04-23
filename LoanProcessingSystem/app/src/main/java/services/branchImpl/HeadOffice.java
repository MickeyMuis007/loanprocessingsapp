package services.branchImpl;

import Domain.Branch;
import services.BranchService;

/**
 * Created by Riaan on 4/15/2016.
 */
public class HeadOffice implements BranchService {
    @Override
    public Branch getBranch() {
        Branch branch = new Branch
                .Builder()
                .name("Head Office")
                .address(7,"Main rd","JHB",8650)
                .branchNo(1)
                .build();
        return branch;
    }
}
