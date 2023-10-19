package com.example.api.OdfFtp.Ftp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FtpRepository extends JpaRepository<Ftp, Long> {
    
}
