package util;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import model.Product;

import java.io.FileOutputStream;
import java.util.List;

public class PDFUtil {
    public static void exportProducts(List<Product> products, String filePath) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            document.add(new Paragraph("Product Inventory Report"));
            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(3);
            table.addCell("ID");
            table.addCell("Name");
            table.addCell("Quantity");

            for (Product p : products) {
                table.addCell(String.valueOf(p.getId()));
                table.addCell(p.getName());
                table.addCell(String.valueOf(p.getQuantity()));
            }

            document.add(table);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }
}
