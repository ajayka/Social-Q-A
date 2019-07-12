package com.controller;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.jdbc.JDBCCategoryDataset;

/**
 * Servlet implementation class PricisionAndRecall
 */
@WebServlet("/PricisionAndRecall")
public class PricisionAndRecall extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		final String DB_URL = "jdbc:mysql://localhost/iask?autoReconnect=true&useSSL=false";
		final String USER = "root";
		final String PASS = "";
		if (request.getParameter("Accuracy").equalsIgnoreCase("Pricision")) {
			response.setContentType("image/png");

			String query = "select id,pricision from PricisionAndRecall";

			JDBCCategoryDataset dataset = null;
			try {
				dataset = new JDBCCategoryDataset(DB_URL, JDBC_DRIVER, USER, PASS);
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}

			try {
				dataset.executeQuery(query);
				// System.out.println(query);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			final JFreeChart chart = ChartFactory.createBarChart("Pricision Graph", "//System running count",
					"Pricision", dataset, PlotOrientation.VERTICAL, true, true, false);

			final CategoryPlot plot = (CategoryPlot) chart.getPlot();

			plot.setBackgroundPaint(Color.white);

			try

			{

				final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());

				File file2 = new File("C://OxygenWorkspace//SocialQA//WebContent//images//pricision.png");

				ChartUtilities.saveChartAsPNG(file2, chart, 1000, 800, info);
				RequestDispatcher rd = request.getRequestDispatcher("Pricision.jsp");
				rd.forward(request, response);

			} catch (Exception e) {

				// System.out.println(e);

			}
		} else if (request.getParameter("Accuracy").equalsIgnoreCase("Recall")) {

			response.setContentType("image/png");

			String query = "select id,recall from PricisionAndRecall";

			JDBCCategoryDataset dataset = null;
			try {
				dataset = new JDBCCategoryDataset(DB_URL, JDBC_DRIVER, USER, PASS);
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}

			try {
				dataset.executeQuery(query);
				// System.out.println(query);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			final JFreeChart chart = ChartFactory.createBarChart("Racall Graph", "//System running count", "Racall",
					dataset, PlotOrientation.VERTICAL, true, true, false);

			final CategoryPlot plot = (CategoryPlot) chart.getPlot();

			plot.setBackgroundPaint(Color.white);

			try

			{

				final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());

				File file2 = new File("C://OxygenWorkspace//SocialQA//WebContent//images//Racall.png");

				ChartUtilities.saveChartAsPNG(file2, chart, 1000, 800, info);
				RequestDispatcher rd = request.getRequestDispatcher("Recall.jsp");
				rd.forward(request, response);

			} catch (Exception e) {

				// System.out.println(e);

			}
		} else if (request.getParameter("Accuracy").equalsIgnoreCase("Time")) {
			response.setContentType("image/png");

			String query = "select * from TotalTime";

			JDBCCategoryDataset dataset = null;
			try {
				dataset = new JDBCCategoryDataset(DB_URL, JDBC_DRIVER, USER, PASS);
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}

			try {
				dataset.executeQuery(query);
				// System.out.println(query);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			final JFreeChart chart = ChartFactory.createLineChart("Time taken ", "Number Question Per request",
					"Running time", dataset, PlotOrientation.VERTICAL, true, true, false);

			final CategoryPlot plot = (CategoryPlot) chart.getPlot();

			plot.setBackgroundPaint(Color.white);

			try

			{

				final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());

				File file2 = new File("C://OxygenWorkspace//SocialQA//WebContent//images//time_graph.png");

				ChartUtilities.saveChartAsPNG(file2, chart, 1000, 800, info);
				RequestDispatcher rd = request.getRequestDispatcher("result_graph.jsp");
				rd.forward(request, response);

			} catch (Exception e) {

				// System.out.println(e);

			}
		} else if (request.getParameter("Accuracy").equalsIgnoreCase("Answer Rating")) {
			response.setContentType("image/png");

			String query = "select aid,rating from answer";

			JDBCCategoryDataset dataset = null;
			try {
				dataset = new JDBCCategoryDataset(DB_URL, JDBC_DRIVER, USER, PASS);
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}

			try {
				dataset.executeQuery(query);
				// System.out.println(query);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			final JFreeChart chart = ChartFactory.createLineChart("Answer Avgrage", "Answer id", "Running time",
					dataset, PlotOrientation.VERTICAL, true, true, false);

			final CategoryPlot plot = (CategoryPlot) chart.getPlot();

			plot.setBackgroundPaint(Color.white);
			try {
				final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
				File file2 = new File("C://OxygenWorkspace//SocialQA//WebContent//images//answerAvg.png");
				ChartUtilities.saveChartAsPNG(file2, chart, 1000, 800, info);
				RequestDispatcher rd = request.getRequestDispatcher("answerAvg.jsp");
				rd.forward(request, response);

			} catch (Exception e) {
				// System.out.println(e);
			} // User Question Ratio
		} else if (request.getParameter("Accuracy").equalsIgnoreCase("User Question Ratio")) {
			response.setContentType("image/png");

			String query = "select count(qid) as qid,uid from `vw_questionanswer` group by uid";

			JDBCCategoryDataset dataset = null;
			try {
				dataset = new JDBCCategoryDataset(DB_URL, JDBC_DRIVER, USER, PASS);
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}

			try {
				dataset.executeQuery(query);
				// System.out.println(query);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			final JFreeChart chart = ChartFactory.createBarChart("User Question Ratio", "//Users", "Question Count",
					dataset, PlotOrientation.VERTICAL, true, true, false);

			final CategoryPlot plot = (CategoryPlot) chart.getPlot();

			plot.setBackgroundPaint(Color.white);

			try

			{

				final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());

				File file2 = new File("C://OxygenWorkspace//SocialQA//WebContent//images//userQuestion.png");

				ChartUtilities.saveChartAsPNG(file2, chart, 1000, 800, info);
				RequestDispatcher rd = request.getRequestDispatcher("userQuestion.jsp");
				rd.forward(request, response);

			} catch (Exception e) {

				// System.out.println(e);

			}
		} else {
			response.setContentType("image/png");

			String query = "select count(uid) as uid,cid from `vw_usercommunityinfomation` group by cid";

			JDBCCategoryDataset dataset = null;
			try {
				dataset = new JDBCCategoryDataset(DB_URL, JDBC_DRIVER, USER, PASS);
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}

			try {
				dataset.executeQuery(query);
				// System.out.println(query);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			final JFreeChart chart = ChartFactory.createBarChart("Community information", "//Community", "User Count",
					dataset, PlotOrientation.VERTICAL, true, true, false);

			final CategoryPlot plot = (CategoryPlot) chart.getPlot();

			plot.setBackgroundPaint(Color.white);

			try

			{

				final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());

				File file2 = new File("C://OxygenWorkspace//SocialQA//WebContent//images//userCommunity.png");

				ChartUtilities.saveChartAsPNG(file2, chart, 1000, 800, info);
				RequestDispatcher rd = request.getRequestDispatcher("userCommunity.jsp");
				rd.forward(request, response);

			} catch (Exception e) {

				// System.out.println(e);

			}
		}
	}

}
