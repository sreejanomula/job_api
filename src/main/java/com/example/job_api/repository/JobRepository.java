package com.example.job_api.repository;

import com.example.job_api.entity.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
//mport org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository


public interface JobRepository extends JpaRepository<Job, Long > {
//    @Query("SELECT j FROM Job j WHERE " +
//            "(:title IS NULL OR j.title LIKE %:title%) AND " +
//            "(:assignedTo IS NULL OR j.assignedTo = :assignedTo) AND " +
//            "(:employmentType IS NULL OR j.employmentType = :employmentType) AND " +
//            "(:status IS NULL OR j.status = :status) AND " +
//            "(:description IS NULL OR j.description LIKE %:description%)")
//    Page<Job> searchJobs(
//            @Param("title") String title,
//            @Param("assignedTo") Long assignedTo,
//            @Param("employmentType") String employmentType,
//            @Param("status") String status,
//            @Param("description") String description,
//            Pageable pageable);
}
