package services.branchImpl;

import Domain.Branch;
import services.BranchService;

/**
 * Created by Riaan on 4/15/2016.
 */
public class HeadOffice implements BranchService {
    @Override
    public Branch getBranch() {
        Branch branch = new Branch("Head Office");
        branch.setAddress(7,"Main rd","JHB","South Africa",8650);
        return branch;
    }
}
