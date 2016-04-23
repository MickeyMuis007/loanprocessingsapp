package services.branchImpl;

import Domain.Branch;
import services.BranchService;

/**
 * Created by Riaan on 4/15/2016.
 */
public class CapeTown implements BranchService{
    @Override
    public Branch getBranch() {
        Branch branch = new Branch
                .Builder()
                .name("Cape Town")
                .address(7,"Main rd","Cape Town",7650)
                .branchNo(2)
                .build();
        return branch;
    }
}
