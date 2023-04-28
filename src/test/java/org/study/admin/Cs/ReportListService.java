package org.study.admin.Cs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.study.commons.constants.ReportStatus;
import org.study.commons.validators.ReportNotFoundException;
import org.study.controllers.admin.board.BoardConfig;
import org.study.entities.Report;
import org.study.entities.board.Board;
import org.study.repositories.ReportRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.data.domain.Sort.Order.desc;

@Service
public class ReportListService {

    @Autowired
    private ReportRepository repository;

    public void reportList(CsConfig config) {
        reportList(config, null);
    }

    public void reportList(CsConfig config, Errors errors) {
        reportList(config, null);
    }

    public List<CsConfig> gets() { // 신고 목록 전체를 조회
        List<Report> reports = repository.findAll(Sort.by(desc("createdAt")));
        if(reports ==null && reports.isEmpty()){
            throw new ReportNotFoundException("신고 목록을 찾지 못했습니다.");
        }
        List<CsConfig> csConfigList = reports.stream().map(this::toConfig).toList();
        return csConfigList;

    }

    public CsConfig get(Long code) { // Code를 통해서 하나의 목록만 조회
        Report report = repository.findById(code).get();
        CsConfig config = CsConfig.builder()
                .division(report.getDivision())
                .code(report.getCode())
                .detail(report.getDetail())
                .status(report.getStatus().toString())
                .process(report.getProcess())
                .build();

        return config;
    }

    private CsConfig toConfig(Report report) {

        return CsConfig.builder()
                .division(report.getDivision())
                .code(report.getCode())
                .detail(report.getDetail())
                .status(report.getStatus().toString())
                .process(report.getProcess())
                .build();


    }
}
