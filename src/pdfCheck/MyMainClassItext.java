package pdfCheck;

import com.itextpdf.kernel.geom.AffineTransform;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.kernel.pdf.PdfReader;

import java.io.*;


/**
 * Created by asus on 7/13/2020.
 */
public class MyMainClassItext  {

    private static final String inputFile ="E:/pdf/secure.pdf";
    private static final String outputFile1="E:/pdf/text1.pdf";
    private static final String notEncryptFile="E:/pdf/text.pdf";


    public static void main(String[] args) throws Exception {

         manipulatePdf(notEncryptFile);
         makeNewPdfFile(notEncryptFile);

    }


    protected static void manipulatePdf(String decryptFile) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(inputFile).setUnethicalReading(true), new PdfWriter(decryptFile));
        pdfDoc.close();

    }






    //______________________________________________________________________________________

    static void makeNewPdfFileA4(String decryptFile) {
        PdfDocument pdf =null;

        PdfDocument origPdf = null;
        try {
            pdf = new PdfDocument(new PdfWriter(outputFile1));
            origPdf = new PdfDocument(new PdfReader(decryptFile));

            System.out.println(origPdf.getNumberOfPages());
            for(int i=1; i<=origPdf.getNumberOfPages(); i++) {

                PdfPage origPage = origPdf.getPage(i);

                Rectangle orig = origPage.getPageSizeWithRotation();

                 PdfPage page = pdf.addNewPage(PageSize.A4.rotate());


                 PdfCanvas canvas = new PdfCanvas(page);

                 AffineTransform transformationMatrix = AffineTransform.getScaleInstance(

                         page.getPageSize().getWidth() / orig.getWidth(),

                         page.getPageSize().getHeight() / orig.getHeight());

                 canvas.concatMatrix(transformationMatrix);

                 PdfFormXObject pageCopy = origPage.copyAsFormXObject(pdf);

                 canvas.addXObject(pageCopy, 0, 0);

            }

            pdf.close();

            origPdf.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //______________________________________________________________________________________


    static void makeNewPdfFileA2(String decryptFile) {
        PdfDocument pdf =null;

        PdfDocument origPdf = null;
        try {
            pdf = new PdfDocument(new PdfWriter(outputFile1));
            origPdf = new PdfDocument(new PdfReader(decryptFile));

            System.out.println(origPdf.getNumberOfPages());
            for(int i=1; i<=origPdf.getNumberOfPages(); i++) {

                PdfPage origPage = origPdf.getPage(i);

                Rectangle orig = origPage.getPageSizeWithRotation();

                PdfPage page = pdf.addNewPage(PageSize.A2.rotate());


                PdfCanvas canvas = new PdfCanvas(page);

                AffineTransform transformationMatrix = AffineTransform.getScaleInstance(

                        page.getPageSize().getWidth() / orig.getWidth(),

                        page.getPageSize().getHeight() / orig.getHeight());

                canvas.concatMatrix(transformationMatrix);
                PdfFormXObject pageCopy = origPage.copyAsFormXObject(pdf);
                canvas.addXObject(pageCopy, 0, 0);
            }

            pdf.close();

            origPdf.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static void readPdfTextContent(String decryptFile){
        try {


            PdfDocument pdfDoc = new PdfDocument(new PdfReader(decryptFile));
            for(int i=1; i<=pdfDoc.getNumberOfPages(); i++) {
            PdfPage origPage = pdfDoc.getPage(i);
            String text = PdfTextExtractor.getTextFromPage(origPage);
            System.out.println("Page number = " + i + ",  -> content:");
            System.out.println(text + "\n");
        }

       } catch (IOException e) {
        e.printStackTrace();
        }
    }

    //______________________________________________________________________________________

    static void makeNewPdfFile(String decryptFile) {


         PdfDocument pdf =null;

         PdfDocument origPdf = null;
         try {
             pdf = new PdfDocument(new PdfWriter(outputFile1));
             origPdf = new PdfDocument(new PdfReader(decryptFile));

             System.out.println(origPdf.getNumberOfPages());
             for(int i=1; i<=origPdf.getNumberOfPages(); i++) {

                 PdfPage origPage = origPdf.getPage(i);

                 Rectangle orig = origPage.getPageSizeWithRotation();
                 pdf.addPage(origPage.copyTo(pdf));
            }

             pdf.close();

             origPdf.close();

         } catch (IOException e) {
             e.printStackTrace();
         }


    }






}
