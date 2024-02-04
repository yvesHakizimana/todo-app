<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>User Management Application</title>

    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">

</head>

</head>
<body>
<header style="margin-bottom: 30px">
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: #41c5ad">
        <div>
            <h1>Todo App</h1>
        </div>

        <ul class="navbar-nav">
            <li>
                <a href="fetch-todo"
                   class="nav-link">Todos
                </a>
            </li>
        </ul>

        <ul class="navbar-nav navbar-collapse justify-content-end">
            <li><a href="logout-user"
                   class="nav-link">Logout</a></li>
        </ul>
    </nav>
</header>
<div class="container col-md-5">
    <%
        String username = (String) session.getAttribute("username");
        if(username == null){
            response.sendRedirect("register.jsp");
        }
    %>
    <div class="card">
        <div class="card-body">
            <c:if test="${todo != null}">
            <form action="update-todo" method="post">
                </c:if>
                <c:if test="${todo == null}">
                <form action="register-todo" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${todo != null}">
                                Edit Todo
                            </c:if>
                            <c:if test="${todo == null}">
                                Add New Todo
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${todo != null}">
                        <input
                                type="hidden"
                                name="id"
                                value="<c:out value='${todo.id}' />" />
                    </c:if>

                    <fieldset class="form-group">
                        <label>Todo Title</label>
                        <input type="text"
                               value="<c:out value='${todo.title}' />"
                               class="form-control"
                               name="title"
                               required="required"
                               minlength="5">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Todo Decription</label>
                        <input type="text"
                               value="<c:out value='${todo.description}' />"
                               class="form-control"
                               name="desc"
                               minlength="5">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Todo Status</label>
                        <select class="form-control"
                                name="isDone">
                        <option value="false">In Progress</option>
                        <option value="true">Complete</option>
                    </select>
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Todo Target Date</label>
                        <input type="date"
                               value="<c:out value='${todo.targetDate}' />"
                               class="form-control"
                               name="targetDate"
                               required="required">
                    </fieldset>

                    <button type="submit"
                            class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>

<jsp:include page="./foooter.jsp"/>
</body>
</html>