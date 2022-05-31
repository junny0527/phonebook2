package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@WebServlet("/pbc")
public class PhoneController extends HttpServlet {
	// 필드
	private static final long serialVersionUID = 1L;

	// 생성자(기본생성자 사용 default)
	// 메소드 - gs

	// 메소드 - 일반
	// get방식으로 요청 시 호출 메소드
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		//포스트 방식일때 한글깨짐 방지
		request.setCharacterEncoding("UTF-8");
		
		// action 파라미터꺼내기
		String action = request.getParameter("action");
		System.out.println(action);
		
		if("list".equals(action)) {
			// 다오를통해서 데이터 가져오기
			PhoneDao phoneDao = new PhoneDao();
			List<PersonVo> phoneList = phoneDao.getPersonList();
			System.out.println(phoneList);
			
			//request에 데이터 추가
			request.setAttribute("pList", phoneList);
			
			// 데이터 + html --> jsp로 실행
			RequestDispatcher rd = request.getRequestDispatcher("/list.jsp");
			rd.forward(request, response);
		
		}else if("writeForm".equals(action)) {
			
			//포워드
			RequestDispatcher rd = request.getRequestDispatcher("/writeForm.jsp");
			rd.forward(request, response);
		}else if("write".equals(action)){
			
			//파라미터에서 값꺼내기
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			//personVo 꺼내오기
			PersonVo personVo = new PersonVo(name, hp, company);
			System.out.println(personVo);
			
			//PhoneDao.personinsert 를 통해값 저장
			PhoneDao phoneDao = new PhoneDao();
			phoneDao.personInsert(personVo);
			
			//리다이어렉트 list
			response.sendRedirect("/phonebook2/pbc?action=list");
			
		}else if("delete".equals(action)){
			
			int id = Integer.parseInt(request.getParameter("id"));
			
			 PhoneDao phoneDao = new PhoneDao();
			 PersonVo personVo = new PersonVo(id);
			 phoneDao.personDelete(personVo);
			 
			 response.sendRedirect("./pbc?action=list");
			
		}else if ("updateForm".equals(action)) {
	         PhoneDao phoneDao = new PhoneDao();
	         int id = Integer.parseInt(request.getParameter("id"));
	         PersonVo personVo = phoneDao.getPerson(id);
	         
	         request.setAttribute("personVo", personVo);
	         
	         RequestDispatcher rd = request.getRequestDispatcher("/updateForm.jsp");
	         rd.forward(request, response);
	      }else if ("update".equals(action)) {
	         int id = Integer.parseInt(request.getParameter("id"));
	         String name = request.getParameter("name");
	         String hp = request.getParameter("hp");
	         String company = request.getParameter("company");
	         
	         PhoneDao phoneDao = new PhoneDao();
	         PersonVo personVo = new PersonVo(id,name,hp,company);
	         
	         phoneDao.personUpdate(personVo);
	         
	         response.sendRedirect("./pbc?action=list");
	      }else {
			System.out.println("action 파라미터없음");
		}

	}

	// post방식으로 요청 시 호출 메소드
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
}
