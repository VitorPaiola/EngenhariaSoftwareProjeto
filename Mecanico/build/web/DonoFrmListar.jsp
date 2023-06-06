<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="d"%>

<%@page import="Modelo.Dono"%>
<%@page import="java.util.ListIterator"%>
<%@page import="DAO.DonoDAO"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Oficina Mecânica</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">

        <style>

            tr a.link {
                text-decoration: none;
                font-size: 1rem;
                color: #01A9DB;
            }

            .container .estilo {
                box-shadow: 0 0 20px green;
            }

            .container .link:hover {
                color: #084B8A;
            }

            .header a:hover {
                color: #000;
                box-shadow: inset 0 0 20px #FFF;
                border-radius: 30px;
            }

            button {
                margin-top: 10px;
            }

            .header {
                display: flex;
                font-size: 1rem;
                margin-left: 15rem;
                justify-content: space-around;
                width: 100%;
            }

            .logo {
                font-size: 1.1rem;
                color: #FFF;
                font-weight: 200rem;
                cursor: default;
                text-decoration: none;
                position: fixed;
                line-height: 2px;
            }

            .footer {
                background-color: #D8D8D8;
                color: #000;
                width: 100%;
                left: 0;
                bottom: 0;
                display: flex;
                position: fixed;
                justify-content: space-around;
                line-height: 3rem;
                font-style: italic;
                font-weight: 900 - Black;
                
            }


        </style>

    </head>
    <body>
        <header>
            <nav class="navbar navbar-expand-md navbar-dark"
                 style="padding: 15px 0px; background: #5882FA;">
                <div style = "padding: 10px; font-weight: bold;">                    
                    <a href="index.jsp" class="logo">Oficina Mecânica</a>
                </div>

                <div class="header">

                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/dono?list"
                           class="nav-link">Dono</a></li>
                    </ul>

                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/mecanico?list"
                               class="nav-link">Mecânico</a></li>
                    </ul>

                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/revisao?list"
                               class="nav-link">Revisão</a></li>
                    </ul>

                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/veiculo?list"
                               class="nav-link">Veiculo</a></li>
                    </ul>

                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/veiculopesado?list"
                               class="nav-link">VeiculoPesado</a></li>
                    </ul>

                </div>

            </nav>
        </header>
        <br>
        <div class="container">
            <h3 class="text-center">Lista de Donos</h3>
            <hr>
            <div class="container text-left">
                <a href="<%=request.getContextPath()%>/dono?new" class="btn btn-success estilo">Incluir</a>
            </div>
            <br>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nome</th>
                        <th>CNH</th>
                        <th>Endereço</th>
                        <th>Telefone</th>
                        <th>Ações</th>
                    </tr>
                </thead>
                <tbody>                                          
                    <!-- listDono é uma coleção de todos os donos e
                    foi recebida por esta página jsp como um atributo da request
                    envida pelo DonoServlet dentro do método listDono  -->
                    <d:forEach var="dono" items="${listDono}">
                        <tr>
                            <td><d:out value="${dono.idDono}" /></td>
                            <td><d:out value="${dono.nome}" /></td>
                            <td><d:out value="${dono.CNH}" /></td>
                            <td><d:out value="${dono.endereco}" /></td>
                            <td><d:out value="${dono.telefone}" /></td>
                            <td>
                                <a href="<%=request.getContextPath()%>/dono?edit=<d:out value='${dono.idDono}' />" class="link">Editar</a>
                                &nbsp;&nbsp;&nbsp;&nbsp; 
                                <a href="<%=request.getContextPath()%>/dono?delete=<d:out value='${dono.idDono}' />" class="link">Remover</a></td>
                        </tr>
                    </d:forEach>
                </tbody>
            </table>
        </div>

        <div class="footer">
            <ul class="navbar-nav">
                <li>Gabriel Lucizano</li>
            </ul>

            <ul class="navbar-nav">
                <li>Victor Amadeu</li>
            </ul>

            <ul class="navbar-nav">
                <li>Vitor Paiola</li>
            </ul>
        </div>

    </body>
</html>

