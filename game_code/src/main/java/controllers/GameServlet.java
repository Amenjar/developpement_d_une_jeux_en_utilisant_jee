package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.Highlighter.Highlight;

import model.Game;


@WebServlet({ "/GameServlet", "/doGuess" })
public class GameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GameServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Game game = (Game) session.getAttribute("game");
		Object  hs =  session.getAttribute("highScore");
		long highScore;
		if (hs==null) {highScore=0;}
		else {
			highScore=(long) hs;
		}
		if (game==null) {
			game=new Game();
		}
		if (highScore==0L) {
			highScore=0;
		}
		String x=request.getParameter("guess");
		if (x==null)x="0";
		int guess = Integer.parseInt(x);
		game.addGuesses(guess);
		int target = game.getTarget();
	
	
		
		String url = "/index.jsp";
		
		if (guess == target) {
			game.setWin();
			request.setAttribute("game", game);
			session.removeAttribute("game");
			if (game.getScore()>highScore) {
				session.setAttribute("highScore", game.getScore());
				request.setAttribute("highScore", highScore);
				System.out.println(highScore);
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}
		else {
			session.setAttribute("game", game);
			request.setAttribute("game", game);
			request.setAttribute("highScore", highScore);
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);			
		}
	}

}
