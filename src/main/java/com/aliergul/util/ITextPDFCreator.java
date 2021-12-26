package com.aliergul.util;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import com.aliergul.entity.AlbumEntity;
import com.aliergul.entity.OrderEntity;
import com.aliergul.entity.ProductEntity;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.font.PdfFontFactory.EmbeddingStrategy;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.CompressionConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;

public class ITextPDFCreator {
  private static final String PDF_FILE_PATH = "./ornek2.pdf";
  private static final String FONT_PATH = ITextPDFCreator.class
      .getResource("../../../OpenSans-Light.ttf").toExternalForm().substring(6);
  private static final String LOGO_PATH = ITextPDFCreator.class
      .getResource("../../../com/aliergul/img/logo.png").toExternalForm().substring(6);
  private java.util.List<OrderEntity> orders = new ArrayList<OrderEntity>();
  private PdfFont font = null;
  private String path;

  public ITextPDFCreator(java.util.List<OrderEntity> order, String path) {
    if (order != null) {
      this.orders.addAll(order);
      this.path = path;
    }

  }



  public void createITextTablePDF() throws Exception {

    File file = new File(path);
    file.getParentFile().mkdirs();
    // Initialize PDF writer
    PdfWriter writer = new PdfWriter(path);

    // Initialize PDF document
    PdfDocument pdf = new PdfDocument(writer);
    // Initialize document
    Document document = new Document(pdf);
    // FontProgramFactory.registerSystemFontDirectories();
    // FontProgramFactory.registerFont("c:/windows/fonts/Arial.ttf", "garamond bold");
    // PdfFont f1 = PdfFontFactory.createRegisteredFont("garamond bold");


    getFontForThisLanguage();
    // Resim ekleme
    PageSize pageSize = PageSize.A4.rotate();
    Image image = new Image(ImageDataFactory.create(LOGO_PATH));
    image.getXObject().getPdfObject().setCompressionLevel(CompressionConstants.BEST_COMPRESSION);
    image.scaleAbsolute(100, 100);
    image.setFixedPosition(pageSize.getHeight() - 150, pageSize.getWidth() - 150);
    // with= 842.0
    // height= 595.0

    document.add(image);
    // Add paragraph to the document
    Table headerTable = new Table(1).useAllAvailableWidth();
    headerTable.addCell(getCell("Record STORE", 36, TextAlignment.CENTER));
    document.add(headerTable);
    document.add(new Paragraph("Koleksiyoncular için").setFont(font));
    // Create a List
    List list = new List().setSymbolIndent(12).setListSymbol("\u2022").setFont(font);
    // Add ListItem objects

    list.add(new ListItem("En Eski Vinvyl")).add(new ListItem("Koleksiyon Parçaları"))
        .add(new ListItem("En Son Çıkan Albümler"));
    // Add the list
    document.add(list.setFont(font));
    document.add(new Paragraph(""));
    document.add(new Paragraph(""));
    document.add(new Paragraph("Sepet Ayrıntıları").setFont(font));
    Table table = new Table(7).useAllAvailableWidth();
    table.setFont(font);
    table.addHeaderCell("Sıra").setBold().setFont(font);
    table.addHeaderCell("Albüm Adı").setBold().setFont(font);
    table.addHeaderCell("Adet").setFont(font).setBold();
    table.addHeaderCell("Iskonto").setFont(font).setBold();
    table.addHeaderCell("Fiyat").setFont(font).setBold();
    table.addHeaderCell("Indr. Fiyat").setFont(font).setBold();
    table.addHeaderCell("Tutar").setFont(font).setBold();
    long sum_count = 0;
    double sum_pierce = 0;
    for (int i = 0; i < orders.size(); i++) {
      OrderEntity order = orders.get(i);
      ProductEntity product = order.getProduct();
      AlbumEntity album = product.getAlbum();
      table.addCell(getNormalCell((i + 1) + "", 10L));
      table.addCell(getNormalCell(album.getName() + "- " + product.getTypeProduct(), 10));
      table.addCell(getNormalCell(order.getCount() + " adt", 10));
      table.addCell(getNormalCell(String.format("%.02f \u2030", order.getDiscountRate()), 10));
      table.addCell(getNormalCell(product.getPierce() + " TL", 10));
      table.addCell(getNormalCell(String.format("%.02f TL", order.getDiscounted_pierce()), 10));
      table.addCell(getNormalCell(
          String.format("%.02f TL", order.getDiscounted_pierce() * order.getCount()), 10));;
      sum_count += order.getCount();
      sum_pierce += order.getDiscounted_pierce() * order.getCount();
    }

    // add table

    document.add(table);
    document.add(new Paragraph(""));
    document.add(new Paragraph(""));
    Table sumTable = new Table(2).setHorizontalAlignment(HorizontalAlignment.RIGHT);
    String sumCountString = String.format("%02d", sum_count);
    String sumPierceString = String.format("%.02f", sum_pierce);
    sumTable.addCell(getCell("Toplam Adet : ", 12, TextAlignment.RIGHT).setBold());
    sumTable.addCell(getCell(String.format(" %10s adt", sumCountString), 12, TextAlignment.RIGHT));
    sumTable.addCell(getCell("Toplam Fiyat : ", 12, TextAlignment.RIGHT).setBold());
    sumTable.addCell(getCell(String.format(" %10s  TL", sumPierceString), 12, TextAlignment.RIGHT));
    document.add(sumTable);
    // Close document
    document.close();
    openPDF();


  }

  public Cell getCell(String text, float size, TextAlignment alignment) {
    Cell cell = new Cell().add(new Paragraph(text));
    cell.setPadding(0);
    cell.setFontSize(size);
    cell.setTextAlignment(alignment);
    cell.setBorder(Border.NO_BORDER);
    return cell;
  }

  private Cell getNormalCell(String string, float size) throws IOException {
    if (string != null && "".equals(string)) {
      return new Cell();
    }


    Paragraph paragraph = new Paragraph(string).setFont(font);

    Cell cell = new Cell().add(paragraph);
    cell.setHorizontalAlignment(HorizontalAlignment.LEFT);


    if (size < 0) {
      size = -size;
      cell.setFontSize(size);
      cell.setFontColor(ColorConstants.RED);
    }

    return cell;
  }


  private PdfFont getFontForThisLanguage() {
    try {
      this.font = PdfFontFactory.createFont(FONT_PATH, PdfEncodings.PDF_DOC_ENCODING,
          EmbeddingStrategy.PREFER_EMBEDDED);
    } catch (IOException e) {
    }
    return font;
  }

  private void openPDF() {
    try {

      File pdfFile = new File(path);
      if (pdfFile.exists()) {

        if (Desktop.isDesktopSupported()) {
          Desktop.getDesktop().open(pdfFile);
        } else {
          System.out.println("Awt Desktop is not supported!");
        }

      } else {
        System.out.println("File is not exists!");
      }

      System.out.println("Done");

    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
