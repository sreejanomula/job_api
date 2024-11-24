package com.example.job_api.service;

import com.example.job_api.dto.JobDTO;
import com.example.job_api.exception.JobNotFoundException;
//import org.springframework.data.domain.Page;

import java.util.List;
//import java.util.Optional;

public interface JobService{
    Long createJob (JobDTO JobDTO);
    JobDTO getJobById(Long id) throws JobNotFoundException;
    JobDTO updateJob(Long id, JobDTO JobDTO) throws JobNotFoundException;
    List<JobDTO> getAllJobs();
    void deleteJob(Long id) throws JobNotFoundException;
    //Page<JobDTO> searchJobs(String title, Long assignedTo, String employmentType, String status, int page, int size);

}