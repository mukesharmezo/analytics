package com.armezo.analytics.demographic.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.armezo.analytics.demographic.entity.Demographic;

@Repository
public interface DemographicRepository extends JpaRepository<Demographic, Long> {

	@Query("SELECT d FROM Demographic d WHERE d.accesskey =:accesskey ")
	Optional<Demographic> getDemographicByAccesskey(String accesskey);

	@Query("SELECT d FROM Demographic d WHERE (d.accesskey IN :accesskeyList OR :accesskeyList IS NULL) AND (d.registrationDate >= :dateFrom AND d.registrationDate <= :dateTo)")
	List<Demographic> getDemographicsByAccesskeyList(List<String> accesskeyList, Date dateFrom, Date dateTo);

	@Query("SELECT d FROM Demographic d WHERE (:appName IS NULL OR (LOWER(d.applicationName) LIKE LOWER(:appName)) OR :appName='') AND "
			+ "(:clientId IS NULL OR d.clientId = :clientId OR :clientId = '') AND (d.registrationDate >= :dateFrom AND d.registrationDate <= :dateTo)")
	List<Demographic> getDemographicByApplicationnameClientId(String appName, String clientId, Date dateFrom,
			Date dateTo);

}
