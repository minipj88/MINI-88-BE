## GIT 협의  
### **1️⃣브랜치 전략**
<details>
<summary> </summary>
<div markdown="1">
  
- 브렌치 네임 선택
  - {feat}-{task}-{no}-{nickName}
  - {feat}-{nickName}
- 작업물 서버에 배포
  </div>
</details>

### **2️⃣ 배포 전략**
  <details>
<summary> </summary>
<div markdown="1">
  
- aws 클라우드 서버에 git action ci/cd 배포 
  </div>
</details>

### **3️⃣커밋메세지 작성 규칙**
<details>
<summary> </summary>
<div markdown="1">
  
- `#이슈번호 conf: 메시지 - 내용`        
- 컨벤션
    - `docs` : 문서 작업 (README.md)
    - `feat` : 새로운 기능 구현
        - 최소 단위
    - `conf` : 설정 파일 관련
        - 패키지, 라이브러리 추가
    - `asset` : 이미지 소스 추가
    - `design` : css 작업
    - `rename` : 파일 명 | 디렉토리 변경
    - `remove` : 파일 삭제
    - `chore` :  주석 변경/삭제😎
    
    ---
    
    - `refactor` : 코드 리팩토링 (성능, 가독성)
        - 의미나 동작에 영향을 주지 않는 상태에서 가독성, 재사용성 또는 구조를 개선하기 위해 현재 코드를 재작성하는 것
    - `fix` : 버그를 고친 경우
    - `hotfix` : 치명적인 버그 수정
        - 의논 후 담당 1명을 정해서 처리
        - 의도치 않은 에러 수정
    - `!BreakingChange` : 커다란 API의 변경
    - `test` : 테스트 관련
  </div>
</details>
