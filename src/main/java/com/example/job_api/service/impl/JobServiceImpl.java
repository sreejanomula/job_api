package com.example.job_api.service.impl;

import com.example.job_api.dto.JobDTO;
import com.example.job_api.entity.Job;
import com.example.job_api.exception.JobNotFoundException;
import com.example.job_api.mapper.JobMapper;
import com.example.job_api.repository.JobRepository;
import com.example.job_api.service.JobService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final JobMapper jobMapper;

    public JobServiceImpl(JobRepository jobRepository, JobMapper jobMapper) {
        this.jobRepository = jobRepository;
        this.jobMapper = jobMapper;
    }

    @Override
    public Long createJob(JobDTO jobDTO) {
        Job job = jobMapper.toEntity(jobDTO);
        Job savedJob = jobRepository.save(job);
        return savedJob.getId(); // Return the ID of the created job
    }

    @Override
    public JobDTO getJobById(Long id) throws JobNotFoundException {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new JobNotFoundException("Job not found with ID: " + id));
        return jobMapper.toDTO(job);
    }

    @Override
    public JobDTO updateJob(Long id, JobDTO jobDTO) throws JobNotFoundException {
        Job existingJob = jobRepository.findById(id)
                .orElseThrow(() -> new JobNotFoundException("Job not found with ID: " + id));

        // Update fields of the existing job
        existingJob.setTitle(jobDTO.getTitle());
        existingJob.setAssignedTo(jobDTO.getAssignedTo());
        existingJob.setEmploymentType(jobDTO.getEmploymentType());
        existingJob.setStatus(jobDTO.getStatus());
        existingJob.setDescription(jobDTO.getDescription());
        existingJob.setDesignation(jobDTO.getDesignation());
        existingJob.setTotalExp(jobDTO.getTotalExp());
        existingJob.setPrimarySkills(jobDTO.getPrimarySkills());
        existingJob.setSecondarySkills(jobDTO.getSecondarySkills());
        existingJob.setNoticePeriod(jobDTO.getNoticePeriod());
        existingJob.setPreferredLocation(jobDTO.getPreferredLocation());
        existingJob.setCompanyName(jobDTO.getCompanyName());
        existingJob.setCompanyUrl(jobDTO.getCompanyUrl());
        existingJob.setSkills(jobDTO.getSkills());
        existingJob.setKeywords(jobDTO.getKeywords());
        existingJob.setCompanies(jobDTO.getCompanies());
        existingJob.setIndustries(jobDTO.getIndustries());
        existingJob.setColleges(jobDTO.getColleges());
        existingJob.setShortlistedProfiles(jobDTO.getShortlistedProfiles());

        Job updatedJob = jobRepository.save(existingJob);
        return jobMapper.toDTO(updatedJob);
    }

    @Override
    public List<JobDTO> getAllJobs() {
        List<Job> jobs = jobRepository.findAll();
        return jobs.stream()
                .map(jobMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteJob(Long id) throws JobNotFoundException {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new JobNotFoundException("Job not found with ID: " + id));
        jobRepository.delete(job);
    }
//    @Override
//    public Page<JobDTO> searchJobs(String title, Long assignedTo, String employmentType, String status, int page, int size) {
//        Pageable pageable = PageRequest.of(page, size);
//
//        Page<Job> jobPage = jobRepository.searchJobs(title, assignedTo, employmentType, status, pageable);
//        return jobPage.map(job -> modelMapper.map(job, JobDTO.class));
//    }

//    public PaginatedResponse<JobDTO> searchJobs(
//            String title, Long assignedTo, String employmentType, String status,
//            String description, String designation, Integer totalExp, String primarySkills,
//            String secondarySkills, Integer noticePeriod, String preferredLocation,
//            String companyName, String companyUrl, int page, int limit) {
//
//        Pageable pageable = PageRequest.of(page, limit);
//        Page<Job> jobPage = jobRepository.searchJobs(
//                title, assignedTo, employmentType, status, description,
//                designation, totalExp, primarySkills, secondarySkills,
//                noticePeriod, preferredLocation, companyName, companyUrl,
//                pageable
//        );
//
//        List<JobDTO> results = jobPage.getContent().stream()
//                .map(this::convertToDTO)
//                .collect(Collectors.toList());
//
//        return new PaginatedResponse<>(
//                jobPage.getTotalElements(),
//                jobPage.getTotalPages(),
//                page,
//                results
//        );
//    }
}