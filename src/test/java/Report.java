import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.NetworkMode;

import java.io.File;

/**
 * Created by sunny on 17/04/18.
 */
public class Report {

    private String directory ="";
    ExtentTest test;
    ExtentReports extentReports;

    /*
        This method will create a Directory
        Need to provide a Repository direcoty so where it will create a filder
         */
    public void createDirectory(String reportDirectory){

//        /Users/sunny/Documents/Selenium_Demo/SeleniumDemo/Report
        directory = reportDirectory;
//        directory = reportDirectory;
        File fileDirectory = new File(reportDirectory);

        if (!fileDirectory.exists()){
            System.out.println("Creating directory :- "+fileDirectory.getName());
            boolean result = false;

            try {
                fileDirectory.mkdir();
                result = true;
                System.out.println("Directory Created (T/F)? :- "+result);
            }
            catch (Exception e){
                e.printStackTrace();
            }

            if (result){
                System.out.println(fileDirectory+" directory created");
            }

        }
    }


    /*
    This method will initialize report
     */
    public void startReport(String reportDirectory,String testClassName){
        createDirectory(reportDirectory);
        directory = reportDirectory;
        extentReports = new ExtentReports(directory, NetworkMode.OFFLINE);
        test= extentReports.startTest("TrelloLoginClass","Basic Functionality of Trello");


    }

    /*
    This method will enter logs
     */
    public void enterLog(String log){
        test.log(LogStatus.PASS,log);

    }

    public void endTestScenario(){
        extentReports.endTest(test);

    }

    public void flushToReport(){
        extentReports.flush();
    }






}
