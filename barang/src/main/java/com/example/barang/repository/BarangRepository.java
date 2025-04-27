package com.example.barang.repository;

import com.example.barang.entity.Barang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface BarangRepository extends JpaRepository<Barang, Long> {

    @Query(value = "SELECT * FROM barang", nativeQuery = true)
    List<Barang> findAllBarangNative();

    @Query(value = "SELECT * FROM barang WHERE id = :id", nativeQuery = true)
    Optional<Barang> findBarangByIdNative(Long id);
}
