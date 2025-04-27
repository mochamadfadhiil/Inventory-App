package com.example.barang.controller;

import com.example.barang.dto.BarangResponse;
import com.example.barang.dto.CreateBarangRequest;
import com.example.barang.dto.UpdateBarangRequest;
import com.example.barang.service.BarangService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartException;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/barang")
@RequiredArgsConstructor
@Log4j2
public class BarangController {

    private final BarangService barangService;

    @PostMapping
    public ResponseEntity<BarangResponse> createBarang(@ModelAttribute CreateBarangRequest request) {
        try {
            BarangResponse response = barangService.createBarang(request);
            return ResponseEntity.ok(response);
        } catch (IOException | MultipartException e) {
            log.error("Failed to create barang", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<BarangResponse>> listBarang() {
        List<BarangResponse> list = barangService.listBarang();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BarangResponse> detailBarang(@PathVariable Long id) {
        try {
            BarangResponse response = barangService.detailBarang(id);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            log.error("Barang not found", e);
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BarangResponse> updateBarang(
            @PathVariable Long id,
            @ModelAttribute UpdateBarangRequest request
    ) {
        try {
            BarangResponse response = barangService.updateBarang(id, request);
            return ResponseEntity.ok(response);
        } catch (IOException | RuntimeException e) {
            log.error("Failed to update barang", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBarang(@PathVariable Long id) {
        try {
            barangService.deleteBarang(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            log.error("Failed to delete barang", e);
            return ResponseEntity.notFound().build();
        }
    }
}
