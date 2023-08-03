<%@ page import="java.util.ArrayList" %>
<%@ page import="db.Items" %>
<%@ page import="db.Countries" %>
<%@ page import="java.awt.image.DataBuffer" %>
<%@ page import="db.DBManager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
  <%@include file="vendor/head.jsp"%>
</head>
<body>
<%@include file="vendor/navbar.jsp"%>
<div class="container">
  <div class="row mt-5">
    <div class="col sm-6 offset-3">
      <%
        String success=request.getParameter("success");
        if(success!=null){
      %>
      <div class="alert alert-success" role="alert">
        Item Add Successfully!
      </div>
      <%
        }
      %>
      <form action="/additem" method="post">
        <div class="form-group">
          <label>Name: <input type="text" name="name" class="form-control"></label>
        </div>
        <div class="form-group">
          <label>Amount: <input type="number" name="amount" class="form-control"></label>
        </div>
        <div class="form-group">
          <label>Price: <input type="number" name="price" class="form-control"></label>
        </div>
        <div class="form-group">
          <label>Manufacturer:</label>
          <select class="form-select mb-2" name="manufacturer_id">
            <%
              ArrayList<Countries> countries=(ArrayList<Countries>) request.getAttribute("countries");
              if(countries!=null){
                for(Countries c:countries){
            %>
            <option value="<%=c.getId()%>">
              <%=c.getName() + "/"+c.getShortName()%>
            </option>
            <%
                }
              }
            %>
          </select>
        </div>
        <div class="form-group">
          <button class="btn btn-success">ADD ITEM</button>
        </div>
      </form>
    </div>
  </div>
</div>
</body>
</html>
