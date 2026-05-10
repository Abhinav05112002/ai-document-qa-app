package com.aiapp.backend.service;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import com.aiapp.backend.entity.ExtractedContent;
import com.aiapp.backend.entity.UploadedFile;
import com.aiapp.backend.repository.ExtractedContentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PdfService {

    private final ExtractedContentRepository
            extractedContentRepository;
    
    private final RagService ragService;

    public void extractTextFromPdf(
            UploadedFile uploadedFile)
            throws IOException {

        File pdfFile =
                new File(uploadedFile.getFilePath());

        PDDocument document =
                Loader.loadPDF(pdfFile);

        PDFTextStripper stripper =
                new PDFTextStripper();

        String extractedText =
                stripper.getText(document);

        document.close();

        ExtractedContent extractedContent =
                ExtractedContent.builder()
                .uploadedFile(uploadedFile)
                .extractedText(extractedText)
                .summary(null)
                .build();

        extractedContentRepository
                .save(extractedContent);
        
        ragService.processDocument(uploadedFile);
    }
}
