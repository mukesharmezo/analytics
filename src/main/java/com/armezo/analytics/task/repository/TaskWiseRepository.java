package com.armezo.analytics.task.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.armezo.analytics.task.entity.Taskwise;

@Repository
public interface TaskWiseRepository extends JpaRepository<Taskwise, Long> {

	@Query("SELECT t FROM Taskwise t WHERE t.applicationName = :applicationName AND t.clientId = :clientId AND  t.accesskey = :accesskey")
	Optional<Taskwise> getTakwiseByAppNameClientIdAccesskey(String applicationName, String clientId, String accesskey);

	@Query("SELECT t FROM Taskwise t WHERE t.applicationName = :applicationName AND t.clientId = :clientId AND  (t.registrationDate >= :dateFrom AND t.registrationDate <= :dateTo)")
	List<Taskwise> getTaskwiseByAppNameClientId(String applicationName, String clientId, Date dateFrom, Date dateTo);

}
