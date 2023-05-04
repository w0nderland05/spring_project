package org.study.models.cs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.study.commons.validators.ReportNotFoundException;
import org.study.controllers.admin.cs.CsConfig;
import org.study.entities.Report;
import org.study.repositories.cs.ReportRepository;

import java.util.List;

import static org.springframework.data.domain.Sort.Order.desc;

@Service
public class ReportListService {

    @Autowired
    private ReportRepository repository;

    private CsConfig toConfig(Report report) {

        return CsConfig.builder()
                .division(report.getDivision().toString())
                .code(report.getCode())
                .detail(report.getDetail())
                .status(report.getStatus().toString())
                .process(report.getProcess())
                .build();
    }

    public List<CsConfig> gets() { // 신고 목록 전체를 조회
        List<Report> reports = repository.findAll(Sort.by(desc("createdAt")));
        if(reports == null && reports.isEmpty()){
            throw new ReportNotFoundException();
        }
        List<CsConfig> csConfigList = reports.stream().map(this::toConfig).toList();
        return csConfigList;

    }

    public CsConfig get(Long code) { // Code를 통해서 하나의 목록만 조회
        if (code == null ) {
            throw new ReportNotFoundException();
        }
        Report report = repository.findById(code).orElseThrow(ReportNotFoundException::new);

        CsConfig config = CsConfig.builder()
                .division(report.getDivision().toString())
                .code(report.getCode())
                .detail(report.getDetail())
                .status(report.getStatus().toString())
                .process(report.getProcess())
                .build();

        return config;
    }


}
