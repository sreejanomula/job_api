package com.example.job_api.mapper;

import com.example.job_api.dto.JobDTO;
import com.example.job_api.entity.Job;
import org.springframework.stereotype.Component;

@Component
public class JobMapper {

    public Job toEntity(JobDTO jobDTO) {
        Job job = new Job();
        job.setId(jobDTO.getId());
        job.setTitle(jobDTO.getTitle());
        job.setAssignedTo(jobDTO.getAssignedTo());
        job.setEmploymentType(jobDTO.getEmploymentType());
        job.setStatus(jobDTO.getStatus());
        job.setDescription(jobDTO.getDescription());
        job.setDesignation(jobDTO.getDesignation());
        job.setTotalExp(jobDTO.getTotalExp());
        job.setPrimarySkills(jobDTO.getPrimarySkills());
        job.setSecondarySkills(jobDTO.getSecondarySkills());
        job.setNoticePeriod(jobDTO.getNoticePeriod());
        job.setPreferredLocation(jobDTO.getPreferredLocation());
        job.setCompanyName(jobDTO.getCompanyName());
        job.setCompanyUrl(jobDTO.getCompanyUrl());
        job.setSkills(jobDTO.getSkills());
        job.setKeywords(jobDTO.getKeywords());
        job.setCompanies(jobDTO.getCompanies());
        job.setIndustries(jobDTO.getIndustries());
        job.setColleges(jobDTO.getColleges());
        job.setShortlistedProfiles(jobDTO.getShortlistedProfiles());
        return job;
    }

    public JobDTO toDTO(Job job) {
        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(job.getId());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setAssignedTo(job.getAssignedTo());
        jobDTO.setEmploymentType(job.getEmploymentType());
        jobDTO.setStatus(job.getStatus());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setDesignation(job.getDesignation());
        jobDTO.setTotalExp(job.getTotalExp());
        jobDTO.setPrimarySkills(job.getPrimarySkills());
        jobDTO.setSecondarySkills(job.getSecondarySkills());
        jobDTO.setNoticePeriod(job.getNoticePeriod());
        jobDTO.setPreferredLocation(job.getPreferredLocation());
        jobDTO.setCompanyName(job.getCompanyName());
        jobDTO.setCompanyUrl(job.getCompanyUrl());
        jobDTO.setSkills(job.getSkills());
        jobDTO.setKeywords(job.getKeywords());
        jobDTO.setCompanies(job.getCompanies());
        jobDTO.setIndustries(job.getIndustries());
        jobDTO.setColleges(job.getColleges());
        jobDTO.setShortlistedProfiles(job.getShortlistedProfiles());
        return jobDTO;
    }
}