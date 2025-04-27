package com.example.barang.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UpdateBarangRequest {

    private String namaBarang;
    private Integer jumlahStockBarang;
    private String nomorSeriBarang;
    private String additionalInfo; // JSON String
    private MultipartFile gambar;  // Optional update gambar
    private String updatedBy;
}
