package com.kokobato.huynhduc.kokobatodemo.repositories;

import com.kokobato.huynhduc.kokobatodemo.models.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeRepository extends JpaRepository<Office, Integer> {

}
