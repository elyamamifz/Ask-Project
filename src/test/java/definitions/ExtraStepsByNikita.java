package definitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import java.util.Random;
import java.util.Stack;
import static org.assertj.core.api.Assertions.fail;
import static support.TestContext.getDriver;

public class ExtraStepsByNikita {
    @Then("^I click on element with xpath \"([^\"]*)\" 2$")
    public void iClickOnElementWithXpath(String xpath) {
        for(int second = 0; ;second++){
            if(second >= 1000){
                fail("Timeout for element: " + xpath);
            }
            try{
                if(getDriver().findElement(By.xpath(xpath)).isDisplayed()){
                    getDriver().findElement(By.xpath(xpath)).click();
                    break;
                }
            }
            catch(Exception e){}
        }
    }

    @And("^I click on element \"([^\"]*)\" using JavaScript$")
    public void revertToBackOldNew1(String user) throws Exception {
        String func = "return document.evaluate(\"//form//div//div[contains(., '"+user+"')]\", document, null, XPathResult.ANY_TYPE, null).iterateNext().click()";
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        Object users =  executor.executeScript(func);
    }

    @Then("^I scroll to the element with xpath \"([^\"]*)\"$")
    public void iScrollToTheElementWithXpath(String xpath) {
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        for(int second = 0; ;second++){
            if(second >= 1000){
                fail("Timeout for element" + xpath);
            }
            try{
                executor.executeScript("arguments[0].scrollIntoView(true);", getDriver().findElement(By.xpath(xpath)));
                if(getDriver().findElement(By.xpath(xpath)).isDisplayed()){
                    break;
                }
            }
            catch(Exception e){}
        }
    }


//    @Then("^I Cut inputed data from field with xpath \"([^\"]*)\"$")
//    public void iCutInputedDataFromFieldWithXpath(String xpath) {
//        Stack<String> stack = new Stack<>();
//        getDriver().findElement(By.xpath(xpath)).sendKeys(Keys.CONTROL + "a");
//        getDriver().findElement(By.xpath(xpath)).sendKeys(Keys.CONTROL + "x");
//        if (stack != null) {
//            String value = stack.peek();
//            System.out.println(value);
//        }
//        String result = getDriver().findElement(By.xpath(xpath)).getAttribute("value");
//        if(result.equals("")){
//            System.out.println("Menu 'Cut' is enable");
//        }
//        else{
//            System.out.println("Menu 'Cut' is disable");
//        }
//    }

    @When("^I type \"([^\"]*)\" into element with xpath \"([^\"]*)\" 2$")
    public void iTypeIntoElementWithXpath(String text, String xpath) {
        for(int second = 0; ;second++){
            if(second >= 1000){
                fail("Timeout for element" + xpath);
            }
            try{
                if(getDriver().findElement(By.xpath(xpath)).isDisplayed()){
                    getDriver().findElement(By.xpath(xpath)).sendKeys(text);
                    break;
                }
            }
            catch(Exception e){}
        }
    }

    @And("^I input value with length (-?\\d+) on field with xpath \"([^\"]*)\" which include digits alphabet and special characters$")
    public void iInputValueWithLengthOnFieldWithXpath(int length, String xpath) {
        //use ASCII in DEC code column list for setting leftlimit and rightlimit
        //https://www.asciitable.com/
        int leftlimit = 33;
        int rightlimit = 126;
        //this limit will be generate random characters between ! and ~
        Random random = new Random();
        StringBuilder createdString = new StringBuilder(length);
        for(int i = 0; i < length; i++){
            int randomLimitedInt = leftlimit + (int)(random.nextFloat()*(rightlimit - leftlimit + 1));
            createdString.append((char)randomLimitedInt);
        }
        String buildedString = createdString.toString();
        System.out.println(buildedString);
        getDriver().findElement(By.xpath(xpath)).sendKeys(buildedString);
    }

    @Then("^I input value with length (\\d+) on field with xpath \"([^\"]*)\" which include digits$")
    public void iInputValueWithLengthOnFieldWithXpathWhichIncludeDigits(int length, String xpath) {
        int leftlimit = 48;
        int rightlimit = 57;
        Random random = new Random();
        StringBuilder createdString = new StringBuilder(length);
        for(int i = 0; i < length; i++){
            int randomLimitedInt = leftlimit + (int)(random.nextFloat()*(rightlimit - leftlimit + 1));
            createdString.append((char)randomLimitedInt);
        }
        String buildedString = createdString.toString();
        System.out.println(buildedString);
        getDriver().findElement(By.xpath(xpath)).sendKeys(buildedString);
    }

