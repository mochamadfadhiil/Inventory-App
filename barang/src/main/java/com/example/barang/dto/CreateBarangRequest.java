package com.example.barang.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CreateBarangRequest {

    private String namaBarang;
    private Integer jumlahStockBarang;
    private String nomorSeriBarang;
    private String additionalInfo; // JSON String
    private MultipartFile gambar;  // Untuk upload gambar
    private String createdBy;
}
