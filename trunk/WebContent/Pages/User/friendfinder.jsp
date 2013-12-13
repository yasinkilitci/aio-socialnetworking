<%@ taglib prefix="s" uri="/struts-tags" %>

<table>
<s:iterator value="friendList" var="eachUser">
	<tr>
	      <td>
	     	 <s:property value="#eachUser.username"/>
	      </td>
	      <td>
	     	 <s:property value="#eachUser.firstname"/>
	      </td>
	      <td>
	     	 <s:property value="#eachUser.lastname"/>
	      </td>
  </tr>
</s:iterator>
</table> 

<span>Page has come!</span>