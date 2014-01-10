package org.sourcelesslight.actions;

import java.util.List;
import org.sourcelesslight.actions.interfaces.LoginRequired;
import org.sourcelesslight.model.User;
import org.sourcelesslight.services.UserService;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller
public class FriendFinderAction extends ActionSupport implements LoginRequired {

	private static final long serialVersionUID = 7089836153796088129L;
	
	private List<User> friendList;
	private UserService userService;
	private String keyword;
	
//	private String string1 = "A";
//	private String[] stringarray1 = {"A1","B1"};
//	private int number1 = 123456789;
//	private int[] numberarray1 = {1,2,3,4,5,6,7,8,9};
//	private List<String> lists = new ArrayList<String>();
//	private Map<String, String> maps = new HashMap<String, String>();
	
	public FriendFinderAction()
	{
//		lists.add("list1");
//		lists.add("list2");
//		lists.add("list3");
//		lists.add("list4");
//		lists.add("list5");
// 
//		maps.put("key1", "value1");
//		maps.put("key2", "value2");
//		maps.put("key3", "value3");
//		maps.put("key4", "value4");
//		maps.put("key5", "value5");
	}
	
	public String execute()
	{
		return SUCCESS;
	}
	
	public String executeSearch()
	{
		if(keyword!=null)
		{
			friendList = userService.searchUser(keyword);
		}
			return SUCCESS;
	}

	public List<User> getFriendList() {
		return friendList;
	}

	public void setFriendList(List<User> friendList) {
		this.friendList = friendList;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	

}
