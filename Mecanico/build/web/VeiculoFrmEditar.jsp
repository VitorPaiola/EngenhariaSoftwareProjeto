<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="v"%>
<html> 
    <head>
        <title>Oficina Mecânica</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">

        <style>

            .forma {
                border: 5px solid #5882FA; 
                background-color: #A9F5D0; 
                transition: 1s; 
                cursor: pointer;
            }

            .container .forma:hover {
                border: 5px solid #A9F5D0; 
                background-color: #5882FA; 
                transition: 1s;
                border-radius: 30px;
            }

            tr a.link {
                text-decoration: none;
                font-size: 1rem;
                color: #01A9DB;
            }

            .container .estilo {
                box-shadow: 0 0 10px green;
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
        <div class="container col-md-5">
            <div class="text-center">
                <div class="card-body forma">
                    <form action="<%=request.getContextPath()%>/veiculo?update" method="post">
                        <caption>
                            <h2 style="text-align: center;">Editar Veiculo</h2>
                            <input type="hidden" name="idVeiculo" value="<v:out value='${veiculo.idVeiculo}' />" />
                            <fieldset class="form-group p-1">
                                <label>IDRevisao</label> <input type="text"
                                                           value="<v:out value='${veiculo.idRevisao}' />" class="form-control"
                                                           name="idRevisao" required="required">
                            </fieldset>
                            <fieldset class="form-group p-1">
                                <label>IDDono</label> <input type="text"
                                                               value="<v:out value='${veiculo.idDono}' />" class="form-control"
                                                               name="idDono">
                            </fieldset>
                            <fieldset class="form-group p-1">
                                <label>Placa</label> <input type="text"
                                                               value="<v:out value='${veiculo.placa}' />" class="form-control"
                                                               name="placa">
                            </fieldset>
                            <fieldset class="form-group p-1">
                                <label>Ano</label> <input type="text"
                                                               value="<v:out value='${veiculo.ano}' />" class="form-control"
                                                               name="ano">
                            </fieldset>
                            <fieldset class="form-group p-1">
                                <label>RENAVEM</label> <input type="text"
                                                               value="<v:out value='${veiculo.RENAVEM}' />" class="form-control"
                                                               name="RENAVEM">
                            </fieldset>
                            <fieldset class="form-group p-1">
                                <label>Modelo</label> <input type="text"
                                                               value="<v:out value='${veiculo.modelo}' />" class="form-control"
                                                               name="modelo">
                            </fieldset>
                            <fieldset class="form-group p-1">
                                <label>Marca</label> <input type="text"
                                                               value="<v:out value='${veiculo.marca}' />" class="form-control"
                                                               name="marca">
                            </fieldset>
                            <button type="submit" class="btn btn-success estilo">Atualizar</button>
                    </form>
                </div>
            </div>
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

