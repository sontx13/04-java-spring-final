package vn.project.smart.controller;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkraft.springfilter.boot.Filter;

import jakarta.validation.Valid;
import vn.project.smart.domain.Exam;
import vn.project.smart.domain.response.ResultPaginationDTO;
import vn.project.smart.service.ExamService;
import vn.project.smart.util.annotation.ApiMessage;

@RestController
@RequestMapping("/api/v1")
public class ExamController {
    private final ExamService ExamService;

    public ExamController(ExamService ExamService) {
        this.ExamService = ExamService;
    }

    @PostMapping("/exams")
    public ResponseEntity<?> createExam(@Valid @RequestBody Exam reqExam) {

        return ResponseEntity.status(HttpStatus.CREATED).body(this.ExamService.handleCreateExam(reqExam));
    }

    @GetMapping("/exams")
    @ApiMessage("Fetch exams")
    public ResponseEntity<ResultPaginationDTO> getExam(
            @Filter Specification<Exam> spec, Pageable pageable) {

        return ResponseEntity.ok(this.ExamService.handleGetExam(spec, pageable));
    }

    @PutMapping("/exams")
    public ResponseEntity<Exam> updateExam(@Valid @RequestBody Exam reqExam) {
        Exam updatedExam = this.ExamService.handleUpdateExam(reqExam);
        return ResponseEntity.ok(updatedExam);
    }

    @DeleteMapping("/exams/{id}")
    public ResponseEntity<Void> deleteExam(@PathVariable("id") long id) {
        this.ExamService.handleDeleteExam(id);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/exams/{id}")
    @ApiMessage("fetch Exam by id")
    public ResponseEntity<Exam> fetchExamById(@PathVariable("id") long id) {
        Optional<Exam> cOptional = this.ExamService.findById(id);
        return ResponseEntity.ok().body(cOptional.get());
    }
}
