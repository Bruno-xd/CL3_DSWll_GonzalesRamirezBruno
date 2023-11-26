package com.example.GonzalesRamirezBruno.demo.controller;

import com.example.GonzalesRamirezBruno.demo.model.response.ResponseFile;
import com.example.GonzalesRamirezBruno.demo.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/file")
public class FileControllerPdf {

    private FileService fileService;

    @PreAuthorize("hasRole('ROLE_SUPERVISOR')")
    @PostMapping("/filespdf")
    public ResponseEntity<ResponseFile> uploadPdfFiles(
            @RequestParam("files") List<MultipartFile> files) throws Exception {

        fileService.guardarArchivos(files, "pdf", Long.MAX_VALUE);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseFile.builder().message("Los archivos PDF fueron cargados correctamente").build());
    }
}
