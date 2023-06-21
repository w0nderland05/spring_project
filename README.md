***
# 스터디 매칭 사이트
:ab: 사용언어 : Java / HTML /CSS /JavaScript <br>
:computer: 개발환경: SpringBoot<br>
:floppy_disk: 데이터베이스: MySQL<br>

##### 프로젝트 목표
🌟 대표기능: 스터디 개설 신청시 관리자페이지에서 승인하고, 승인된 스터디는 '스터디함께해요 페이지에서 검색가능<br>

① 관리자페이지 (사이트 관리 )<br>
② 회원가입 , 로그인<br>
③ 스터디 개설 (관리자 승인시 개설)<br>
④ 스터디 함께해요 (스터디 검색)<br>
⑤ 커뮤니티 (등록, 수정, 댓글)<br>
⑥ 마이페이지 (개설 및 가입된 스터디)<br>

***
## 사이트맵
![사이트 맵](https://github.com/82everywin/spring_project/blob/study/%EC%82%AC%EC%9D%B4%ED%8A%B8%20%EB%A7%B5.jpg?raw=true)

***
## ERD 설계하기 
![image](https://github.com/82everywin/spring_project/assets/109841880/b5a2a351-84f2-454d-9512-c2d80e00480e)

[ERD 설계.pdf](https://github.com/82everywin/spring_project/files/11442031/ERD.pdf)

***
## 기능 설계, 화면 구상 
https://www.notion.so/7350235726844e8888cc2718200452e3?pvs=4

***
## 구현된 스터디기능 이미지 및 시현 영상 
### 어드민페이지
* 스터디관리자 페이지
![스터디관리자 페이지](https://github.com/w0nderland05/spring_project/assets/111275210/d4885a6d-75f2-4426-b7d1-94a8ead9cf3e)

* 스터디 관리자 페이지 기능 시현
![스터디관리자 페이지 기능시현 영상](https://github.com/w0nderland05/spring_project/assets/111275210/d5edb3b2-b7f5-471b-b1fc-0df39ec8bff6)

### 사용자페이지
* 스터디만들기
![스터디만들기](https://github.com/w0nderland05/spring_project/assets/111275210/7fb11ee2-086c-4daf-aa26-84a1adf425cf)

* 스터디 함께해요
![스터디 함께해요](https://github.com/w0nderland05/spring_project/assets/111275210/61aad145-3082-4a26-8e98-0a441d32ce36)

* 개설된 스터디 상세페이지 
![개설된 스터디 상세페이지](https://github.com/w0nderland05/spring_project/assets/111275210/39ecc4f6-4513-4955-a938-e2ac178655d2)

* 스터디 검색 기능시현
![스터디 검색 기능시현](https://github.com/w0nderland05/spring_project/assets/111275210/4288cf38-b967-40cc-ba04-c899256aa07b)

***
## 느낀 점
개발 과정 중 TDD 방식으로 기능을 구현했던 것이 가장 기억에 남습니다. 테스트 케이스를 작성하는 과정에서 어떻게 이 기능을 통과 시킬 지에 대한 집중을 할 수 있었고, 테스트 케이스를 세분화함에 따라 오류 발생 시 문제점을 빠르게 찾고 해결하는 데 도움이 되었습니다.
또한 스터디 관련 중심 기능들을 담당하다 보니, 다른 영역과 연관된 부분들이 많았고, 연관성을 고려하여 설계하는 과정에서 이 프로젝트의 전체적인 흐름을 읽을 수 있었습니다. 구체적인 예로, 개설 신청 시 회원 정보 가져오기, 권한 검증에서는 로그인 여부 등에 대한 파악이 필요했고, 관리자에서 승인 처리를 한 이후, 해당 페이지가 공부 검색 페이지에 노출됨과 함께 회원의 마이페이지에서도 확인할 수 있도록 해야 했습니다.
구현한 기능 중에 기억에 남는 것은 검색 기능입니다. Boolean Builder를 통해 검색 조건을 구현하고, 페이지 국가를 통해 페이지 구간을 설정하였는데 평소 자주 쓰던 검색 기능을 직접 만들었을 때 큰 성취감을 느꼈습니다.




