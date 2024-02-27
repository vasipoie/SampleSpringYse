package kr.or.ddit.book.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.book.service.BookService;

/*
 * @Controlelr 어노테이션이 있는 클래스는 스프링이 브라우저의 요청(request)을
 * 받아들이는 컨트롤러라고 인지해서 자바 빈(Java Bean)으로 등록해서 관리한다.
 * 
 * Bean : 서버를 run 하는 순간 메모리에 인스턴스를 올리는 과정
 * 어노테이션을 붙여주면 스프링 프레임워크에서 객체화해서 메모리에 올려줌
 * 
 */
@Controller
@RequestMapping("/book")
public class BookInsertController {
	
	//BookService 인터페이스=껍데기 호출
	//@Autowired 또는 @Inject 사용해서 객체를 불러와서 메소드를 사용할 수 있도록 해준다
	//구현체 클래스에 들어있는 진짜 알맹이가 필요하니까
	//참조받아서 만든 구현체 클래스 객체 정보가 인터페이스에 주입됨(의존성 주입)
	//->이걸 프레임워크가 해줌
	/*
	 * 서비스를 호출하기 위해 BookServie를 의존성을 주입한다.
	 * 의존성 주입을 위한 결합도 낮추기
	 */
	@Autowired
	private BookService bookService;
	
	/*
	 * @RequestMapping
	 * - 요청 URL을 어떤 메소드가 처리할 지 여부를 결정
	 * 
	 * method 속성은 http 요청 메소드를 의미한다.
	 * 일반적인 웹 페이지 개발에서 
	 * GET 메소드는 데이터를 변경하지 않는 경우에,
	 * POST 메소드는 데이터가 변경될 경우 사용됩니다.
	 * 
	 * ModelAndView는 
	 * 컨트롤러가 반환할 데이터를 담당하는 모습(Model)과
	 * 화면을 담당하는 뷰(View)의 경로를 합쳐놓은 객체입니다.
	 * ModelAndView의 생성자에 문자열 타입 파라미터가 입력되면 뷰의 경로라고 간주합니다.
	 * 
	 * 뷰의 경로를 'book/form'과 같은 형태로 전달하는 이유는 
	 * 요청(request)에 해당하는 url의 mapping 되는 화면의 경로 값을 
	 * ViewResolver라는 녀석이 제일 먼저 받습니다.
	 * 받아서 suffix, prefix 속성에 의해서 앞에는 '/WEB-INF/views/'를 붙이고
	 * 뒤에는 '.jsp'를 붙여 최종 위치에 해당하는 jsp 파일을 찾아줍니다.
	 */
	
	
	//등록 페이지를 보기 위한 도착 페이지
	@RequestMapping(value="/form.do", method=RequestMethod.GET)
	public ModelAndView bookForm() {
		// 1.setViewName 이용해서 페이지 정보 return
		// 2.생성자를 이용해서 파라미터를 넣으면 페이지 정보를 return
		return new ModelAndView("book/form");
	}
	
	
	//등록 기능을 처리할 컨트롤러 메소드
	//일단 vo없이, map 컬렉션을 받아서 운용
	//등록함과 동시에 최신 등록한 sequence값을 가져와서 상세보기로 넘어가는 프로세스
	
	//form.jsp에 action이 비어져있는데 그럴 경우, 자기자신 컨트롤러로 다시 넘어오고
	//insertBook메소드를 타고 @RequestMapping method 방식에 따라 
	//POST로 데이터를 받아와서 처리
	/*
	 * 데이터의 변경이 일어나므로 HTTP 메소드는 POST 방식으로 처리합니다
	 * @RequestMapping의 각 속성은 2개이상 사용해야 할 때에는 무조건 명시해줘야한다.
	 * 하나만 쓸 때 default는 value
	 * 
	 * Map 타입의 경우는 @RequestParam 을 붙여야만 HTTP 파라미터의 값을 map안에 바인딩 할 수 있습니다!
	 */
	@RequestMapping(value="/form.do", method = RequestMethod.POST)
	public ModelAndView insertBook(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		
		String bookId = bookService.insertBook(map);
		if(bookId == null) {
			//redirect: 무조건 붙여야함
			//데이터 입력이 실패할 경우 다시 데이터를 입력받아야 하므로 생성 화면으로 redirect한다
			//ModelAndView 객체는 .setViewName() 메소드를 통해 뷰의 경로를 지정할 수 있다.
			mav.setViewName("redirect:/book/form.do");
		}else {
			//데이터 입력이 성공하면 상세페이지로 이동한다.
			mav.setViewName("redirect:/book/detail.do?bookId="+bookId);
		}
		
		return mav;
	}
}
