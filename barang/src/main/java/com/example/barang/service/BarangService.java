package com.example.barang.service;

import com.example.barang.dto.BarangResponse;
import com.example.barang.dto.CreateBarangRequest;
import com.example.barang.dto.UpdateBarangRequest;
import com.example.barang.entity.Barang;
import com.example.barang.repository.BarangRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class BarangService {

    private final BarangRepository barangRepository;

    private final String uploadDir = "uploads/";

    public BarangResponse createBarang(CreateBarangRequest request) throws IOException {
        log.info("Creating new barang: {}", request.getNamaBarang());

        Barang barang = new Barang();
        barang.setNamaBarang(request.getNamaBarang());
        barang.setJumlahStockBarang(request.getJumlahStockBarang());
        barang.setNomorSeriBarang(request.getNomorSeriBarang());
        barang.setAdditionalInfo(request.getAdditionalInfo());
        barang.setCreatedBy(request.getCreatedBy());

        // Handle gambar upload
        if (request.getGambar() != null && !request.getGambar().isEmpty()) {
            String gambarPath = saveGambar(request.getGambar());
            barang.setGambarPath(gambarPath);
        }

        barang = barangRepository.save(barang);

        return mapToResponse(barang);
    }

    public List<BarangResponse> listBarang() {
        log.info("Listing all barang");
        return barangRepository.findAllBarangNative().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public BarangResponse detailBarang(Long id) {
        log.info("Fetching detail barang id: {}", id);
        Barang barang = barangRepository.findBarangByIdNative(id)
                .orElseThrow(() -> new RuntimeException("Barang not found"));
        return mapToResponse(barang);
    }

    public BarangResponse updateBarang(Long id, UpdateBarangRequest request) throws IOException {
        log.info("Updating barang id: {}", id);
        Barang barang = barangRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Barang not found"));

        if (request.getNamaBarang() != null) barang.setNamaBarang(request.getNamaBarang());
        if (request.getJumlahStockBarang() != null) barang.setJumlahStockBarang(request.getJumlahStockBarang());
        if (request.getNomorSeriBarang() != null) barang.setNomorSeriBarang(request.getNomorSeriBarang());
        if (request.getAdditionalInfo() != null) barang.setAdditionalInfo(request.getAdditionalInfo());
        if (request.getUpdatedBy() != null) barang.setUpdatedBy(request.getUpdatedBy());

        if (request.getGambar() != null && !request.getGambar().isEmpty()) {
            String gambarPath = saveGambar(request.getGambar());
            barang.setGambarPath(gambarPath);
        }

        barang = barangRepository.save(barang);

        return mapToResponse(barang);
    }

    public void deleteBarang(Long id) {
        log.info("Deleting barang id: {}", id);
        Barang barang = barangRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Barang not found"));
        barangRepository.delete(barang);
    }

    private String saveGambar(MultipartFile file) throws IOException {
        // Validasi MIME Type yang lebih aman
        String contentType = file.getContentType();
        if (contentType == null || 
            !(contentType.equals("image/jpeg") || contentType.equals("image/png"))) {
            throw new RuntimeException("Only JPG or PNG images are allowed");
        }

        // Save gambar ke folder uploads/
        File uploadPath = new File(uploadDir);
        if (!uploadPath.exists()) {
            uploadPath.mkdirs();
        }

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File destination = new File(uploadDir + fileName);
        file.transferTo(destination);

        return destination.getPath();
    }

    private BarangResponse mapToResponse(Barang barang) {
        BarangResponse response = new BarangResponse();
        response.setId(barang.getId());
        response.setNamaBarang(barang.getNamaBarang());
        response.setJumlahStockBarang(barang.getJumlahStockBarang());
        response.setNomorSeriBarang(barang.getNomorSeriBarang());
        response.setAdditionalInfo(barang.getAdditionalInfo());
        response.setGambarPath(barang.getGambarPath());
        response.setCreatedAt(barang.getCreatedAt());
        response.setCreatedBy(barang.getCreatedBy());
        response.setUpdatedAt(barang.getUpdatedAt());
        response.setUpdatedBy(barang.getUpdatedBy());
        return response;
    }
}
