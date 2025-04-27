package com.example.barang.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "barang")
public class Barang {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "nama_barang", nullable = false)
    private String namaBarang;

    @Column(name = "jumlah_stock_barang")
    private Integer jumlahStockBarang;

    @Column(name = "nomor_seri_barang", unique = true)
    private String nomorSeriBarang;

    @Column(name = "additional_info", columnDefinition = "jsonb")
    private String additionalInfo; // JSON disimpan sebagai String dulu, nanti bisa diparsing manual kalau mau

    @Column(name = "gambar_path")
    private String gambarPath; // Path ke file gambar

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "updated_by")
    private String updatedBy;
}
