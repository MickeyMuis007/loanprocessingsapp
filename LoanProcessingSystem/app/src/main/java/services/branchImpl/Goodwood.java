package services.branchImpl;

import Domain.Branch;
import services.BranchService;

/**
 * Created by Riaan on 4/15/2016.
 */
public class Goodwood implements BranchService {
    @Override
    public Branch getBranch() {
        Branch branch = new Branch("Goodwood");
        branch.setAddress(7,"Alice str","Goodwood","South Africa",7640);
        return branch;
    }
}
