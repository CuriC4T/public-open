package com.example.demo.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import com.example.demo.domain.FoodData;
import com.example.demo.domain.FoodDataList;
import com.example.demo.domain.Menu;
import com.example.demo.domain.PriceData;
import com.example.demo.domain.PriceDataList;
import com.example.demo.domain.WeekMenu;

public class DatabaseConnection {
	static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
	static final String DB_URL = "jdbc:mariadb://localhost:3306/evewa";
	static final String USER = "root";
	static final String PASS = "1234";

	public Connection connect() {

		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER);
			System.out.println("Load Memory.....");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("connect to DB.....");
			if (conn != null) {
				System.out.println("성공");
			} else {
				System.out.println("실패");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return conn;

	}

	public PriceDataList getPriceOfPeriod(String type, String startdate, String enddate) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		PriceDataList pricelist = new PriceDataList();
		ArrayList<PriceData> Objectlist = new ArrayList<PriceData>();
		PriceData pricedata = null;

		int size;
		if (type != null && startdate != null && enddate != null) {
			conn = connect();

			if (conn != null) {
				try {
					switch (type) {
					case "rice":
						pstmt = conn.prepareStatement("SELECT day,rice FROM price WHERE day >= ? and day <= ?");
						break;
					case "onion":
						pstmt = conn.prepareStatement("SELECT day,onion FROM price WHERE day >= ? and day <= ?");
						break;
					case "pollack":
						pstmt = conn.prepareStatement("SELECT day,pollack FROM price WHERE day >= ? and day <= ?");
						break;
					case "neutali":
						pstmt = conn.prepareStatement("SELECT day,neutali FROM price WHERE day >= ? and day <= ?");
						break;
					case "chicken":
						pstmt = conn.prepareStatement("SELECT day,chicken FROM price WHERE day >= ? and day <= ?");
						break;
					case "potato":
						pstmt = conn.prepareStatement("SELECT day,potato FROM price WHERE day >= ? and day <= ?");
						break;
					case "mu":
						pstmt = conn.prepareStatement("SELECT day,mu FROM price WHERE day >= ? and day <= ?");
						break;
					case "aehobak":
						pstmt = conn.prepareStatement("SELECT day,aehobak FROM price WHERE day >= ? and day <= ?");
						break;
					default:
						pstmt = null;
						break;
					}
					if (pstmt != null) {
						startdate = startdate.substring(0, 4) + "-" + startdate.substring(4, 6) + "-"
								+ startdate.substring(6);
						enddate = enddate.substring(0, 4) + "-" + enddate.substring(4, 6) + "-" + enddate.substring(6);

						pstmt.setDate(1, Date.valueOf(startdate));
						pstmt.setDate(2, Date.valueOf(enddate));

						rs = pstmt.executeQuery();
						System.out.print(rs.toString());
						while (rs.next()) {

							pricedata = new PriceData();

							pricedata.setType(type);
							pricedata.setDay(rs.getDate(1).toString());
							pricedata.setPrice(rs.getInt(2));

							Objectlist.add(pricedata);
						}
						size = rs.getRow() - 1;
						pricelist.setSuccess(true);
						pricelist.setTotal_count(size);
						pricelist.setList(Objectlist);
						return pricelist;

					}
					return null;

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally {
					try {
						if (pstmt != null && !pstmt.isClosed())
							pstmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		} else {
			return null;
		}
		return null;

	}

	public FoodDataList getFoodOfPeriod(String startdate) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		FoodDataList foodlist = new FoodDataList();
		ArrayList<FoodData> Objectlist = new ArrayList<FoodData>();
		FoodData fooddata = null;

		int size;

		conn = connect();

		if (conn != null) {
			try {
				pstmt = conn.prepareStatement("SELECT * FROM food_table limit 5");
				rs = pstmt.executeQuery();

				while (rs.next()) {
					fooddata = new FoodData();
					fooddata.setFood_name(rs.getString(2));
					fooddata.setFood_material(rs.getString(3));
					fooddata.setMain_nutri(rs.getString(4));

					Objectlist.add(fooddata);
				}
				size = rs.getRow() - 1;
				foodlist.setSuccess(true);
				foodlist.setTotal_count(size);
				foodlist.setList(Objectlist);

				return foodlist;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} finally {
				try {
					if (pstmt != null && !pstmt.isClosed())
						pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			return null;
		}

		return null;

	}

	public void getPrice(String date) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		MakeFoodTable foodtable = new MakeFoodTable();
		ArrayList<String> vegetablelist;
		ArrayList<String> meatlist;
		WeekMenu weekmenu = new WeekMenu();

		ArrayList<PriceDTO> priceList = new ArrayList<PriceDTO>();
		PriceDTO price;

		int category = 5;
		String startdate = null;

		int vegetabletemp[][] = new int[6][5];
		int meattemp[][] = new int[3][5];

		double vegetableRatio[] = new double[] { 0, 0, 0, 0, 0, 0 };
		double meatbleRatio[] = new double[] { 0, 0, 0 };

		int minvegetable[];
		int minmeatble[];

		if (date != null) {
			conn = connect();
			if (conn != null) {
				startdate = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6);

				try {
					int i = 0;

					pstmt = conn.prepareStatement("SELECT * FROM vegetableprice WHERE day>= ? limit 5");
					pstmt.setDate(1, Date.valueOf(startdate));

					rs = pstmt.executeQuery();

					while (rs.next()) {

						for (int j = 0; j < 6; j++) {

							vegetabletemp[j][i] = rs.getInt(2 + j);
						}
						i++;

					}

					i = 0;

					pstmt = conn.prepareStatement("SELECT * FROM meatprice WHERE day>= ? limit 5");
					pstmt.setDate(1, Date.valueOf(startdate));

					rs = pstmt.executeQuery();

					while (rs.next()) {

						for (int j = 0; j < 3; j++) {
							meattemp[j][i] = rs.getInt(2 + j);
						}
						i++;

					}
					vegetableRatio = foodtable.calcVegetablePrice(vegetabletemp);
					meatbleRatio = foodtable.calcMeatPrice(meattemp);

					minvegetable = foodtable.getMinIndex(vegetableRatio);
					minmeatble = foodtable.getMinIndex(meatbleRatio);

					String[] meatfoodlist = new String[3];

					meatfoodlist[0] = Meat_Value.getMeatName(minmeatble[0]);
					meatfoodlist[1] = Meat_Value.getMeatName(minmeatble[1]);
					meatfoodlist[2] = Meat_Value.getMeatName(minmeatble[2]);

					String[] vegetablefoodlist = new String[5];
					vegetablefoodlist[0] = Vegetable_Value.getVegetableName(minvegetable[0]);
					vegetablefoodlist[1] = Vegetable_Value.getVegetableName(minvegetable[1]);
					vegetablefoodlist[2] = Vegetable_Value.getVegetableName(minvegetable[2]);
					vegetablefoodlist[3] = Vegetable_Value.getVegetableName(minvegetable[3]);
					vegetablefoodlist[4] = Vegetable_Value.getVegetableName(minvegetable[4]);

					pstmt = conn.prepareStatement(
							"select * from food_table where food_material=? or food_material=?or food_material=?or food_material=?or food_material=?");
					pstmt.setString(1, vegetablefoodlist[0]);
					pstmt.setString(2, vegetablefoodlist[1]);
					pstmt.setString(3, vegetablefoodlist[2]);
					pstmt.setString(4, vegetablefoodlist[3]);
					pstmt.setString(5, vegetablefoodlist[4]);
					
					rs = pstmt.executeQuery();

					long seed = System.currentTimeMillis();
					Random rand = new Random(seed);
					vegetablelist = new ArrayList<String>();
					while (rs.next()) {
						vegetablelist.add(rs.getString(2));
					}
					int rssize = rs.getRow() - 1;

					pstmt = conn.prepareStatement(
							"select * from food_table where food_material=? or food_material=? or food_material=?");
					pstmt.setString(1, meatfoodlist[0]);
					pstmt.setString(2, meatfoodlist[1]);
					pstmt.setString(3, meatfoodlist[2]);

					rs = pstmt.executeQuery();

					meatlist = new ArrayList<String>();
					while (rs.next()) {
						meatlist.add(rs.getString(2));
					}
					rssize = rs.getRow() - 1;

					for (int k = 0; k < 5; k++) {

						Menu menu = new Menu();
						menu.setDate("aaa");
						menu.setMeatfood(meatlist.get(rand.nextInt(meatlist.size())));
						menu.setVegetablefood1(vegetablelist.get(rand.nextInt(vegetablelist.size())));
						menu.setVegetablefood2(vegetablelist.get(rand.nextInt(vegetablelist.size())));
						weekmenu.getWeekmenu().add(menu);
					}
					for (int k = 0; k < 5; k++) {
						System.out.println(weekmenu.getWeekmenu().get(k).getMeatfood());
						System.out.println(weekmenu.getWeekmenu().get(k).getVegetablefood1());
						System.out.println(weekmenu.getWeekmenu().get(k).getVegetablefood2());
						System.out.println("---------------------------------------");
					}

					// int iValue = rand.nextInt(rssize);

				} catch (SQLException e) {
					e.printStackTrace();
				}

			}

		}

	}

}
