package com.example.job_api.controller;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import com.example.job_api.dto.JobDTO;
import com.example.job_api.exception.JobNotFoundException;
import com.example.job_api.service.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/jobs")
public class JobController<PaginatedResponse> {

    private final JobService jobService;

    public JobController(JobService jobService) {

        this.jobService = jobService;
    }


    @PostMapping
    public ResponseEntity<?> createJob(@Valid @RequestBody JobDTO jobDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            bindingResult.getFieldErrors().forEach(error ->
                    errors.append(error.getField())
                            .append(": ")
                            .append(error.getDefaultMessage())
                            .append("; ")
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid request data: " + errors.toString());
        }

        try {
            Long savedJobId = jobService.createJob(jobDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("JD successfully created with ID: " + savedJobId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred: " + e.getMessage());
        }
    }
    @GetMapping("/{job_id}")
    public ResponseEntity<?> getJobById(@PathVariable("job_id") Long id) {
        try {
            JobDTO jobDTO = jobService.getJobById(id);
            return new ResponseEntity<>(jobDTO, HttpStatus.OK);
        } catch (JobNotFoundException e) {
            return new ResponseEntity<>("Job not found: " + e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal Server Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping
    public ResponseEntity<?> getAllJobs() {
        try {
            List<JobDTO> jobDTO = jobService.getAllJobs();
            return new ResponseEntity<>(jobDTO, HttpStatus.OK);
        } catch (JobNotFoundException e) {
            return new ResponseEntity<>("Job not found: " + e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal Server Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/search")
    public String searchJobs(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "assigned_to", required = false) Long assignedTo,
            @RequestParam(value = "employment_type", required = false) String employmentType,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "designation", required = false) String designation,
            @RequestParam(value = "total_exp", required = false) Integer totalExp,
            @RequestParam(value = "primary_skills", required = false) String primarySkills,
            @RequestParam(value = "secondary_skills", required = false) String secondarySkills,
            @RequestParam(value = "notice_period", required = false) Integer noticePeriod,
            @RequestParam(value = "preferred_location", required = false) String preferredLocation,
            @RequestParam(value = "company_name", required = false) String companyName,
            @RequestParam(value = "company_url", required = false) String companyUrl,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit)
     {
         return "Hello";
//        try {
//
//            PaginatedResponse<JobDTO> response = jobService.searchJobs(
//                    title, assignedTo, employmentType, status, description,
//                    designation, totalExp, primarySkills, secondarySkills,
//                    noticePeriod, preferredLocation, companyName, companyUrl,
//                    page, limit);
//            return ResponseEntity.ok(response);
//        } catch (JobNotFoundException e) {
//            return new ResponseEntity<>("Job not found: " + e.getMessage(), HttpStatus.NOT_FOUND);
//        } catch (Exception e) {
//            return new ResponseEntity<>("Internal Server Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @PutMapping("/{job_id}")
    public ResponseEntity<?> updateJob(@PathVariable("job_id") Long id, @Valid @RequestBody JobDTO jobDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            bindingResult.getFieldErrors().forEach(error ->
                    errors.append(error.getField())
                            .append(": ")
                            .append(error.getDefaultMessage())
                            .append("; ")
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid request data: " + errors.toString());
        }

        try {
            JobDTO updatedJob = jobService.updateJob(id, jobDTO);
            return ResponseEntity.status(HttpStatus.OK).body(updatedJob);

        } catch (JobNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Job not found: " + e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal Server Error: " + e.getMessage());
        }
    }
//    @GetMapping("/search")
//    public ResponseEntity<?> searchJobs(
//            @RequestParam(value = "title", required = false) String title,
//            @RequestParam(value = "assigned_to", required = false) Long assignedTo,
//            @RequestParam(value = "employment_type", required = false) String employmentType,
//            @RequestParam(value = "status", required = false) String status,
//            @RequestParam(value = "description", required = false) String description,
//            @RequestParam(value = "designation", required = false) String designation,
//            @RequestParam(value = "total_exp", required = false) Integer totalExp,
//            @RequestParam(value = "primary_skills", required = false) String primarySkills,
//            @RequestParam(value = "secondary_skills", required = false) String secondarySkills,
//            @RequestParam(value = "notice_period", required = false) Integer noticePeriod,
//            @RequestParam(value = "preferred_location", required = false) String preferredLocation,
//            @RequestParam(value = "company_name", required = false) String companyName,
//            @RequestParam(value = "company_url", required = false) String companyUrl,
//            @RequestParam(value = "page", defaultValue = "0") int page,
//            @RequestParam(value = "limit", defaultValue = "10") int limit) {
//
//        try {
//            PaginatedResponse<JobDTO> response = jobService.searchJobs(
//                    title, assignedTo, employmentType, status, description,
//                    designation, totalExp, primarySkills, secondarySkills,
//                    noticePeriod, preferredLocation, companyName, companyUrl,
//                    page, limit
//            );
//            return ResponseEntity.ok(response);
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest().body("Invalid query parameters: " + e.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("An unexpected error occurred: " + e.getMessage());
//        }
//    }
}
