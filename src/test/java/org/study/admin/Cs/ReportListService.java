package org.study.admin.Cs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
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

    public void reportList(CsConfig config, Errors errors) {
        reportList(config, null);
    }

    public List<CsConfig> gets() { // 신고 목록 전체를 조회
        List<Report> reportList = repository.findAll();
        List<CsConfig>  csConfigList = new ArrayList<>();

        for(Report report : reportList) {
            CsConfig csConfig = CsConfig.builder()
                    .division(report.getDivision())
                    .code(report.getCode())
                    .detail(report.getDetail())
                    .status(report.getStatus())
                    .process(report.getProcess())
                    .build();
            csConfigList.add(csConfig);
        }

        return csConfigList;
    }

    public CsConfig get(Long code) { // Code를 통해서 하나의 목록만 조회
        Report report = repository.findById(code).get();
        CsConfig config = CsConfig.builder()
                .division(report.getDivision())
                .code(report.getCode())
                .detail(report.getDetail())
                .status(report.getStatus())
                .process(report.getProcess())
                .build();

        return config;
    }

    public List<CsConfig> regDt() { // 최신 등록순으로 정렬 되는지 체크
        List<Report> reports = repository.findAll(Sort.by(desc("regDt")));
        List<CsConfig> configs = reports.stream().map(this::toConfig).toList();
        return configs;
    }

    private CsConfig toConfig(Report report) {

        return CsConfig.builder()
                .division(report.getDivision())
                .code(report.getCode())
                .detail(report.getDetail())
                .status(report.getStatus())
                .process(report.getProcess())
                .build();
    }
}
