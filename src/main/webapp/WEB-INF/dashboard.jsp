<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
import="java.util.Date" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!-- for forms -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!--  mgiht show error-->
<%@ page isErrorPage="true" %>  


<!DOCTYPE html>
<html>

<head>
    <!-- bootstrap -->
    <!-- <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" /> -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"  crossorigin="anonymous">
    <!-- regularcss -->
    <link rel="stylesheet" type="text/css" href="/css/style.css">

    <!-- bootstrap js -->
    <!-- <script src="/webjars/jquery/jquery.min.js"></script> -->
    <!-- <script src="/webjars/bootstrap/js/bootstrap.min.js"></script> -->

    <!-- regular js -->
    <script type="text/javascript" src="/javascript/script.js"></script>

    <meta charset="UTF-8">
    <title>Home</title>
</head>

<body>

    <div class="d-flex align-items-center justify-content-between gap-3 p-3">
        <div class="home-button"><a class="btn btn-primary" href="/home">Home</a></div>
        <div class="d-flex justify-content-end"><a class="btn btn-danger" href="/logout">Logout</a></div>
    </div>
    
    <div class="container">
        <div class="row">
            <div class="col">
                <h4>Welcome! ${user.firstName} ${user.lastName}</h4>


                <div class="d-flex justify-content-between align-items-center">
                    <h5 class="all-projects">All Project</h5>
                    <a href="/projects/add" class="btn btn-primary new-project">+ New Project</a>
                </div>
                <table class="table">
                    <thead>
                        <tr>
                            <th>Project</th>
                            <th>Team Lead</th>
                            <th>Due Date</th>
                            <th>Actions</th>
                        </tr>
                    </thead>


                    <tbody>
                        <c:forEach var="project" items="${projects}">
                            <c:choose>
                                <c:when  test="${!user.id.equals(project.user.id) && !(project.members.contains(user))}">
                                    <tr>
                                        <td><a href="/projects/${project.id}">${project.name}</a></td>
                                        <td>${project.user.firstName}</td>
                                        <td><fmt:formatDate value="${project.projectDate}" pattern="MMM dd"/></td>
                                        <td class="d-flex justify-content-around align-items-center">
                                            <form action="/projects/addConnect" method="post">
                                                <input type="hidden" name="userId" value="${user.id}">
                                                <input type="hidden" name="projectId" value="${project.id}">
                                                <input class="btn btn-primary" type="submit" value="Join Team">
                                            </form>
                                        </td>
                                    </tr>
                                </c:when>
                                <c:otherwise>
                                </c:otherwise>
                                
                            </c:choose>
                            

                        </c:forEach>
                    </tbody>
                </table>


                <div class="d-flex justify-content-between align-items-center">
                    <h5 class="all-projects">My Project</h5>
                </div>
                <table class="table">
                    <thead>
                        <tr>
                            <th>Project</th>
                            <th>Team Lead</th>
                            <th>Due Date</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="project" items="${projects}">


                            <c:choose>
                                <c:when  test="${!(!user.id.equals(project.user.id)  && !(project.members.contains(user)))}">
                                    <tr>
                                        <td><a href="/projects/${project.id}">${project.name}</a></td>
                                        <td>${project.user.firstName}</td>
                                        <td><fmt:formatDate value="${project.projectDate}" pattern="MMM dd"/></td>
        
        
                                                <td class="d-flex justify-content-around align-items-center">
                                                    <c:choose>
                                                            <c:when test="${user.id == project.user.id}">
                                                                <div class="edit-button">
                                                                    <a class="btn btn-primary" href="/projects/${project.id}/edit">Edit</a>
                                                                </div>
                                                                <div class="delete-button">
                                                                    <form action="/projects/${project.id}" method="post">
                                                                        <input type="hidden" name="_method" value="delete">
                                                                        <input class="btn btn-danger" type="submit" value="Delete">
                                                                    </form>
                                                                </div>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <form action="/projects/removeConnect" method="post">
                                                                    <input type="hidden" name="userId" value="${user.id}">
                                                                    <input type="hidden" name="projectId" value="${project.id}">
                                                                    <input class="btn btn-warning" type="submit" value="Leave Team">
                                                                </form>
                                                            </c:otherwise>
                                                        </c:choose>
                                                </td>
                                    </tr>

                                
                                </c:when>
                            </c:choose>
                        </c:forEach>
                    </tbody>
                </table>
                
                
            </div>
        </div>
    </div>
</body>

</html>