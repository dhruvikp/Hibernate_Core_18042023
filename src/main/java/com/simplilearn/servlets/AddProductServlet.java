package com.simplilearn.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.simplilearn.entity.EProduct;
import com.simplilearn.util.HibernateUtil;

/**
 * Servlet implementation class AddProductServlet
 */
@WebServlet("/add-product")
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("add-product.html").include(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Preparing Object 
		String name = request.getParameter("name");
		Double price = Double.valueOf(request.getParameter("price"));
		
		EProduct product = new EProduct();
		product.setName(name);
		product.setPrice(price);
		
		// STEP 1: Gets Session Factory object
		SessionFactory sf = HibernateUtil.buildSessionFactory();
		
		// STEP 2: Open Session
		Session session = sf.openSession();
		
		// STEP 3:  Save entity
		Transaction tx = session.beginTransaction();
		session.save(product);
		tx.commit();
		
		// STEP 4: Close Session
		session.close();
		
		response.sendRedirect("read-product");
	}

}
