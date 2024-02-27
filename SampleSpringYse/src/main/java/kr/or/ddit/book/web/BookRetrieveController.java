package kr.or.ddit.book.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.book.service.BookService;

//Book 목록, 상세보기를 가용할 페이지
@Controller
@RequestMapping("/book")
public class BookRetrieveController {
	
	@Autowired
	private BookService bookService;
	
	//Controller 특성(페이지 정보 return)에 맞춰서 return 타입 지정
	@RequestMapping(value="/list.do")
	// ModelAndView : 데이터, 페이지 처리를 같이 해준다
	//파라미터로 list.jsp에서 검색으로 온 데이터를 받아옴
	public ModelAndView list(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		List<Map<String, Object>> list = bookService.selectBookList(map);
		mav.addObject("bookList",list);
		// setViewName : return 할 페이지 정보를 설정
		// /WEB-INF/views/book/list.jsp 그동안 이렇게 썼지만, (root:webapp)
		// ViewResolver(servlet-context.xml)가 prefix, suffix를 잡아줄거라서 
		//jsp페이지의 위치 경로를 만들어줌
		mav.setViewName("book/list");
		return mav;
	}
	
	@RequestMapping(value="/detail.do", method = RequestMethod.GET)
	public ModelAndView detail(@RequestParam Map<String, Object> map) {
		Map<String, Object> detailMap = bookService.selectBook(map);
		
		//ModelAndView 객체 mav에 뷰로 전달할 데이터를 담는다.
		//book이라는 키의 이름으로 쿼리의 결과를 담았다.(bookId에 해당하는 BOOK 정보)
		ModelAndView mav = new ModelAndView();
		mav.addObject("book", detailMap);
		String bookId = map.get("bookId").toString();
		mav.addObject("bookId", bookId);
		mav.setViewName("book/detail");
		return mav;
		
	}
}
