package com.example.sbjpacreditcard.repository;

import com.example.sbjpacreditcard.domain.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCarsRepository extends JpaRepository<CreditCard, Long> {
}
