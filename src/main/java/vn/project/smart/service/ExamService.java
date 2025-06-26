package vn.project.smart.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import vn.project.smart.domain.Exam;
import vn.project.smart.domain.response.ResultPaginationDTO;
import vn.project.smart.repository.ExamRepository;
import vn.project.smart.repository.UserRepository;

@Service
public class ExamService {

    private final ExamRepository ExamRepository;

    public ExamService(
            ExamRepository ExamRepository,
            UserRepository userRepository) {
        this.ExamRepository = ExamRepository;

    }

    public Exam handleCreateExam(Exam c) {
        return this.ExamRepository.save(c);
    }

    public ResultPaginationDTO handleGetExam(Specification<Exam> spec, Pageable pageable) {
        Page<Exam> pExam = this.ExamRepository.findAll(spec, pageable);
        ResultPaginationDTO rs = new ResultPaginationDTO();
        ResultPaginationDTO.Meta mt = new ResultPaginationDTO.Meta();

        mt.setPage(pageable.getPageNumber() + 1);
        mt.setPageSize(pageable.getPageSize());

        mt.setPages(pExam.getTotalPages());
        mt.setTotal(pExam.getTotalElements());

        rs.setMeta(mt);
        rs.setResult(pExam.getContent());
        return rs;
    }

    public Exam handleUpdateExam(Exam c) {
        Optional<Exam> examOptional = this.ExamRepository.findById(c.getId());
        if (examOptional.isPresent()) {
            Exam currentExam = examOptional.get();
            currentExam.setLogo(c.getLogo());
            currentExam.setName(c.getName());
            currentExam.setDescription(c.getDescription());
            return this.ExamRepository.save(currentExam);
        }
        return null;
    }

    public void handleDeleteExam(long id) {
        this.ExamRepository.deleteById(id);
    }

    public Optional<Exam> findById(long id) {
        return this.ExamRepository.findById(id);
    }
}
