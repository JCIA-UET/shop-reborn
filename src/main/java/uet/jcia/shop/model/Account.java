package uet.jcia.shop.model;

/**
 * Account Entity: id, username, password
 * realname, email, type, address 
 * @author cuong
 *
 */
public class Account {
	/**
	 * account id
	 */
	private int id;
	
	/**
	 * account username and account password
	 * are used for login
	 */
	private String username;
	private String password;
	
	/**
	 * the real name of user
	 */
	private String realName;
	
	/**
	 * user phone
	 */
	private String phone;
	
	/**
	 * customer city
	 */
	private String city;
	
	/**
	 * customer address
	 */
	private String address;
	
	/**
	 * type of account
	 */
	private AccountType accountType;

	public Account(int id, String username, String password, String realName, String phone, String city, String address,
			AccountType accountType) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.realName = realName;
		this.phone = phone;
		this.city = city;
		this.address = address;
		this.accountType = accountType;
	}
	
	public Account(String username, String password, String realName, String phone, String city, String address,
			AccountType accountType) {
		this.username = username;
		this.password = password;
		this.realName = realName;
		this.phone = phone;
		this.city = city;
		this.address = address;
		this.accountType = accountType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	
}