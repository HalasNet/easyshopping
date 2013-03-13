<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<!-- saved from url=(0037)http://2school.wygk.cn/admin/left.htm -->
<HTML><HEAD><TITLE>左栏帮助</TITLE>
<META http-equiv=Content-Type content="text/html; charset=gb2312"><LINK 
href="../styles/style_left.css" type=text/css rel=stylesheet>
<META content="MSHTML 6.00.2900.5726" name=GENERATOR></HEAD>
<BODY>
<TABLE height="100%" cellSpacing=0 cellPadding=0 width="100%" border=0>
  <TBODY>
  <TR>
    <TD style="PADDING-TOP: 10px" vAlign=top>
      <TABLE class=alpha>
        <TBODY>
        <TR>
          <TD class=menu id=menubar vAlign=top>
          <ol>
        <sec:authorize ifAnyGranted="authority_mgr" >
            <LI><A href="authority" 
            target=frmright>权限管理</A> </LI>
        </sec:authorize>    
        <sec:authorize ifAnyGranted="role_mgr">
            <LI><A href="role" 
            target=frmright>角色管理</A> </LI>
        </sec:authorize>    
        <sec:authorize ifAnyGranted="user_mgr">
            <LI><A href="user" 
            target=frmright>人员管理</A> </LI>
        </sec:authorize>    
        <sec:authorize ifAnyGranted="product_mgr">
            <LI><A href="product" 
            target=frmright>商品管理</A> </LI>
        </sec:authorize>    
        <sec:authorize ifAnyGranted="product_kind_mgr">
            <LI><A href="product_category" 
            target=frmright>商品类别管理</A> </LI>
        </sec:authorize>    
          </ol>
           </TD></TR></TBODY></TABLE></TD></TR>
  </TBODY></TABLE></BODY></HTML>
