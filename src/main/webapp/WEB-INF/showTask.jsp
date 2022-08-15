<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- bootstrap css -->
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css"/>
    <!-- css -->
    <link rel="stylesheet" href="/css/style.css">
    <!-- bootstrap javascript -->
    <script defer src="/webjars/jquery/jquery.min.js"></script>
    <script defer src="/webjars/bootstrap/js/bootstrap.min.js"></script>

    <!-- javascript -->
    <script defer type="text/javascript" src="/js/script.js"></script>
    
    
    <title>New Project</title>
</head>
<body>
    <div class="d-flex align-items-center justify-content-between gap-3 p-3">
        <div class="home-button"><a class="btn btn-primary" href="/home">Home</a></div>
        <div class="d-flex justify-content-end"><a class="btn btn-danger" href="/logout">Logout</a></div>
    </div>

    <div class="container">
        <div class="row">
            <div class="col">
                <h3>${project.name}</h3>
                <h5>Project Lead: ${project.user.firstName} ${project.user.lastName}</h5> <hr/>
                <div class="card">
                    <h4 class="card-header text-primary p-4">Add a task ticket for this team: </h4>
                    <div class="card-body">    
                        <form:form action="/tasks/add" method="post" modelAttribute="task">
                            <form:input type="hidden" path="user" value="${userId}"></form:input>
                            <form:input type="hidden" path="project" value="${project.id}"></form:input>
                            <div class="mb-3">
                                <form:label path="message" class="form-label">message </form:label>
                                <form:input path="message" type="text" class="form-control" placeholder="message" />
                                <form:errors  class="text-danger" path="message" />
                            </div>
                            <div class="d-flex justify-content-end">
                                <input type="submit" value="Add" class="btn btn-primary">
                            </div>
                        </form:form>

                        
                    </div>
                </div>

                <h5 class="mt-3">Tasks</h5>  <hr/>
                <ul class="list-group overflow-auto mb-5" style="height: 200px;" >
                    <c:forEach var="task" items="${tasks}">
                        <li class="list-group-item">Added by ${task.user.firstName} at <fmt:formatDate value="${task.createdAt}" pattern="hh:mma MMM dd"/>: <br>
                            ${task.message}
                        </li>

                    </c:forEach>
                </ul>



                
            
            </div>
        </div>
    </div>
</body>
</html>