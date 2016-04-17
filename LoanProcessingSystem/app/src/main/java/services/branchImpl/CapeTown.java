package services.branchImpl;

import Domain.Branch;
import services.BranchService;

/**
 * Created by Riaan on 4/15/2016.
 */
public class CapeTown implements BranchService{
    @Override
    public Branch getBranch() {
        Branch branch = new Branch("Cape Town");
        branch.setAddress(7,"Main rd","Cape Town","South Africa",7650);
        return branch;
    }
}
