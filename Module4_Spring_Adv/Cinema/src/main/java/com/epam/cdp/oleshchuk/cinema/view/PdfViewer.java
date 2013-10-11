package com.epam.cdp.oleshchuk.cinema.view;

import com.epam.cdp.oleshchuk.cinema.model.Ticket;
import com.epam.cdp.oleshchuk.cinema.model.User;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;


public class PdfViewer extends AbstractPdfView {

    protected void buildPdfDocument(
            Map model,
            Document doc,
            PdfWriter writer,
            HttpServletRequest req,
            HttpServletResponse resp)
            throws Exception {

        String error = (String) model.get("error");
        User user = (User) model.get("user");


        if (error != null) {
            doc.add(new Paragraph(error));
        } else {
            if (user != null) {
                doc.add(new Paragraph(user.getName() + ", this is your tickets\r\n"));
                doc.add(new Paragraph("\r\n"));
            }


            List<Ticket> tickets = (List<Ticket>) model.get("ticketsList");
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.addCell(createHeaderCell("Date"));
            table.addCell(createHeaderCell("Film name"));
            table.addCell(createHeaderCell("Category"));
            table.addCell(createHeaderCell("Place"));

            for (Ticket ticket : tickets) {
                table.addCell(createCell(new SimpleDateFormat("dd/MM/yy").format(ticket.getDate())));
                table.addCell(createCell(ticket.getTitle()));
                table.addCell(createCell(ticket.getTicketCategory().toString()));
                table.addCell(createCell(ticket.getPlace().toString()));
            }
            doc.add(table);
        }
    }


    private PdfPCell createHeaderCell(String text) {
        Paragraph paragraph = new Paragraph(text);
        PdfPCell cell1 = new PdfPCell(paragraph);
        cell1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell1.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
        cell1.setBackgroundColor(Color.yellow);
        return cell1;
    }

    private PdfPCell createCell(String text) {
        Paragraph paragraph = new Paragraph(text);
        PdfPCell cell1 = new PdfPCell(paragraph);
        cell1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell1.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
        return cell1;
    }
}