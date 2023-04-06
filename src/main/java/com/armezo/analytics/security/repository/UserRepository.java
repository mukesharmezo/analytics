package com.armezo.analytics.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.armezo.analytics.security.entity.AnalyticsUser;

public interface UserRepository extends JpaRepository<AnalyticsUser, Long> {

	@Query("SELECT u FROM AnalyticsUser u WHERE u.username = :username")
	Optional<AnalyticsUser> getUserByUsername(@Param("username") String username);


}
