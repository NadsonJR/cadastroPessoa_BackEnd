package org.desafio.utils;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Log4j2
public class Utilities {
    private List<String> screenshots = new ArrayList<>();

    public void runPowerShellCommand(String command) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("powershell.exe", command);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void takeScreenshot(WebDriver driver, String fileName) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            String filePath = "evidencias/" + fileName;
            FileUtils.copyFile(screenshot, new File(filePath));
            screenshots.add(filePath);
            log.info("Screenshot saved: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generateDocument() {
        XWPFDocument document = new XWPFDocument();
        File folder = new File("evidencias");
        File[] listOfFiles = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".png"));

        if (listOfFiles == null || listOfFiles.length == 0) {
            log.warn("No screenshots to add to the document.");
            return;
        }
        try {
            for (File file : listOfFiles) {
                String screenshot = file.getAbsolutePath();
                XWPFParagraph paragraph = document.createParagraph();
                XWPFRun run = paragraph.createRun();
                run.setText("Screenshot: " + screenshot);
                run.addBreak();
                try (FileInputStream is = new FileInputStream(screenshot)) {
                    run.addPicture(is, XWPFDocument.PICTURE_TYPE_PNG, screenshot, Units.toEMU(500), Units.toEMU(300));
                }
                run.addBreak(BreakType.PAGE);
                log.info("Screenshot added to document: " + screenshot);
            }
            try (FileOutputStream out = new FileOutputStream("evidencias/TestEvidence.docx")) {
                document.write(out);
                log.info("Document generated: evidencias/TestEvidence.docx");
            }
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

