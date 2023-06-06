<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="m"%>

<%@page import="Modelo.Mecanico"%>
<%@page import="java.util.ListIterator"%>
<%@page import="DAO.MecanicoDAO"%>

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
            <h3 class="text-center">Lista de Mecânicos</h3>
            <hr>
            <div class="container text-left">
                <a href="<%=request.getContextPath()%>/mecanico?new" class="btn btn-success estilo">Incluir</a>
            </div>
            <br>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>CREA</th>
                        <th>Nome</th>
                        <th>Endereço</th>
                        <th>Celular</th>
                        <th>CPF</th>
                        <th>Ações</th>
                    </tr>
                </thead>
                <tbody>                                          
                    <!-- listMecanico é uma coleção de todos os mecanicos e
                    foi recebida por esta página jsp como um atributo da request
                    envida pelo MecanicoServlet dentro do método listMecanico  -->
                    <m:forEach var="mecanico" items="${listMecanico}">
                        <tr>
                            <td><m:out value="${mecanico.idMeca}" /></td>
                            <td><m:out value="${mecanico.CREA}" /></td>
                            <td><m:out value="${mecanico.nome}" /></td>
                            <td><m:out value="${mecanico.endereco}" /></td>
                            <td><m:out value="${mecanico.celular}" /></td>
                            <td><m:out value="${mecanico.CPF}" /></td>
                            <td>
                                <a href="<%=request.getContextPath()%>/mecanico?edit=<m:out value='${mecanico.idMeca}' />" class="link">Editar</a>
                                &nbsp;&nbsp;&nbsp;&nbsp; 
                                <a href="<%=request.getContextPath()%>/mecanico?delete=<m:out value='${mecanico.idMeca}' />" class="link">Remover</a></td>
                        </tr>
                    </m:forEach>
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

