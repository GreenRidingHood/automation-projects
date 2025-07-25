package dbTests;

import dbAdapter.Adapter;
import models.FailureBank;
import org.testng.annotations.Test;

import java.util.List;

public class DbTests {
    @Test
    public void getFailureBanks(){
        List<FailureBank> failureBankList = Adapter.getAllFailureBanks();

        for (FailureBank failureBank : failureBankList){
            System.out.println(failureBank);
        }
    }
}
