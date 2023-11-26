package com.example.GonzalesRamirezBruno.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class FileService {
    private final Path rootFolder = Paths.get("archivos");

    public void guardar(MultipartFile archivo) throws Exception{
        Files.copy(archivo.getInputStream(),
                this.rootFolder.resolve(archivo.getOriginalFilename()));
    }

    public void guardarArchivos(List<MultipartFile> archivos, String allowedExtension, long maxFileSize) throws Exception {
        for (MultipartFile archivo : archivos) {
            // Verificar la extensión del archivo
            String fileExtension = getFileExtension(archivo.getOriginalFilename());
            if (!fileExtension.equalsIgnoreCase(allowedExtension)) {
                throw new IllegalArgumentException("La extensión del archivo no es válida");
            }

            // Verificar el tamaño del archivo
            if (archivo.getSize() > maxFileSize) {
                throw new IllegalArgumentException("El tamaño del archivo excede el límite permitido");
            }

            // Guardar el archivo si pasa las verificaciones
            guardar(archivo);
        }
    }

    private String getFileExtension(String filename) {
        int dotIndex = filename.lastIndexOf('.');
        if (dotIndex == -1) {
            return ""; // No se encontró una extensión
        }
        return filename.substring(dotIndex + 1);
    }


}
