package services.branchImpl;

import Domain.Branch;
import services.BranchService;

/**
 * Created by Riaan on 4/15/2016.
 */
public class Goodwood implements BranchService {
    @Override
    public Branch getBranch() {
        Branch branch = new Branch
                .Builder()
                .name("Goodwood")
                .address(7,"Alice str","Goodwood",7640)
                .branchNo(3)
                .build();
        return branch;
    }
}
