package org.study.admin.Cs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.study.repositories.ReportRepository;
import org.study.repositories.UserRepository;

@Service
public class CsReportService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReportRepository reportRepository;



}
