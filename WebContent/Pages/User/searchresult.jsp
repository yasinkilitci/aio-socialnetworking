<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<span>Results: </span><s:property value="%{friendList.size()}"/>
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