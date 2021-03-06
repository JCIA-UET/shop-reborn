package uet.jcia.shop.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Manager for Account entity
 * @author cuong
 *
 */
public class AccountManager {
	
	DBConnector dbconnector = DBConnector.getInstance();
	
	Connection conn = null;
	
	/**
	 * get account from db by username
	 * @param username
	 * @return account entity
	 */
	public Account getAccountByUsername(String username){
		ResultSet rs = null ;
		String sqlcommand = "select * from account where account.username = ?";
		PreparedStatement pts = null;
		Account account = null;
		try {
			Connection con = dbconnector.createConnection();
			pts = con.prepareStatement(sqlcommand);
			pts.setString(1, username);
			rs = pts.executeQuery();
			
			if (rs.next()) {
				int accountId = rs.getInt(1);
				String password = rs.getString(3);
				String realName = rs.getString(4);
				String phone = rs.getString(5);
				String city = rs.getString(6);
				String address = rs.getString(7);
				String accountTypeStr = rs.getString(8);
				
				AccountType accType = AccountType.valueOf(accountTypeStr); 
						
				account = new Account(accountId, username, password, realName,
						phone, city, address, accType);
			}
			
			con.close();
			return account;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * get account by id
	 * @param accountId account's id
	 * @return the account which owns that id
	 */
	public Account getAccountById(int accountId){
		ResultSet rs = null ;
		String sqlcommand = "select * from account where account.accountId = ?;";
		PreparedStatement pts = null;
		Account account = null;
		try {
			Connection con = dbconnector.createConnection();
			pts = con.prepareStatement(sqlcommand);
			pts.setInt(1, accountId);
			rs = pts.executeQuery();
			
			if (rs.next()) {
				String username = rs.getString(2);
				String password = rs.getString(3);
				String realName = rs.getString(4);
				String phone = rs.getString(5);
				String city = rs.getString(6);
				String address = rs.getString(7);
				String accountTypeStr = rs.getString(8);
				AccountType accType = AccountType.valueOf(accountTypeStr);
				
				account = new Account(accountId, username, password, realName,
						phone, city, address, accType);

			}
			
			con.close();
			return account;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * authenticate user
	 * @param username input username
	 * @param password input password
	 * @return account if user's account is valid
	 * false if otherwise
	 */
	public Account authenticate(String username , String password){
		Account account = getAccountByUsername(username);
		if (account == null ||
			!account.getPassword().equals(password)) {
			return null;
		}
		return account;
	}
	
	
	/**
	 * add new Account into db
	 * @param newAccount the new account object
	 * @return account id if success, 0 if fail
	 */
	public int addAccount(Account newAccount){
		String sqlCommand = "insert into account (username, password, realName, phone, city, address, accountType) values (?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pst = null;
		int id = 0;
		
		try {
			String accountTypeStr = newAccount.getAccountType().name();
			conn = dbconnector.createConnection();
			pst = conn.prepareStatement(sqlCommand, Statement.RETURN_GENERATED_KEYS);

			pst.setString(1,newAccount.getUsername());
			pst.setString(2,newAccount.getPassword());
			pst.setString(3, newAccount.getRealName());
			pst.setString(4, newAccount.getPhone());
			pst.setString(5, newAccount.getCity());
			pst.setString(6, newAccount.getAddress());
			pst.setString(7, accountTypeStr);
			
			pst.executeUpdate();
			
			// get generate key
			ResultSet pKeys = pst.getGeneratedKeys();
			if (pKeys.next()) {
				id = pKeys.getInt(1);
			}
			
			conn.close();
			return id;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	public boolean removeAccountByUsername(String username){
 		return true;
	}
	public boolean updateAccount(int id, Account account){
		String sqlCommand = "UPDATE account Set realName= ? , phone=? , city = ? , address= ? , password= ? WHERE accountId = ?; ";
		PreparedStatement pst ; 
		conn = dbconnector.createConnection();
		try {
			pst = conn.prepareStatement(sqlCommand);
			pst.setString(1, account.getRealName());
			pst.setString(2, account.getPhone());
			pst.setString(3, account.getCity());
			pst.setString(4, account.getAddress());
			pst.setString(5, account.getPassword());
			pst.setInt(6, id);
			pst.executeUpdate();
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
	}

	public boolean updateEmployee (int id, Account account){
		String sqlCommand = "UPDATE account Set username=?, password=?,"
				+ " realName= ? , phone=? , city = ? , address= ?, accountType= ? WHERE accountId = ?; ";
		PreparedStatement pst ; 
		conn = dbconnector.createConnection();
		try {
			pst = conn.prepareStatement(sqlCommand);
			pst.setString(1, account.getUsername());
			pst.setString(2, account.getPassword());
			pst.setString(3, account.getRealName());
			pst.setString(4, account.getPhone());
			pst.setString(5, account.getCity());
			pst.setString(6, account.getAddress());
			pst.setString(7, account.getAccountType().name());
			pst.setInt(8, id);
			pst.executeUpdate();
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public List<Account> getAllAccounts(AccountType type){
		List<Account> list = new ArrayList<>();
		String sqlCommand =
				"select * from account where account.accountType = '" +
				type.name() +
				"' order by username;";
		PreparedStatement pst ; 
		ResultSet rs = null ; 
		conn = dbconnector.createConnection();
		try {
			pst = conn.prepareStatement(sqlCommand);
			rs = pst.executeQuery();
			while(rs.next()){
				int accountId = rs.getInt(1);
				String username = rs.getString(2);
				String password = rs.getString(3);
				String realName = rs.getString(4);
				String phone = rs.getString(5);
				String city = rs.getString(6);
				String address = rs.getString(7);
				String accountTypeStr = rs.getString(8);
				
				AccountType accType = AccountType.valueOf(accountTypeStr); 
						
				 Account account = new Account(accountId, username, password, realName,
						phone, city, address, accType);
				 list.add(account);
			}
			conn.close();
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}	
	}
	
	public boolean removeAccountById(int id){
		if (getAccountById(id) == null) {
			return false;
		}
		try {
			conn = dbconnector.createConnection();
			String query = "delete from account " +
							"where accountId= ?";
			
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, id);
			statement.execute();

			conn.close();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public int countItems() {
		conn = dbconnector.createConnection();
		int count = -1;
		
		try {
			PreparedStatement stt = conn.prepareStatement("SELECT COUNT(*) FROM `account` WHERE accountType = \'CUSTOMER\'");
			ResultSet rs = stt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}

			conn.close();
			return count;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
			
		}
	}
	
//	public static void main(String[] args) {
//		AccountManager test = new AccountManager();
//		List<Account> test1 = test.getAllCustomer();
//		for (Account account : test1) {
//			System.out.println(account.getUsername());
//		}
//	}
}

	