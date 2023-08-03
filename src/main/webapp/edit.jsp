<%@ page import="db.Items" %>
<%@ page import="db.Countries" %>
<%@ page import="java.util.ArrayList" %>
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
    <div class="col-sm-6 offset-3">
      <%
        Items item=(Items) request.getAttribute("item");
        if(item!=null){
      %>
        <%
          String success=request.getParameter("success");
          if(success!=null){
        %>
        <div class="alert alert-success" role="alert">
          Item saved Successfully!
        </div>
        <%
          }
        %>
        <%
          String error=request.getParameter("error");
          if(error!=null){
        %>
        <div class="alert alert-danger" role="alert">
          Something went wrong!
        </div>
        <%
          }
        %>
        <form action="/edit" method="post">
          <input type="hidden" name="id" value="<%=item.getId()%>">
          <div class="form-group">
            <label>Name: <input type="text" name="name" class="form-control" value="<%=item.getName()%>"></label>
          </div>
          <div class="form-group">
            <label>Amount: <input type="number" name="amount" class="form-control" value="<%=item.getAmount()%>"></label>
          </div>
          <div class="form-group">
            <label>Price: <input type="number" name="price" class="form-control" value="<%=item.getPrice()%>"></label>
          </div>
          <div class="form-group">
            <label>Manufacturer:</label>
            <select class="form-select mb-2" name="manufacturer_id">
              <%
                ArrayList<Countries> countries=(ArrayList<Countries>) request.getAttribute("countries");
                if(countries!=null){
                  for(Countries c:countries){
              %>
              <option value="<%=c.getId()%>" <%if(c.getId()==item.getManufacturer().getId()){
                out.print("selected");
              }%>>
                <%=c.getName() + "/"+c.getShortName()%>
              </option>
              <%
                  }
                }
              %>
            </select>
          </div>
          <div class="form-group">
            <button class="btn btn-success">SAVE ITEM</button>
            <button type="button" class="btn btn-danger float-right" data-bs-toggle="modal" data-bs-target="#deleteItemModal">
              DELETE ITEM
            </button>
          </div>
        </form>
      <div class="modal fade" id="deleteItemModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <form action="/delete" method="post">
                <input type="hidden" name="id" value="<%=item.getId()%>">
                <div class="modal-header">
                  <h1 class="modal-title fs-5" id="staticBackdropLabel">Confirm Delete Process</h1>
                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                  Are you sure?
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">No</button>
                  <button class="btn btn-danger">Yes</button>
                </div>
            </form>
          </div>
        </div>
      </div>
        <%
          }
        %>
    </div>
  </div>
</div>
</body>
</html>
