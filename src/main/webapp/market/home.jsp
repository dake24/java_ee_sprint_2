<%@ page import="java.util.List" %>
<%@ page import="model.Laptop" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
            crossorigin="anonymous"></script>
</head>
<body>

<%@include file="vendor/navbar.jsp" %>
<br>
<div class="container">
    <table class="table table-hover">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Model</th>
            <th scope="col">Count</th>
            <th scope="col">Price</th>
            <th scope="col">Delete</th>
        </tr>
        </thead>
        <tbody>

        <%
            List<Laptop> laptops = (List<Laptop>) request.getAttribute("nouts");
            for (Laptop l : laptops) {
        %>
        <tr>
            <th scope="row"><%=l.getId()%>
            </th>
            <td><%=l.getModel()%>
            </td>
            <td><%=l.getCount()%>
            </td>
            <td><%=l.getPrice() + " KZT"%>
            </td>
            <td>
                <button type="button" class="btn btn-danger" data-bs-toggle="modal"
                        data-bs-target="#deleteModal<%=l.getId()%>">
                    Delete
                </button>

                <div class="modal fade" id="deleteModal<%=l.getId()%>" tabindex="-1" aria-labelledby="deleteModalLabel"
                     aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <form action="delete" method="post">
                                <input hidden name="id" value="<%=l.getId()%>">
                                <div class="modal-header">
                                    <h1 class="modal-title fs-5" id="deleteModalLabel">
                                        <%=l.getId() + " " + l.getModel()%>
                                    </h1>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    Do you want to delete product ?
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close
                                    </button>
                                    <button type="submit" class="btn btn-danger">
                                        Delete
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </td>
        </tr>
        <%
            }
        %>

        </tbody>
    </table>
</div>
</body>
</html>
