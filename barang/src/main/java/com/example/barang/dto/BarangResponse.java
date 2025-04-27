package com.example.barang.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BarangResponse {

    private Long id;
    private String namaBarang;
    private Integer jumlahStockBarang;
    private String nomorSeriBarang;
    private String additionalInfo;
    private String gambarPath;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
}