    @Then("^I input value with length (\\d+) on field with xpath \"([^\"]*)\" which include alphabet$")
    public void iInputValueWithLengthOnFieldWithXpathWhichIncludeAlphabet(int length, String xpath) {
        int leftlimit = 65;
        int rightlimit = 90;
        Random random = new Random();
        StringBuilder createdString = new StringBuilder(length);
        for(int i = 0; i < length; i++){
            int randomLimitedInt = leftlimit + (int)(random.nextFloat()*(rightlimit - leftlimit + 1));
            createdString.append((char)randomLimitedInt);
        }
        String buildedString = createdString.toString();
        System.out.println(buildedString);
        getDriver().findElement(By.xpath(xpath)).sendKeys(buildedString);
    }

    @Then("^I verify that required text \"([^\"]*)\" and text with xpath \"([^\"]*)\" is equal$")
    public void iValidateThatTextAndTextWithXpathIsEqual(String xpath, String text) {
        try{
            boolean equal = getDriver().findElement(By.xpath(xpath)).getText().equals(text);
            System.out.println(getDriver().findElement(By.xpath(xpath)).getText());
            String equal2 = getDriver().findElement(By.xpath(xpath)).getText();
            System.out.println(equal2);
            if (equal){
                System.out.println("Text with xpath " + xpath + " is equals to" + text);
            }
        }
        catch(Exception e){
        }
    }

    @Then("^I verify that text with xpath \"([^\"]*)\" is displayed")
    public void verifyError(String xpath){
        try{
            boolean error = getDriver().findElement(By.xpath(xpath)).isDisplayed();
            if (error){
                System.out.println("Error is displayed");
            }
        }
        catch(Exception e){
        }
    }

    @Then("^I verify that got value from the field with xpath \"([^\"]*)\" is equal to \"([^\"]*)\"$")
    public void iTryGetValueFromTheFieldWithXpath(String xpath, String requiredValue) {
        String getres = getDriver().findElement(By.xpath(xpath)).getAttribute("value");
        if(getres.equals(requiredValue)){
            System.out.println("Value from field is equal to required Value");
        }else{
            System.out.println("Value from field isn't equal to required Value");
        }
    }

    @Then("^element with xpath \"([^\"]*)\" should be displayed no delete \"([^\"]*)\" as \"([^\"]*)\" with \"([^\"]*)\" credentials$")
    public void elementWithXpathShouldBeDisplayedNoDelete(String xpath, String name, String userrule, String credentials) throws InterruptedException {
        boolean flag;
        try {
            flag = getDriver().findElement(By.xpath(xpath)).isDisplayed();
        }catch(Exception e){
            iDeleteUserWhichIsATeacher(name, userrule, credentials);
            Thread.sleep(1000);
            throw new PendingException();
        }
    }



    PredefinedStepDefs st = new PredefinedStepDefs();
    @Then("^I delete user \"([^\"]*)\" which is a \"([^\"]*)\" with \"([^\"]*)\" credentials other user$")
    public void iDeleteUserWhichIsATeacher(String lastname, String userrule, String credentials) {
        if(credentials.equals("")){
            st.iOpenUrl("http://local.school.portnov.com:4520/#/login");
            st.iTypeIntoElementWithXpath("nikita_teacher@amail.club","//input[@formcontrolname='email']");
            st.iTypeIntoElementWithXpath("0123456789","//input[@formcontrolname='password']");
            st.iClickOnElementWithXpath("//button[@type='submit']");
        }
        iClickOnElementWithXpath("//h5[contains(text(),'Management')]");
        if(userrule.equals("Student")){
            iClickOnElementWithXpath("(//div[@role='tab'])[1]");
            iScrollToTheElementWithXpath("//h4[contains(text(),'"+lastname+"')]");
            iClickOnElementWithXpath("//h4[contains(text(),'"+lastname+"')]");
            iClickOnElementWithXpath("//button[@color='accent']");
            iClickOnElementWithXpath("(//button[@role='menuitem'])[4]");
        }else if(userrule.equals("Teacher")){
            iClickOnElementWithXpath("(//div[@role='tab'])[2]");
            iScrollToTheElementWithXpath("//h4[contains(text(),'"+lastname+"')]");
            iClickOnElementWithXpath("//h4[contains(text(),'"+lastname+"')]");
            iClickOnElementWithXpath("//button[@color='accent']");
            iClickOnElementWithXpath("(//button[@role='menuitem'])[3]");
        }
        iClickOnElementWithXpath("(//button[@aria-label])[2]");
    }

    @And("^Revert to back \"([^\"]*)\" old \"([^\"]*)\" new1 \"([^\"]*)\"$")
    public void revertToBackOldNew(String arg0, String old, String new1) throws Exception {
        iClickOnElementWithXpath("//h5[contains(text(),'Setting')]");
        iClickOnElementWithXpath("//span[contains(text(),'Change Your Password')]");
        iTypeIntoElementWithXpath(old,"//input[@placeholder='Password']");
        iTypeIntoElementWithXpath(new1,"//input[@placeholder='New Password']");
        iTypeIntoElementWithXpath(new1,"//input[@placeholder='Confirm New Password']");
        iClickOnElementWithXpath("(//button[@aria-label])[2]");
        st.iWaitForSec(2);
    }
}