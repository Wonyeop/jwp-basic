#### 1. Tomcat 서버를 시작할 때 웹 애플리케이션이 초기화하는 과정을 설명하라.
*
servlet container의 초기화는 ContextLoaderListener에서 담당한다.
ContextLoaderListener 클래스에서는 DB의 초기화를 구현하고 있다.
ResourceDatabasePopulator(RDP)를 통해서 jwp.sql읽어서 
DatabasePopulatorUtiles의 execute method를 사용하여 DB의 초기화를 진행한다.

DispatcherServlet에서 loadOnStartUp설정이 1로 되어있어 서버 실행시에 초기화와 자원 할당을 수행한다
양수이므로 서버 실행시에 수행되며, 1은 우선순위에 해당한다
init method를 수행하여 RequestMapping class를 생성하고 초기화를 수행한다.
servlet과 url pattern 간의 mapping을 수행한다.

#### 2. Tomcat 서버를 시작한 후 http://localhost:8080으로 접근시 호출 순서 및 흐름을 설명하라.
* 
1.서버 시작시 servlet container의 초기화를 위해 ContextLoaderListener가 수행이 된다
여기에서 DB의 초기화가 이루어지며 질문목록이 구성이 된다
2. DispatcherServlet의 loadOnStartUp의 값이 양수이므로 init method가 수행이 되며
Request url pattern과 servlet간의 mapping이 초기화가 이루어 진다
3.http://localhost:8080로 요청이 들어오게 되면 /의 url pattern으로 인식하여 HomeController가 수행이 된다
HomeController는 questionDao.findAll()를 통해 질문 목록을 읽어와 index.jsp에 questions 객체를 전달하고 
index.jsp에서 해당 목록을 하나씩 출력하게 된다.

#### 7. next.web.qna package의 ShowController는 멀티 쓰레드 상황에서 문제가 발생하는 이유에 대해 설명하라.
* 
