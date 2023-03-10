## how_are_you? 

<img width="75%" src="https://user-images.githubusercontent.com/97998547/214502225-c00f0d99-a821-485e-a216-e194f52eb48a.png"/>





How are you?는 오늘 어때? , 안녕? 등의 의미로 널리 알려져 있다.

우리 나라 사람들의 공통 대답은 I’m fine , 괜찮아, 좋아, 안녕. 

정말 fine한걸까? 안녕 한걸까?. 그렇다면 왜 ? 우울증의 지수는 점점 높아져 가는 걸까?

치열한 경쟁 사회에서 사람들은 더욱 스마트해지고 있으면서도, 점차 자기 자신을 잊고 살아갈 때가 많다. 
타인에 대해서는 잘 이해하면서도 정작 자기 자신에게는 야박하게 구는 사람들이 많다.

SNS에 투자하는 시간도 좋지만, 하루에 1개의 질문을 통해 스스로를 생각해 볼 수 있는 시간을 갖게 되었으면 좋겠다는 마음에 제작하게 되었다.


## 기술스택

1. Frontend
   + Javascript, JQuery, HTML, w3shools
  

2. Backend
   + Java(v11), SpringBoot(v2.7.7), IntelliJ IDEA, Querydsl-jpa, Gradle, Junit4
  
3. DB
   + MariaDB






## ERD
member, question, member_question은 핵심 기능이다. 
order과 comment는 추후에 추가 할 예정이다. 

<img width="75%" src="https://user-images.githubusercontent.com/97998547/214490005-0db3c9f5-fac7-4102-b66b-0585030d4e4c.png"/>

에이쿼리툴 사용 : [AQuery](https://aquerytool.com/)


1. member
   + 회원 정보가 저장되어 있는 테이블이다.
   + member 테이블과 member_question 테이블은 일대다 관계이다. 

2. question
   + 랜덤질문 리스트가 저장되어 있는 테이블이다.
   + question 테이블과 member_question 테이블은 일대다 관계이다. 
   

3. member_question
   + 하루에 1번 생성된 랜덤질문이 저장되어 있는 테이블이다.
   + member_question 테이블과 question 테이블은 다대일 관계이다. 
   + member_question과 member 테이블은 다대일 관계이다.

4. item_store(추후 업데이트 예정)
   + 아임포트 api를 사용하여 결제시스템 구현 예정
   + count ->  date로 
  

5. comment(추후 업데이트 예정) 
   + 댓글이 저장되어 있는 테이블이다.




## 사용자 기능정리

1. 회원가입/로그인/로그아웃
   + 아이디, 이름, 생년월일, 이메일을 입력하여 회원가입한다.
   + 아이디, 비밀번호 입력 후 로그인한다.(로그인 후 로그인용 네비바로 변경된다.)
   + 회원이 아니라면 가입할 수 있다. 
   + 로그인 창을 닫을 수 있다.
   + 로그아웃시 홈 화면으로 돌아간다.


2. 마이페이지
   + 회원가입시 입력한 내용을 조회할 수 있다.
   + 비밀번호와 이메일을 변경할 수 있다.
   + 아이템 구매 내역을 조회할 수 있다.
   + 질문에 답변한 내용을 검색조건에 따라 조회할 수 있다. 
   + 심리검사 결과를 조회할 수 있다.

3. 오늘의 질문
   + 오늘의 질문이 랜덤으로 조회된다.
   + 질문에 답변한 내용이 저장된다.
   + 질문에 답변한 내용을 검색조건에 따라 조회할 수 있다.
   + 답변하고 3개월이 지나면 내용을 조회할 수 없다.
   + 심리검사 결과를 조회할 수 있다.

4. 아이템 상점(추후 업데이트 예정)
   + 선택한 아이템을 구매할 수 있다.
   + 아이템 구매전 확인용 메세지를 띄운다.
   + 3개월이 지난 답변을 조회 할 수 있다. 
   + 개당 아이템일 경우에만 수량 설정 가능하다.
 



  



