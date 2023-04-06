package com.armezo.analytics.demographic.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.armezo.analytics.demographic.entity.ClientDetails;

@Repository
public interface ClientDetailsRepository extends JpaRepository<ClientDetails, Long> {

	Optional<ClientDetails> findByApplicationName(String appName);

}
