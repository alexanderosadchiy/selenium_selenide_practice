package org.osadchiy.tests.upload_download;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.osadchiy.BaseTestsClass;
import org.osadchiy.driver.WebDriverHolder;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class FileUploadDownloadTests extends BaseTestsClass {
    File fileToUpload;
    WebDriver driver = WebDriverHolder.getInstance().getDriver();

    @BeforeClass
    public void beforeClass(){
        fileToUpload = generateTestFile(new File("files/lorem.txt"));
    }

    @AfterClass
    public void afterClass(){
        if(fileToUpload.exists()){
            fileToUpload.delete();
        }
    }

    @Test
    public void uploadFileTest() {
        goToUrl("http://the-internet.herokuapp.com/upload");
        uploadFile();
        checkFileUpload();
        goToUrl("http://the-internet.herokuapp.com/download");
        checkFileDownloadLink();
    }

    @Test
    public void downloadFileTest() throws InterruptedException, IOException {
        goToUrl("http://the-internet.herokuapp.com/upload");
        uploadFile();
        goToUrl("http://the-internet.herokuapp.com/download");
        driver.findElement(By.linkText(fileToUpload.getName())).click();
        Thread.sleep(2000);
        File downloadedFile = new File(new File("downloads"), fileToUpload.getName());
        int count = 0;
        while (count<60){
            long lengthBefore = downloadedFile.length();
            Thread.sleep(1000);
            long lengthAfter = downloadedFile.length();
            if(lengthBefore == lengthAfter){
                break;
            }
            count++;
        }
        Assert.assertTrue(FileUtils.contentEquals(fileToUpload, downloadedFile));

    }

    private void checkFileDownloadLink() {
        WebElement element = driver.findElement(By.linkText(fileToUpload.getName()));
        Assert.assertTrue(element.isDisplayed());
    }

    private void checkFileUpload() {
        String trim = driver.findElement(By.id("uploaded-files")).getText().trim();
        Assert.assertEquals(trim, fileToUpload.getName());
    }

    private File generateTestFile (File template){
        File file = new File("files/lorem " + new Date().getTime() + ".txt");
        try {
            FileUtils.copyFile(template, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file;
    }

    private void uploadFile(){
        WebElement uploadInput = driver.findElement(By.id("file-upload"));
        uploadInput.sendKeys( fileToUpload.getAbsolutePath());
        driver.findElement(By.id("file-submit")).click();

    }
}
