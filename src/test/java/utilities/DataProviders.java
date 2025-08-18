package utilities;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "LoginData")
    public Object[][] getLoginData() throws Exception {
        String filePath = "/Users/AutomationTraining/OpencartAutomationFramework/testData/OpencartLoginData.xlsx";
        return ExcelUtil.readExcelData(filePath, "Sheet1"); // This returns 3 columns
    }
}
