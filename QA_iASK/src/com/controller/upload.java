package com.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.List;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.DAO.DataAceesLayer;
import com.model.UserDetails;

/**
 * Servlet implementation class upload
 */
@WebServlet("/upload1")
public class upload extends HttpServlet {
	

	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public upload() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		    	HttpSession session=request.getSession();
		    	String uid=session.getAttribute("uid").toString();
//		    	System.out.println("uid is===============>  "+uid);
		        //process only if its multipart content
		        if(ServletFileUpload.isMultipartContent(request)){
		            try {
		                List<FileItem> multiparts = new ServletFileUpload(
		                                         new DiskFileItemFactory()).parseRequest(request);
		                for(FileItem item : multiparts){
		                    if(!item.isFormField()){
		                        String name = new File(item.getName()).getName();
		                        //item.write( new File(UPLOAD_DIRECTORY + File.separator + name));
		                       InputStream is= item.getInputStream();
		                       Boolean flag=DataAceesLayer.checkImg1(Integer.parseInt(uid));
		                       DataAceesLayer.uploadImage(is, Integer.parseInt(uid));
		                    }
		                }
		               //File uploaded successfully
		               request.setAttribute("message", "File Uploaded Successfully");
		            } catch (Exception ex) {
		               request.setAttribute("message", "File Upload Failed due to " + ex);
		            }          
		        }else{
		            request.setAttribute("message",
		                                 "Sorry this Servlet only handles file upload request");
		        }
		        request.getRequestDispatcher("myProfile.jsp").forward(request, response);
		    }
	}

