package qa.guru;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import static org.assertj.core.api.Assertions.assertThat;

public class FileParse {

    ClassLoader cl = FileParse.class.getClassLoader();

    @DisplayName("ZipPDF test")
    @Test
    void zipPdfTest() throws Exception {
        try (ZipFile zf = new ZipFile(new File("src/test/resources/Test.zip"))) {

            ZipInputStream is = new ZipInputStream(cl.getResourceAsStream("Test.zip"));
            ZipEntry entry;
            while ((entry = is.getNextEntry()) != null) {
                if (entry.getName().contains(".pdf")) {
                    try (InputStream inputStream = zf.getInputStream(entry)) {
                        PDF pdf = new PDF(inputStream);
                        assertThat(pdf.text).contains("Текст");

                    }
                }
            }
        }
    }
    @DisplayName("ZipCSV test")
    @Test
    void zipCsvTest() throws Exception {
        try (ZipFile zf = new ZipFile(new File("src/test/resources/Test.zip"))) {

            ZipInputStream is = new ZipInputStream(cl.getResourceAsStream("Test.zip"));
            ZipEntry entry;
            while ((entry = is.getNextEntry()) != null) {
                if (entry.getName().contains(".csv")) {
                    try (InputStream inputStream = zf.getInputStream(entry)) {
                        CSVReader reader = new CSVReader(new InputStreamReader(inputStream));
                        List<String[]> content = reader.readAll();
                        content.forEach(x -> System.out.println(Arrays.toString(x)));
                        org.assertj.core.api.Assertions.assertThat(content).contains(
                                new String[]{"1"+";"+"2"},
                                new String[]{"Test"+";"+"Grove"} ,
                                new String[]{"Grome"+";"+"West"});


                    }
                }
            }
        }
    }
    @DisplayName("ZipXlsx test")
    @Test
    void zipXlsxTest() throws Exception {
        try (ZipFile zf = new ZipFile(new File("src/test/resources/Test.zip"))) {

            ZipInputStream is = new ZipInputStream(cl.getResourceAsStream("Test.zip"));
            ZipEntry entry;
            while ((entry = is.getNextEntry()) != null) {
                if (entry.getName().contains("new-xlsx.xlsx")) {
                    try (InputStream inputStream = zf.getInputStream(entry)) {
                        XLS xls = new XLS(inputStream);
                        assertThat(
                                xls.excel.getSheetAt(0)
                                        .getRow(1)
                                        .getCell(1)
                                        .getStringCellValue()
                        ).isEqualTo("Бын");
                        assertThat(
                                xls.excel.getSheetAt(0)
                                        .getRow(2)
                                        .getCell(1)
                                        .getStringCellValue()
                        ).isEqualTo("Вын");

                    }
                }
            }
        }
    }
}








