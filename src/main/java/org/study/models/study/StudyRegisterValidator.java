package org.study.models.study;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.study.commons.constants.RegionType;
import org.study.commons.validators.BadRequestException;
import org.study.commons.validators.CommonException;
import org.study.commons.validators.RequiredCheckValidator;
import org.study.commons.validators.ServiceValidator;
import org.study.controllers.admin.study.StudyConfig;
import org.study.repositories.StudyRepository;

@Component
@RequiredArgsConstructor
public class StudyRegisterValidator implements ServiceValidator<StudyConfig>, RequiredCheckValidator {

    private final StudyRepository studyRepository;

    @Override
    public void check(StudyConfig config) {
        /** StudyConfig null일 경우 예외발생*/
        nullCheck(config, new BadRequestException("잘못된 접근입니다."));

        /**필수항목 체크*/
        requiredCheck(config.getStudyNm(), new BadRequestException("스터디명을 입력하세요."));
        requiredCheck(config.getCategory(), new BadRequestException("카테고리를 선택해주세요."));
        requiredCheck(config.getNumOfWeek(), new BadRequestException("스터디 주당횟수를 선택해주세요."));
        requiredCheck(config.getSimpleIntro(), new BadRequestException("한줄 소개글을 입력해주세요."));
        requiredCheck(config.getIntroduction(), new BadRequestException("소개글을 작성해주세요."));


        nullCheck(config.getMaxMember(), new BadRequestException("신청최대인원수를 체크해주세요."));
        nullCheck(config.getRegionType(), new BadRequestException("스터디 지역타입을 선택해주세요."));
        nullCheck(config.getStudyCode(), new BadRequestException("올바르지 않은 스터디코드 입니다."));


        /**신청최대 인원수 -0이상 1000이하  (0- 무제한/ 숫자 - 제한 있음 )*/
       Long maxNumber = config.getMaxMember();
        if(maxNumber!=null && maxNumber<0 && maxNumber>1000){
            throw new BadRequestException("신청최대인원수는 0이상 1000이하로 입력해주세요.");
        }

//        /**지역 -1) 온라인
//         *      2) 오프라인 선택시 시/도, 군/구 설정
//         */
//        RegionType regionType= config.getRegionType();
//        if(regionType==RegionType.OFFLINE){
//
//        }

        /**sutdyCode 중복 여부 체크*/
        if(studyRepository.exists(config.getStudyCode())){
            throw new DuplicationStudyCdException();
        }

    }


    /**
     * 스터디 개설을 신청할 때 필요한 유효성 검사
     *
     * 필수 항목 채크
     * 1. 가입된 회원이 개설을 신청하는 것인지 체크
     * 2. 신청 최대인원수 체크
     * 3. 스터디 주당 횟수 체크 ( 관리자가 어떻게 할 것인지에 따라 다름)
     * 4. 온라인/오프라인에 따른 지역 선택 체크
     *      1) 온라인
     *      2) 오프라인 선택시 시/도, 군/구 설정 ->
     */

}
