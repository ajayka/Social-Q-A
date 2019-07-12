package com.DAO;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.imageio.ImageIO;

import com.model.Answers;
import com.model.Category;
import com.model.Notification;
import com.model.PriviousQuestion;
import com.model.Question;
import com.model.QuestionAndAnswerDetails;
import com.model.Rating;
import com.model.Reward;
import com.model.Subcategory;
import com.model.Suggestion;
import com.model.UserCommunityInfomation;
import com.model.UserDetails;

public class DataAceesLayer {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/iask?autoReconnect=true&useSSL=false";
	static final String USER = "root";
	static final String PASS = "";

	public static Connection makeConnection() {
		Connection con = null;
		try {
			Class.forName(JDBC_DRIVER);
			con = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;

	}

	public static int addUser(UserDetails um) {

		Connection con = makeConnection();
		int i = 0;
		try {
			String sql = "insert into user_registration(fname,lname,email,address,mob_no,password) values(?,?,?,?,?,?)";
			PreparedStatement pstm = con.prepareStatement(sql);

			pstm.setString(1, um.getFname());
			pstm.setString(2, um.getLname());
			pstm.setString(3, um.getEmail());
			pstm.setString(4, um.getAddress());
			pstm.setString(5, um.getPhnno());
			pstm.setString(6, um.getPass());

			i = pstm.executeUpdate();
			if (i == 1)
				System.out.println("Added");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	public static UserDetails getEmailPass(String email, String pass) {
		UserDetails u = new UserDetails();

		Connection con = makeConnection();
		try {
			ResultSet rs2 = con.createStatement().executeQuery(
					"select * from user_registration where email='" + email + "' and password='" + pass + "'");

			while (rs2.next()) {

				u.setId(rs2.getInt("uid"));
				u.setEmail(rs2.getString("email"));
				u.setPass(rs2.getString("password"));
				u.setFname(rs2.getString("fname"));
				u.setFname(rs2.getString("lname"));
				u.setFname(rs2.getString("mob_no"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return u;

	}
	
	public static UserDetails getEmailPass1(String email, String pass) {
		UserDetails u = new UserDetails();

		Connection con = makeConnection();
		try {
			ResultSet rs2 = con.createStatement().executeQuery(
					"select * from user_registration where email='" + email +"'");

			while (rs2.next()) {

				u.setId(rs2.getInt("uid"));
				u.setEmail(rs2.getString("email"));
				u.setPass(rs2.getString("password"));
				u.setFname(rs2.getString("fname"));
				u.setFname(rs2.getString("lname"));
				u.setFname(rs2.getString("mob_no"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return u;

	}
	
	public static UserDetails getEmailPass2(String email) {
		UserDetails u = new UserDetails();

		Connection con = makeConnection();
		try {
			ResultSet rs2 = con.createStatement().executeQuery(
					"select * from user_registration where email='" + email + "'");

			while (rs2.next()) {

				u.setId(rs2.getInt("uid"));
				u.setEmail(rs2.getString("email"));
				u.setPass(rs2.getString("password"));
				u.setFname(rs2.getString("fname"));
				u.setFname(rs2.getString("lname"));
				u.setFname(rs2.getString("mob_no"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return u;

	}

	public static UserDetails getEmailPassForAdmin(String email, String pass) {
		UserDetails u = new UserDetails();

		Connection con = makeConnection();
		try {
			ResultSet rs2 = con.createStatement().executeQuery(
					"select * from admin_registration where email='" + email + "' and password='" + pass + "'");

			while (rs2.next()) {

				u.setId(rs2.getInt("AdminId"));
				u.setEmail(rs2.getString("email"));
				u.setPass(rs2.getString("password"));
				u.setFname(rs2.getString("fname"));
				u.setFname(rs2.getString("lname"));
				u.setFname(rs2.getString("mob_no"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return u;

	}

	public static int getMaxCode() {
		int id = 0;
		Connection con = makeConnection();
		try {
			ResultSet rs = con.createStatement().executeQuery("select max(id) as id  from user ");
			while (rs.next()) {
				id = rs.getInt("id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}

	public static int getMaxId(int id) {
		Connection con = makeConnection();
		try {
			ResultSet rs1 = con.createStatement().executeQuery("select *   from user where id=" + id);
			while (rs1.next()) {
				// System.out.println(rs1.getString("firstname"));
				// System.out.println(rs1.getString("lastname"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;

	}

	public static int getMaxAId() {
		Connection con = makeConnection();
		int aid = 0;
		try {
			ResultSet rs1 = con.createStatement().executeQuery("select max(aid) as aid  from answer");
			while (rs1.next()) {
				aid = rs1.getInt("aid");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aid;

	}

	/**
	 * 
	 * this method foe selecting the category and it store into the List of
	 * object
	 */

	public static List<Category> getAllCategory() {
		List<Category> categoryList = new ArrayList<Category>();
		Connection con = makeConnection();

		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "Select * from category";

		try {
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();

			while (rs.next()) {
				Category category = new Category();
				category.setCid(rs.getInt("cid"));
				category.setCategoryName(rs.getString("Category_Name"));
				categoryList.add(category);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {

				}
			}
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException ex) {

				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {

				}
			}
		}
		// System.out.println(categoryList.size());
		return categoryList;
	}

	public static String getCategoryName(int cid) {
		Connection con = makeConnection();
		String cName = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "Select * from category where cid=" + cid;

		try {
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();

			while (rs.next()) {
				cName = rs.getString("Category_Name");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();

				} catch (SQLException ex) {

				}
			}
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException ex) {

				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {

				}
			}
		}
		return cName;
	}

	/**
	 * 
	 * this method for selecting the sub category
	 */

	public static List<Subcategory> getAllSubCategory(int cid) {
		List<Subcategory> categoryList = new ArrayList<Subcategory>();
		Connection con = makeConnection();

		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select * from subcategory where categoryid=" + cid;

		try {
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();

			while (rs.next()) {
				Subcategory category = new Subcategory();
				category.setCid(rs.getInt("categoryid"));
				// //System.out.println(rs.getInt("categoryid"));
				category.setSubcategoryName(rs.getString("SubcategoryName"));
				category.setSubid(rs.getInt("subid"));
				// //System.out.println(DataAceesLayer.getCategoryName(rs.getInt("categoryid")));
				// category.setCategoryName(DataAceesLayer.getCategoryName(rs.getInt("categoryid")));
				categoryList.add(category);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {

				}
			}
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException ex) {

				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}
		// //System.out.println(categoryList.size());
		return categoryList;
	}

	/**
	 * 
	 * @param uid:
	 *            user enter into community
	 * @param subid:with
	 *            his sub keywords
	 * @param cid:with
	 *            main category
	 */

	public static boolean insertCommunityInfo(String uid, String cid, String subid) {
		boolean flag = false;
		Connection con = makeConnection();
		PreparedStatement pstm = null;

		String sql = "insert into communityinfo values(?,?,?)";

		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, Integer.parseInt(uid));
			pstm.setInt(2, Integer.parseInt(cid));
			pstm.setInt(3, Integer.parseInt(subid));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int i = 0;
		try {
			i = pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException ex) {

				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}

		if (i == 0) {
			flag = true;
		}

		return true;
	}

	/**
	 * 
	 * @param uid
	 *            it is the current user id
	 * @return object of UserDetails
	 */

	public static UserDetails getDetails(int uid) {
		UserDetails u = null;
		Connection con = makeConnection();
		ResultSet rs = null;
		try {
			rs = con.createStatement().executeQuery("select * from user_registration where uid=" + uid);
			while (rs.next()) {
				u = new UserDetails();

				u.setId(rs.getInt("uid"));
				u.setEmail(rs.getString("email"));
				u.setPass(rs.getString("pass"));
				u.setFname(rs.getString("fname"));
				u.setFname(rs.getString("lname"));
				u.setFname(rs.getString("mob_no"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {

				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}
		return u;

	}

	/**
	 * 
	 * @param AdminID
	 *            here we are using for the admin details
	 * @return
	 */

	public static UserDetails getDetailsForAdmin(int AdminID) {
		UserDetails u = null;
		Connection con = makeConnection();
		ResultSet rs = null;
		try {
			rs = con.createStatement().executeQuery("select * from admin_registration where adminID=" + AdminID);
			while (rs.next()) {
				u = new UserDetails();

				u.setId(rs.getInt("adminID"));
				u.setEmail(rs.getString("email"));
				u.setPass(rs.getString("password"));
				u.setFname(rs.getString("fname"));
				u.setLname(rs.getString("lname"));
				u.setPhnno(rs.getString("mob_no"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {

				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}
		return u;

	}

	/**
	 * 
	 * @param uid
	 * @return
	 */
	public static UserCommunityInfomation getUserCommunityInfomation(int uid) {
		UserCommunityInfomation communityInfomation = new UserCommunityInfomation();
		Connection con = makeConnection();
		Statement st = null;
		ResultSet rs = null;

		String sql = "select * from vw_UserCommunityInfomation where uid=" + uid;

		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {
				communityInfomation.setCategoryName(rs.getString("Category_Name"));
				communityInfomation.setCid(rs.getInt("cid"));
				communityInfomation.setEmail(rs.getString("email"));
				communityInfomation.setFname(rs.getString("fname"));
				communityInfomation.setLname(rs.getString("lname"));
				communityInfomation.setSubCategoryName(rs.getString("SubcategoryName"));
				communityInfomation.setSubid(rs.getInt("subid"));
				communityInfomation.setUid(rs.getInt("uid"));
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {

				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException ex) {

				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}

		return communityInfomation;
	}

	public static String getUserName(int uid) {
		UserCommunityInfomation communityInfomation = new UserCommunityInfomation();
		Connection con = makeConnection();
		Statement st = null;
		ResultSet rs = null;
		String name = null;
		String sql = "select * from vw_UserCommunityInfomation where uid=" + uid;

		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {
				name = rs.getString("fname") + " " + rs.getString("lname");
				communityInfomation.setCategoryName(rs.getString("Category_Name"));
				communityInfomation.setCid(rs.getInt("cid"));
				communityInfomation.setEmail(rs.getString("email"));
				communityInfomation.setFname(rs.getString("fname"));
				communityInfomation.setLname(rs.getString("lname"));
				communityInfomation.setSubCategoryName(rs.getString("SubcategoryName"));
				communityInfomation.setSubid(rs.getInt("subid"));
				communityInfomation.setUid(rs.getInt("uid"));
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {

				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException ex) {

				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}

		return name;
	}

	/**
	 * 
	 * @param cid
	 *            get all user list in specific community
	 * @return list of all user in specific community
	 */
	public static List<UserCommunityInfomation> getAllUserCommunityInfomation(int cid) {
		UserCommunityInfomation communityInfomation = null;

		List<UserCommunityInfomation> communityInfomationsList = new ArrayList<UserCommunityInfomation>();
		List<UserCommunityInfomation> communityInfomationsList1 = new ArrayList<UserCommunityInfomation>();

		Connection con = makeConnection();
		Statement st = null;
		ResultSet rs = null;

		String sql = "select distinct * from vw_UserCommunityInfomation where cid=" + cid + " order by uid";
		// //System.out.println(sql);
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {
				communityInfomation = new UserCommunityInfomation();
				communityInfomation.setCategoryName(rs.getString("Category_Name"));
				communityInfomation.setCid(rs.getInt("cid"));
				communityInfomation.setEmail(rs.getString("email"));
				communityInfomation.setFname(rs.getString("fname"));
				communityInfomation.setLname(rs.getString("lname"));
				communityInfomation.setSubCategoryName(rs.getString("SubcategoryName"));
				communityInfomation.setSubid(rs.getInt("subid"));
				communityInfomation.setUid(rs.getInt("uid"));
				communityInfomationsList.add(communityInfomation);
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {

				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException ex) {

				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}

		Set<UserCommunityInfomation> alphaSet = new HashSet<UserCommunityInfomation>(communityInfomationsList);
		for (UserCommunityInfomation alpha : alphaSet) {

			communityInfomationsList1.add(alpha);
		}
		// //System.out.println("size object list uid " +
		// communityInfomationsList1.size());
		return communityInfomationsList1;
	}

	public static List<UserCommunityInfomation> getAllUserCommunityInfomation() {
		UserCommunityInfomation communityInfomation = null;

		List<UserCommunityInfomation> communityInfomationsList = new ArrayList<UserCommunityInfomation>();
		List<UserCommunityInfomation> communityInfomationsList1 = new ArrayList<UserCommunityInfomation>();

		Connection con = makeConnection();
		Statement st = null;
		ResultSet rs = null;

		String sql = "select distinct * from vw_UserCommunityInfomation";
		// //System.out.println(sql);
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {
				communityInfomation = new UserCommunityInfomation();
				communityInfomation.setCategoryName(rs.getString("Category_Name"));
				communityInfomation.setCid(rs.getInt("cid"));
				communityInfomation.setEmail(rs.getString("email"));
				communityInfomation.setFname(rs.getString("fname"));
				communityInfomation.setLname(rs.getString("lname"));
				communityInfomation.setSubCategoryName(rs.getString("SubcategoryName"));
				communityInfomation.setSubid(rs.getInt("subid"));
				communityInfomation.setUid(rs.getInt("uid"));
				communityInfomationsList.add(communityInfomation);
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {

				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException ex) {

				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}

		Set<UserCommunityInfomation> alphaSet = new HashSet<UserCommunityInfomation>(communityInfomationsList);
		for (UserCommunityInfomation alpha : alphaSet) {

			communityInfomationsList1.add(alpha);
		}
		// //System.out.println("size object list uid " +
		// communityInfomationsList1.size());
		return communityInfomationsList1;
	}

	public static List<UserCommunityInfomation> getAllUserCommunityInfomationList(int cid) {
		UserCommunityInfomation communityInfomation = null;

		List<UserCommunityInfomation> communityInfomationsList = new ArrayList<UserCommunityInfomation>();

		Connection con = makeConnection();
		Statement st = null;
		ResultSet rs = null;

		String sql = "select distinct (uid) from vw_UserCommunityInfomation where cid=" + cid;

		// //System.out.println(sql);
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {
				communityInfomation = new UserCommunityInfomation();
				communityInfomation.setUid(rs.getInt("uid"));
				communityInfomationsList.add(communityInfomation);
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {

				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException ex) {

				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}

		// //System.out.println("size single list uid " +
		// communityInfomationsList.size());
		return communityInfomationsList;
	}

	public static String getCommunityDesc(int cid) {
		String communityDesc = null;
		Connection con = makeConnection();
		ResultSet rs = null;
		try {
			rs = con.createStatement().executeQuery("select * from communitydesc where cid=" + cid);
			// //System.out.println("select * from user where id=" + cid);
			while (rs.next()) {
				communityDesc = rs.getString("desc");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {

				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}
		// //System.out.println(communityDesc);
		return communityDesc;

	}

	public static boolean insertNewCategory(String category) {
		Connection con = makeConnection();
		PreparedStatement pstm = null;
		boolean flag = false;
		try {
			pstm = con.prepareStatement("insert into category(Category_Name) values(?)");
			pstm.setString(1, category);

			int i = pstm.executeUpdate();
			if (i == 1) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException ex) {

				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}
		return flag;

	}

	public static int getMaxCID() {
		int cid = 0;
		Connection con = makeConnection();
		ResultSet rs = null;
		try {
			rs = con.createStatement().executeQuery("select cid from category");
			while (rs.next()) {
				cid = rs.getInt("cid");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {

				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}

		return cid;
	}

	public static boolean insertSubCategory(int cid, String subcategory) {
		boolean flag = false;
		Connection con = makeConnection();
		PreparedStatement pstm = null;
		try {
			// SubId, CategoryId, SubcategoryName
			pstm = con.prepareStatement("insert into subcategory(CategoryId,SubcategoryName) values(?,?)");
			pstm.setInt(1, cid);
			pstm.setString(2, subcategory);

			int i = pstm.executeUpdate();
			if (i == 1) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException ex) {

				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}
		return flag;
	}

	/**
	 * 
	 * @param question
	 *            inserted question by current user
	 * @return
	 */

	public static Boolean insertQuestion(Question question) {

		Boolean flag = false;
		Connection con = makeConnection();
		Statement st = null;

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		// //System.out.println(date);
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		// //System.out.println(sqlDate);
		String sql = "insert into questiondetails(subid,uid,question,date,cid) " + "values(" + question.getSubid() + ","
				+ question.getUid() + ",'" + question.getQuestion() + "',sysdate()," + question.getCid() + ")";
		try {
			// //System.out.println(sql);
			st = con.createStatement();
			int i = st.executeUpdate(sql);

			if (i == 1) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (st != null) {
				try {
					st.close();
				} catch (SQLException ex) {

				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}

		return flag;
	}

	/**
	 * 
	 * @param UID:
	 *            user id
	 * @return list of unique user ask question
	 */
	public static List<QuestionAndAnswerDetails> getQADetails(int UID) {
		List<QuestionAndAnswerDetails> questionAnswerDetailsList = new ArrayList<QuestionAndAnswerDetails>();
		Connection con = makeConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;

		String sql = "select * from vw_questionanswer where uid=" + UID;

		try {
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();

			while (rs.next()) {

				QuestionAndAnswerDetails andAnswerDetails = new QuestionAndAnswerDetails();
				Question question = new Question();
				Answers answers = new Answers();
				question.setUid(rs.getInt("uid"));
				question.setQid(rs.getInt("qid"));
				question.setQuestion(rs.getString("question"));
				question.setDate(rs.getDate("date"));
				answers.setAid(rs.getInt("aid"));
				andAnswerDetails.setAnswerList(DataAceesLayer.getAnswer(rs.getInt("qid")));

				andAnswerDetails.setAnswer(answers);
				andAnswerDetails.setQuestion(question);

				questionAnswerDetailsList.add(andAnswerDetails);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {

				}
			}
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException ex) {

				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}

		return questionAnswerDetailsList;
	}

	/**
	 * 
	 * @return list of question for recent ask question
	 */
	public static List<QuestionAndAnswerDetails> getQADetails() {
		List<QuestionAndAnswerDetails> questionAnswerDetailsList = new ArrayList<QuestionAndAnswerDetails>();
		Connection con = makeConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;

		String sql = "select * from questiondetails order by date desc";

		try {
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();

			while (rs.next()) {
				QuestionAndAnswerDetails andAnswerDetails = new QuestionAndAnswerDetails();
				Question question = new Question();
				Answers answers = new Answers();
				question.setUid(rs.getInt("uid"));
				question.setQid(rs.getInt("qid"));
				question.setQuestion(rs.getString("question"));
				question.setDate(rs.getDate("date"));
				andAnswerDetails.setAnswerList(DataAceesLayer.getAnswer(rs.getInt("qid")));

				andAnswerDetails.setAnswer(answers);
				andAnswerDetails.setQuestion(question);

				questionAnswerDetailsList.add(andAnswerDetails);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {

				}
			}
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException ex) {

				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}

		// //System.out.println(questionAnswerDetailsList);
		return questionAnswerDetailsList;
	}

	/**
	 * 
	 * @return list of question for recent ask question
	 */
	public static List<QuestionAndAnswerDetails> getQADetailsForFordwording(int subid) {
		List<QuestionAndAnswerDetails> questionAnswerDetailsList = new ArrayList<QuestionAndAnswerDetails>();
		Connection con = makeConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;

		String sql = "select * from questiondetails where subid=" + subid;

		// //System.out.println(sql);
		try {
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();

			while (rs.next()) {
				QuestionAndAnswerDetails andAnswerDetails = new QuestionAndAnswerDetails();
				Question question = new Question();
				Answers answers = new Answers();
				question.setUid(rs.getInt("uid"));
				question.setQid(rs.getInt("qid"));
				question.setQuestion(rs.getString("question"));
				question.setDate(rs.getDate("date"));
				andAnswerDetails.setAnswerList(DataAceesLayer.getAnswer(rs.getInt("qid")));

				// andAnswerDetails.setAnswer(answers);
				andAnswerDetails.setQuestion(question);

				questionAnswerDetailsList.add(andAnswerDetails);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {

				}
			}
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException ex) {

				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}

		// System.out.println(questionAnswerDetailsList);
		return questionAnswerDetailsList;
	}

	/**
	 * 
	 * @return list of question for recent ask question
	 */
	public static List<QuestionAndAnswerDetails> getQADetailsForResponseRate(int cid) {
		List<QuestionAndAnswerDetails> questionAnswerDetailsList = new ArrayList<QuestionAndAnswerDetails>();
		Connection con = makeConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;

		String sql = "select * from questiondetails where cid=" + cid;

		// //System.out.println(sql);
		try {
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();

			while (rs.next()) {
				QuestionAndAnswerDetails andAnswerDetails = new QuestionAndAnswerDetails();
				Question question = new Question();
				Answers answers = new Answers();
				question.setUid(rs.getInt("uid"));
				question.setQid(rs.getInt("qid"));
				question.setQuestion(rs.getString("question"));
				question.setDate(rs.getDate("date"));
				andAnswerDetails.setAnswerList(DataAceesLayer.getAnswer(rs.getInt("qid")));

				// andAnswerDetails.setAnswer(answers);
				andAnswerDetails.setQuestion(question);

				questionAnswerDetailsList.add(andAnswerDetails);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {

				}
			}
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException ex) {

				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}

		// System.out.println(questionAnswerDetailsList);
		return questionAnswerDetailsList;
	}

	/**
	 * 
	 * @param qids
	 *            the matching qid's of inserted question's
	 * @return list of those questions
	 */
	public static List<QuestionAndAnswerDetails> getSelectedQADetails(Set<Integer> qids) {
		List<QuestionAndAnswerDetails> questionAnswerDetailsList = new ArrayList<QuestionAndAnswerDetails>();
		Connection con = makeConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;

		// //System.out.println(sql);
		try {
			for (Integer qid : qids) {
				String sql = "select * from questiondetails where qid=" + qid;
				pstm = con.prepareStatement(sql);
				rs = pstm.executeQuery();

				while (rs.next()) {
					QuestionAndAnswerDetails andAnswerDetails = new QuestionAndAnswerDetails();
					Question question = new Question();
					Answers answers = new Answers();
					question.setUid(rs.getInt("uid"));
					question.setQid(rs.getInt("qid"));
					question.setQuestion(rs.getString("question"));
					question.setDate(rs.getDate("date"));
					andAnswerDetails.setAnswerList(DataAceesLayer.getAnswer(rs.getInt("qid")));

					// andAnswerDetails.setAnswer(answers);
					andAnswerDetails.setQuestion(question);

					questionAnswerDetailsList.add(andAnswerDetails);
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {

				}
			}
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException ex) {

				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}

		// System.out.println(questionAnswerDetailsList);
		return questionAnswerDetailsList;
	}

	/**
	 * 
	 * @param answer
	 *            insert answer
	 * @return flag
	 */
	public static Boolean insertAnswer(Answers answer) {
		Boolean flag = false;
		Connection con = makeConnection();
		Statement st = null;

		@SuppressWarnings("unused")
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		// System.out.println(date);
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		// System.out.println(sqlDate);
		String sql = "insert into answer(qid,answer,rating,date,uid) " + "values(" + answer.getQid() + ",'"
				+ answer.getAnswer() + "'," + answer.getRating() + ",sysdate()," + answer.getUid() + ")";
		try {
			// System.out.println(sql);
			st = con.createStatement();
			int i = st.executeUpdate(sql);
			if (i == 1) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (st != null) {
				try {
					st.close();
				} catch (SQLException ex) {

				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}

		return flag;
	}

	public static List<Answers> getAnswer(int qid) {
		Connection con = makeConnection();
		Statement st = null;
		ResultSet rs = null;
		List<Answers> answerList = new ArrayList<Answers>();
		String sql = "select * from answer where qid=" + qid;
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Answers answers = new Answers();
				answers.setAnswer(rs.getString("answer"));
				answers.setAid(rs.getInt("aid"));
				answers.setDate(rs.getDate("Date"));
				answers.setRating(rs.getDouble("rating"));
				answers.setUid(rs.getInt("uid"));
				answerList.add(answers);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {

				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException ex) {

				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}

		return answerList;

	}

	public static Answers getSingleAnswer(int aid) {
		Connection con = makeConnection();
		Statement st = null;
		ResultSet rs = null;
		Answers answers = new Answers();
		String sql = "select * from answer where aid=" + aid;
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {

				answers.setAnswer(rs.getString("answer"));
				answers.setAid(rs.getInt("aid"));
				answers.setDate(rs.getDate("Date"));
				answers.setRating(rs.getDouble("rating"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {

				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException ex) {

				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}

		return answers;

	}

	public static List<Answers> getTopScore(Set<Integer> uid) {
		Connection con = makeConnection();
		Statement st = null;
		ResultSet rs = null;
		List<Answers> list = new ArrayList<Answers>();

		try {
			for (Integer set : uid) {
				String sql = "SELECT * FROM answer where uid=" + set + " ORDER BY rating DESC LIMIT 7";
				st = con.createStatement();
				rs = st.executeQuery(sql);
				while (rs.next()) {
					Answers answers = new Answers();
					answers.setAnswer(rs.getString("answer"));
					answers.setAid(rs.getInt("aid"));
					answers.setDate(rs.getDate("Date"));
					answers.setRating(rs.getDouble("rating"));
					answers.setUid(rs.getInt("uid"));
					list.add(answers);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {

				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException ex) {

				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}

		return list;

	}

	public static Boolean insertRating(Rating rate) {
		Boolean flag = false;
		Connection con = makeConnection();
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = con.prepareStatement("insert into rating values(?,?,?,?)");
			preparedStatement.setInt(1, rate.getRating());
			preparedStatement.setInt(2, rate.getQid());
			preparedStatement.setInt(3, rate.getAid());
			preparedStatement.setInt(4, rate.getUid());
			int i = preparedStatement.executeUpdate();
			if (i == 1) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException ex) {

				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}

		return flag;
	}

	public static boolean getRating(int uid, int aid) {
		boolean flag = false;
		Connection con = makeConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from rating where uid=" + uid + " and aid=" + aid;

		// System.out.println(sql);
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				// flag=true;

				int aid1 = rs.getInt("aid");
				int uid1 = rs.getInt("uid");
				if (aid1 != 0 && uid1 != 0 && rs.getInt("rating") != 0) {
					flag = true;
				}
				// System.out.println(aid1 + " " + uid1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException ex) {

				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}
		return flag;
	}

	public static List<Rating> getRating(int uid) {
		List<Rating> ratingList = new ArrayList<Rating>();
		Connection con = makeConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from rating where uid=" + uid;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Rating rate = new Rating();
				rate.setAid(rs.getInt("aid"));
				rate.setQid(rs.getInt("qid"));
				rate.setRating(rs.getInt("rating"));
				rate.setUid(rs.getInt("uid"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException ex) {

				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}

		return ratingList;
	}

	public static List<Rating> getSingleRating(int aid) {
		List<Rating> ratingList = new ArrayList<Rating>();
		Connection con = makeConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from rating where aid=" + aid;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Rating rate = new Rating();
				rate.setAid(rs.getInt("aid"));
				rate.setQid(rs.getInt("qid"));
				rate.setRating(rs.getInt("rating"));
				rate.setUid(rs.getInt("uid"));

				ratingList.add(rate);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException ex) {

				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}
		return ratingList;
	}

	public static List<Rating> getAllRatingForAvg() {
		List<Rating> ratingList = new ArrayList<Rating>();
		Connection con = makeConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from rating";

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Rating rate = new Rating();
				rate.setAid(rs.getInt("aid"));
				rate.setQid(rs.getInt("qid"));
				rate.setRating(rs.getInt("rating"));
				rate.setUid(rs.getInt("uid"));
				ratingList.add(rate);
				/*
				 * if(ratingList.size()==0)
				 * 
				 * else { if(ratingList.size()!=0) { for(Rating
				 * rating:ratingList) { if(rs.getInt("aid")!=rating.getAid()) {
				 * ratingList.add(rate);
				 * 
				 * } }
				 */
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException ex) {

				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}
		return ratingList;
	}

	public static int getCountRate(int aid) {

		int count = 0;
		Connection con = makeConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select count(*) as count from rating where aid=" + aid;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				count = rs.getInt("count");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException ex) {

				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}

		return count;
	}

	public static boolean insertNotification(Notification notification) {
		boolean flag = false;
		Connection con = makeConnection();
		String sql = "insert into notification value(" + notification.getCuid() + "," + notification.getFuid() + ",'"
				+ notification.getPost() + "',sysdate())";
		Statement pstm = null;

		try {
			pstm = con.createStatement();
			int i = pstm.executeUpdate(sql);
			if (i == 1) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException ex) {

				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}
		return flag;
	}

	public static List<Notification> getNotification(int uid) {
		List<Notification> nList = new ArrayList<Notification>();
		int count = 0;
		Connection con = makeConnection();
		ResultSet rs = null;
		try {

			rs = con.createStatement().executeQuery("select * from notification where fuid=" + uid);
			while (rs.next()) {
				Notification notification = new Notification();
				notification.setPost(rs.getString("post"));
				notification.setCuid(rs.getInt("cuid"));
				nList.add(notification);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}
		// System.out.println(count);
		return nList;
	}

	public static int getNotificationCount(int uid) {
		List<Notification> nList = new ArrayList<Notification>();
		int count = 0;
		Connection con = makeConnection();
		ResultSet rs = null;
		try {

			rs = con.createStatement().executeQuery("select count(fuid) as count from notification where fuid=" + uid);
			while (rs.next()) {
				count = rs.getInt("count");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}

		return count;
	}

	public static int getCommunicationCount(int cuid, int fuid) {

		int count = 0;
		Connection con = makeConnection();
		ResultSet rs = null;
		try {
			rs = con.createStatement().executeQuery("select count(*) as count from notification where " + "(cuid="
					+ cuid + " and fuid=" + fuid + ") or (cuid=" + fuid + " and fuid=" + cuid + ")");
			while (rs.next()) {
				count = rs.getInt("count");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}
		return count;
	}

	/**
	 * insert question that are ask by user in previoussearch table in db
	 * 
	 * @param question
	 * @return
	 */
	public static boolean insertPreviousQuestion(PriviousQuestion question) {
		boolean flag = false;
		Connection con = makeConnection();
		String sql = "insert into previoussearch(question, consistanceAnswer, cid) value(?,?,?)";
		PreparedStatement pstm = null;

		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, question.getQuestion());
			pstm.setString(2, " ");
			pstm.setInt(3, question.getCid());

			int i = pstm.executeUpdate();
			if (i == 1) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException ex) {

				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}
		return flag;
	}

	public static List<PriviousQuestion> getPreviousQuestion(int cid) {
		Connection con = makeConnection();
		Statement st = null;
		ResultSet rs = null;
		List<PriviousQuestion> questionList = new ArrayList<PriviousQuestion>();
		String sql = "select * from previoussearch where cid=" + cid;
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				PriviousQuestion question = new PriviousQuestion();
				question.setQuestion(rs.getString("question"));
				questionList.add(question);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {

				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException ex) {

				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}

		// System.out.println(questionList.size());
		return questionList;

	}

	public static List<String> getStopwords() {
		List<String> stopwordList = new ArrayList<String>();
		PreparedStatement pstm = null;
		Connection con = makeConnection();
		ResultSet rs = null;

		String sql = "select * from stopword";

		try {
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				String word = rs.getString(1);
				stopwordList.add(word);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {

				}
			}
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException ex) {

				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}
		return stopwordList;
	}

	public static long getAvgrageResponseDelay(int qid) {
		long avgTime = 0;
		Connection con = makeConnection();
		ResultSet rs = null;
		Statement st = null;
		String sql = "SELECT q.QID,q.DATE,a.date,a.AID,q.cid, " + "TIMESTAMPDIFF(second,q.DATE,a.DATE) as time "
				+ "FROM questiondetails q INNER JOIN answer a " + "ON q.QID = a.qid where q.qid=" + qid;
		// System.out.println(sql);
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				avgTime = +rs.getInt("time");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return avgTime;
	}

	public static boolean updateAnswerRating(int aid, double avgRating) {
		boolean flag = false;
		Connection con = makeConnection();
		Statement st = null;
		String sql = "update answer set rating=" + avgRating + " where aid=" + aid;

		//// System.out.println(sql);
		try {
			st = con.createStatement();
			int i = st.executeUpdate(sql);
			if (i == 1) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return flag;
	}

	public static Answers getMaxRatingForReward(int qid) {
		Connection con = makeConnection();
		Statement st = null;
		ResultSet rs = null;
		Answers answers = new Answers();
		String sql = "select max(rating) as rating,aid,qid,answer,uid from answer where qid=" + qid;
		// //System.out.println(sql);
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				answers.setRating(rs.getDouble("rating"));
				answers.setAid(rs.getInt("aid"));
				answers.setQid(rs.getInt("qid"));
				answers.setAnswer(rs.getString("answer"));
				answers.setUid(rs.getInt("uid"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {

				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException ex) {

				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}

		return answers;

	}

	public static Boolean insertReward(Answers ans) {
		Connection con = makeConnection();
		Statement st = null;
		if (ans.getRating() > 3) {
			String sql = "insert into reward(UID,CID,RANK,TIME,aid,bal) values" + "(" + ans.getUid() + ","
					+ ans.getUid() + ",'" + ans.getRating() + "',sysdate() , " + ans.getAid() + "," + ans.getBal()
					+ ")";
			// System.out.println(sql);
			try {
				con.createStatement().executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	public static List<Reward> getAllSelectdReward(int cid) {
		List<Reward> list = new ArrayList<Reward>();
		Connection connection = makeConnection();
		try {
			ResultSet rs = connection.createStatement().executeQuery("select * from reward where cid=" + cid);
			while (rs.next()) {
				Reward reward = new Reward();
				reward.setAid(rs.getInt("aid"));
				reward.setCid(rs.getInt("cid"));
				reward.setId(rs.getInt("id"));
				reward.setRank(rs.getString("rank"));
				reward.setUid(rs.getInt("uid"));
				reward.setBal(rs.getInt("bal"));
				list.add(reward);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public static void uploadImage(InputStream is, int uid) {
		Connection con = makeConnection();
		PreparedStatement pstm = null;
		String sql = "insert into profilepic(uid,img) values (?,?)";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, uid);
			pstm.setBlob(2, is);
			pstm.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

	public static Boolean checkImg(int uid) {
		Boolean flag = false;
		Connection connection = makeConnection();
		String sql = "select * from profilePic where uid=" + uid;
		try {
			ResultSet rs = connection.createStatement().executeQuery(sql);
			while (rs.next()) {
				int uid1 = rs.getInt("uid");
				if (uid1 == 0) {
					flag = true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	public static BufferedImage getImg(int uid) {
		BufferedImage image = null;
		Connection connection = makeConnection();
		String sql = "select * from profilePic where uid=" + uid;
		try {
			ResultSet rs = connection.createStatement().executeQuery(sql);
			while (rs.next()) {
				int uid1 = rs.getInt("uid");
				Blob blob = rs.getBlob("img");
				InputStream in = blob.getBinaryStream();
				try {
					image = ImageIO.read(in);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image;
	}

	public static Boolean checkImg1(int uid) {
		Boolean flag = false;
		Connection connection = makeConnection();
		String sql = "delete from profilepic where uid=" + uid;
		try {
			connection.createStatement().executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	public static Reward getSelectdReward(int uid) {
		Reward reward = new Reward();
		Connection connection = makeConnection();
		try {
			ResultSet rs = connection.createStatement().executeQuery("select * from reward where uid=" + uid);
			while (rs.next()) {
				reward.setAid(rs.getInt("aid"));
				reward.setCid(rs.getInt("cid"));
				reward.setId(rs.getInt("id"));
				reward.setRank(rs.getString("rank"));
				reward.setUid(rs.getInt("uid"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reward;
	}

	public static void truncateReward() {
		Connection con = makeConnection();
		String sql = "delete from reward";
		try {
			con.createStatement().executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static int calculateTotalRewars(Integer uid) {
		int total = 0;
		Connection connection = makeConnection();
		String sql = "select sum(bal) as total from reward where uid=" + uid;
		ResultSet rs = null;
		try {
			rs = connection.createStatement().executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (rs.next()) {
				total = rs.getInt("total");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return total;
	}

	public static void insertTotalReward(Integer uid, int total) {
		Connection con = makeConnection();
		String sql = "insert into totalbalance(uid,bal) values (" + uid + "," + total + ")";
		try {
			con.createStatement().executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static int getReward(int uid) {
		int bal = 0;
		Connection con = makeConnection();
		String sql = "select * from totalbalance where uid=" + uid;
		ResultSet rs = null;
		try {
			rs = con.createStatement().executeQuery(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			while (rs.next()) {
				bal = rs.getInt("bal");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bal;
	}

	public static List<Integer> getRewardList() {
		List<Integer> list = new ArrayList<Integer>();
		Connection con = makeConnection();
		String sql = "select * from totalbalance order by bal desc";
		ResultSet rs = null;
		try {
			rs = con.createStatement().executeQuery(sql);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			while (rs.next()) {
				list.add(rs.getInt("uid"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public static void updateReward(int uid, int bal) {
		Connection connection = makeConnection();
		String sql = "update totalbalance set bal=" + bal + " where uid=" + uid;
		try {
			connection.createStatement().executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void insertResponseRate(Map<Question, Double> maxMap) {
		Connection con = makeConnection();
		for (Entry<Question, Double> entry : maxMap.entrySet()) {
			Question que = entry.getKey();
			String sql = "insert into responseRate(qid,rate) values(" + que.getQid() + "," + entry.getValue() + ")";
			// System.out.println(sql);
			try {
				con.createStatement().executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static boolean insertDesc(int cid, String desc) {
		String sql = "insert into communitydesc values(?,?)";
		Connection con = makeConnection();
		System.out.println("conn");
		PreparedStatement pstm;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, cid);
			pstm.setString(2, desc);
			System.out.println(desc);
			pstm.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public static List<QuestionAndAnswerDetails> searchByWholeQuestion(String question) {

		List<QuestionAndAnswerDetails> list = new ArrayList<QuestionAndAnswerDetails>();
		String sql = "select * from questiondetails where question like '%" + question + "%' and question not like '"
				+ question + "'";
		System.out.println(sql);
		try {
			ResultSet rs = makeConnection().createStatement().executeQuery(sql);
			while (rs.next()) {
				QuestionAndAnswerDetails andAnswerDetails = new QuestionAndAnswerDetails();
				Question question1 = new Question();
				Answers answers = new Answers();
				question1.setUid(rs.getInt("uid"));
				question1.setQid(rs.getInt("qid"));
				question1.setQuestion(rs.getString("question"));
				question1.setDate(rs.getDate("date"));
				andAnswerDetails.setAnswerList(DataAceesLayer.getAnswer(rs.getInt("qid")));

				andAnswerDetails.setAnswer(answers);
				andAnswerDetails.setQuestion(question1);

				list.add(andAnswerDetails);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static Boolean insertSuggestCategory(String string, String uid) {

		String string1 = " Admin please this category---->  " + string;
		boolean flag = false;
		try {
			flag = makeConnection().createStatement()
					.execute("insert into suggestCategory(message,uid) values('" + string + "'," + uid + ")");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	public static List<Suggestion> getSuggestCategory() {
		List<Suggestion> list = new ArrayList<Suggestion>();
		try {
			ResultSet rs = makeConnection().createStatement()
					.executeQuery("select * from SuggestCategory order by id desc");
			while (rs.next()) {
				Suggestion suggestion = new Suggestion();

				suggestion.setId(rs.getInt("id"));
				suggestion.setSugg(rs.getString("message"));
				suggestion.setUid(rs.getInt("uid"));
				list.add(suggestion);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public static int getSuggestCategoryCount() {
		int count = 0;
		try {
			ResultSet rs = makeConnection().createStatement()
					.executeQuery("select count(*) as count from SuggestCategory");
			while (rs.next()) {
				count = rs.getInt("count");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	public static void truncateSuggestCategory() {
		try {
			makeConnection().createStatement().execute("truncate table SuggestCategory");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void updateSuggestion(String id, String mag) {
		String sql = "update SuggestCategory set sugg='" + mag + "' where id=" + id;
		try {
			makeConnection().createStatement().execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static List<Suggestion> getSuggestCategoryMsg(int uid) {
		List<Suggestion> list = new ArrayList<Suggestion>();
		try {
			ResultSet rs = makeConnection().createStatement()
					.executeQuery("select * from SuggestCategory where uid=" + uid);
			while (rs.next()) {
				Suggestion suggestion = new Suggestion();

				suggestion.setId(rs.getInt("id"));
				suggestion.setSugg(rs.getString("message"));
				suggestion.setUid(rs.getInt("uid"));
				suggestion.setSugg1(rs.getString("sugg"));
				list.add(suggestion);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	
		/**
		 * 
		 * @return question for recent ask question
		 */
		public static Question checkQuestionExist(Question question) {
			
			Connection con = makeConnection();
			PreparedStatement pstm = null;
			ResultSet rs = null;
			Question question1 = new Question();
			String sql = "select * from questiondetails where question='"+question.getQuestion()+"'";

			System.out.println(sql);
			try {
				pstm = con.prepareStatement(sql);
				rs = pstm.executeQuery();

				while (rs.next()) {
					question1.setUid(rs.getInt("uid"));
					question1.setQid(rs.getInt("qid"));
					question1.setQuestion(rs.getString("question"));
					question1.setDate(rs.getDate("date"));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {

				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException ex) {

					}
				}
				if (pstm != null) {
					try {
						pstm.close();
					} catch (SQLException ex) {

					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (SQLException ex) {
					}
				}
			}

			// //System.out.println(questionAnswerDetailsList);
			return question1;
		}
	
	
		public static Boolean updateQuestionDate(Question question) {
			Connection con=makeConnection();
			Statement st=null;
			boolean flag = false;
			
			String sql="update questionDetails set date=sysdate() where qid=" + question.getQid();
			try {
				// //System.out.println(sql);
				st = con.createStatement();
				int i = st.executeUpdate(sql);

				if (i == 1) {
					flag = true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {

				if (st != null) {
					try {
						st.close();
					} catch (SQLException ex) {

					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (SQLException ex) {
					}
				}
			}

			return flag;
		}
		
		public static Question getQuestion(int qid) {
			Question question=new  Question();
			String sql="select * from questionDetails where qid="+qid;
			try {
				ResultSet rs=makeConnection().createStatement().executeQuery(sql);
				while (rs.next()) {
					question.setCid(rs.getInt("cid"));
					question.setSubid(rs.getInt("subid"));
					question.setUid(rs.getInt("uid"));
					question.setQuestion(rs.getString("question"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return question;
		}

		
		
	public static void main(String[] args) {
		/*
		 * // TODO Auto-generated method stub makeConnection();
		 * 
		 * // getAllSubCategory(1); UserCommunityInfomation u =
		 * getUserCommunityInfomation(5); //System.out.println(u);
		 */

		// getAllUserCommunityInfomation(6);
		// getCommunityDesc(7);

		/*
		 * String startDateString = "08-22-1992"; SimpleDateFormat df = new
		 * SimpleDateFormat("MM-dd-yyyy"); Date startDate; try { startDate =
		 * df.parse(startDateString); String newDateString =
		 * df.format(startDate); //System.out.println(newDateString);
		 * java.sql.Date sqlDate = new java.sql.Date(startDate.getTime());
		 * //System.out.println(sqlDate); } catch (ParseException e) {
		 * e.printStackTrace(); }
		 */
		// getAllUserCommunityInfomation(9);

		/*
		 * Question question = new Question(); question.setUid(6);
		 * question.setQuestion("What is java interface ?");
		 * question.setSubid(11);
		 */

		/* insertQuestion(question); */

		/*
		 * DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		 * Date date = new Date(); //System.out.println(date); java.sql.Date
		 * sqlDate = new java.sql.Date(date.getTime());
		 * //System.out.println(sqlDate);
		 * 
		 */
		/*
		 * getAllUserCommunityInfomation(6);
		 * getAllUserCommunityInfomationList(6);
		 */

		// getQADetails();
		// getRating(5,1);
		// getNotificationCount(5);

		// insertFile();

		// getAllSubCategory(6);

		/*
		 * String cname = getCategoryName(6); //System.out.println(cname);
		 */

		// getAvgrageResponseDelay(18);
		// getSingleRating(1);

		/*
		 * Answers ans = getSingleAnswer(15); double a = ans.getRating();
		 */
		// System.out.println(a);

		insertDesc(14,
				"Sport (British English) or sports (American English) includes all forms of competitive physical activity or games which,[1] through casual or organised participation, aim to use, maintain or improve physical ability and skills while providing enjoyment to participants, and in some cases, entertainment for spectators.[2] Usually the contest or game is between two sides, each attempting to exceed the other. Some sports allow a tie game; others provide tie-breaking methods, to ensure one winner and one loser. A number of such two-sided contests may be arranged in a tournament producing a champion. Many sports leagues make an annual champion by arranging games in a regular sports season, followed in some cases by playoffs. Hundreds of sports exist, from those between single contestants, through to those with hundreds of simultaneous participants, either in teams or competing as individuals. In certain sports such as racing, many contestants may compete, each against each other, with one winner.");

	}

}
