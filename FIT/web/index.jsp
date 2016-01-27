<%@page import="javax.persistence.Persistence"%>
<%@page import="dao.UsuarioJpaController"%>
<%@page import="helper.Session"%>
<%@page import="model.Usuario"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    Usuario user = new Session(request, response).findCookie();
    if (user != null){
        /*  ANTES DISSO TEMOS Q VERIFICAR SE O CADASTRO DO USUARIO ESTÁ COMPLETO
            SE ESTIVER MANDAMOS PRO TEMPLATE PADRAO
            SENAO MANDAMOS PRA CONCLUSAO DE CADASTRO */       
        RequestDispatcher rd = request.getRequestDispatcher("basic-template.jsp");
        request.setAttribute("page", "cadastro");
        
        // PROCURANDO COOKIE
        Cookie[] cookies = request.getCookies();
        String email = "";
        for (Cookie c : cookies) {
            if (c.getName().equals("fitLogin")) {
                email = c.getValue();
            }
        }
        
        user = new UsuarioJpaController(Persistence.createEntityManagerFactory("FITPU")).checkEmail(email);
        request.setAttribute("usuario", user);
        rd.forward(request, response);
    }    
%>
<!DOCTYPE html>
<html>
    <jsp:include page="head.jsp" />    
    <body>
        <div class="home-global">
            <!-- HOME HEADER -->
            <div class="container-fluid home-header">
                <div class="container header">
                    <div class="header-item">
                        <i class="fa fa-question-circle"></i>Como funciona
                    </div>
                    <div class="header-item">
                        <i class="fa fa-info-circle"></i>Sobre
                    </div>
                    <div data-id="login" class="header-item">
                        <i class="fa fa-flag"></i>Explore nossa comunidade / Login
                    </div>
                    <div data-id="login-content" class="login-content">
                        <form name="login-form" action="login" method="POST">
                            <div class="col-lg-6">
                                <input name="log-email" type="text" class="form-control" placeholder="Email">
                            </div>
                            <div class="col-lg-6">
                                <input name="log-pass" type="password" class="form-control" placeholder="Senha">
                            </div>
                            <div class="col-lg-6">
                                <input id="log-keep" name="log-keep" type="checkbox"><label for="log-keep">Manter conectado</label>
                            </div>
                            <div class="col-lg-6">
                                <div class="login-btn" data-id="submit-home-login">Conectar <span class="glyphicon glyphicon-menu-right"></span></div>                                
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <!-- //HOME HEADER -->
            <!-- HOME BRAND -->
            <div class="container-fluid brand">
                <div class="container brand-content">
                    <img src="img/home-logo.png">
                    <div class="brand-text">Seu espaço fitness na internet.</div>
                    <div class="brand-buttons">
                        <button class="btn btn-primary"><i class="fa fa-facebook"></i> Conecte-se com Facebook</button>
                        <button class="btn btn-success"><i class="fa fa-google"></i> Conecte-se com Google</button>
                    </div>
                </div>
            </div>
            <!-- //HOME BRAND -->
            <!-- HOME REGISTER FORM -->
            <div class="container-fluid form">
                <form method="POST" name="home-register" data-react="0" action="cadastro">
                    <div class="container form-content">
                        <div class="form-title">Seja bem-vindo!</div>
                        <div class="form-minus">O cadastro é rápido, nós prometemos :)</div>
                        <div class="col-lg-6 col-md-6">
                            <input name="reg-name" type="text" class="form-control" placeholder="Nome" data-name="Nome" value="Maycon">
                        </div>
                        <div class="col-lg-6 col-md-6">
                            <input name="reg-aftername" type="text" class="form-control" placeholder="Sobrenome" data-name="Sobrenome" value="Teixeira">
                        </div>
                        <div class="col-lg-6 col-md-6">
                            <input name="reg-email" type="text" class="form-control" placeholder="E-mail" data-name="Email" value="tmaycon1@gmail.com">
                        </div>
                        <div class="col-lg-6 col-md-6">
                            <input name="reg-pass" type="password" class="form-control" placeholder="Senha" data-name="Senha" value="abc123">
                        </div>
                        <div class="col-lg-6 col-md-6">
                            <div class="col-lg-12">
                                <span class="label">Data de nascimento</span>
                            </div>    
                            <div class="col-lg-4">
                                <select name="reg-year" class="form-control" data-name="birthdate">
                                    <option value="0">Ano</option>
                                    <option value=2016>2016</option>
                                    <option value=2015>2015</option>
                                    <option value=2014>2014</option>
                                    <option value=2013>2013</option>
                                    <option value=2012>2012</option>
                                    <option value=2011>2011</option>
                                    <option value=2010>2010</option>
                                    <option value=2009>2009</option>
                                    <option value=2008>2008</option>
                                    <option value=2007>2007</option>
                                    <option value=2006>2006</option>
                                    <option value=2005>2005</option>
                                    <option value=2004>2004</option>
                                    <option value=2003>2003</option>
                                    <option value=2002>2002</option>
                                    <option value=2001>2001</option>
                                    <option value=2000>2000</option>
                                    <option value=1999>1999</option>
                                    <option value=1998>1998</option>
                                    <option value=1997>1997</option>
                                    <option value=1996>1996</option>
                                    <option value=1995>1995</option>
                                    <option value=1994>1994</option>
                                    <option value=1993>1993</option>
                                    <option value=1992>1992</option>
                                    <option value=1991>1991</option>
                                    <option value=1990>1990</option>
                                    <option value=1989>1989</option>
                                    <option value=1988>1988</option>
                                    <option value=1987>1987</option>
                                    <option value=1986>1986</option>
                                    <option value=1985>1985</option>
                                    <option value=1984>1984</option>
                                    <option value=1983>1983</option>
                                    <option value=1982>1982</option>
                                    <option value=1981>1981</option>
                                    <option value=1980>1980</option>
                                    <option value=1979>1979</option>
                                    <option value=1978>1978</option>
                                    <option value=1977>1977</option>
                                    <option value=1976>1976</option>
                                    <option value=1975>1975</option>
                                    <option value=1974>1974</option>
                                    <option value=1973>1973</option>
                                    <option value=1972>1972</option>
                                    <option value=1971>1971</option>
                                    <option value=1970>1970</option>
                                    <option value=1969>1969</option>
                                    <option value=1968>1968</option>
                                    <option value=1967>1967</option>
                                    <option value=1966>1966</option>
                                    <option value=1965>1965</option>
                                    <option value=1964>1964</option>
                                    <option value=1963>1963</option>
                                    <option value=1962>1962</option>
                                    <option value=1961>1961</option>
                                    <option value=1960>1960</option>
                                    <option value=1959>1959</option>
                                    <option value=1958>1958</option>
                                    <option value=1957>1957</option>
                                    <option value=1956>1956</option>
                                    <option value=1955>1955</option>
                                    <option value=1954>1954</option>
                                    <option value=1953>1953</option>
                                    <option value=1952>1952</option>
                                    <option value=1951>1951</option>
                                    <option value=1950>1950</option>
                                    <option value=1949>1949</option>
                                    <option value=1948>1948</option>
                                    <option value=1947>1947</option>
                                    <option value=1946>1946</option>
                                    <option value=1945>1945</option>
                                    <option value=1944>1944</option>
                                    <option value=1943>1943</option>
                                    <option value=1942>1942</option>
                                    <option value=1941>1941</option>
                                    <option value=1940>1940</option>
                                    <option value=1939>1939</option>
                                    <option value=1938>1938</option>
                                    <option value=1937>1937</option>
                                    <option value=1936>1936</option>
                                    <option value=1935>1935</option>
                                    <option value=1934>1934</option>
                                    <option value=1933>1933</option>
                                    <option value=1932>1932</option>
                                    <option value=1931>1931</option>
                                    <option value=1930>1930</option>
                                    <option value=1929>1929</option>
                                    <option value=1928>1928</option>
                                    <option value=1927>1927</option>
                                    <option value=1926>1926</option>
                                    <option value=1925>1925</option>
                                    <option value=1924>1924</option>
                                    <option value=1923>1923</option>
                                    <option value=1922>1922</option>
                                    <option value=1921>1921</option>
                                    <option value=1920>1920</option>
                                    <option value=1919>1919</option>
                                    <option value=1918>1918</option>
                                    <option value=1917>1917</option>
                                    <option value=1916>1916</option>
                                    <option value=1915>1915</option>
                                    <option value=1914>1914</option>
                                    <option value=1913>1913</option>
                                    <option value=1912>1912</option>
                                    <option value=1911>1911</option>
                                    <option value=1910>1910</option>
                                    <option value=1909>1909</option>
                                    <option value=1908>1908</option>
                                    <option value=1907>1907</option>
                                    <option value=1906>1906</option>
                                    <option value=1905>1905</option>
                                </select>
                            </div> 
                            
                            <div class="col-lg-4">
                                <select name="reg-mon" class="form-control" data-name="birthdate">
                                    <option value="0">Mês</option>
                                    <option value="1">Jan</option>
                                    <option value="2">Fev</option>
                                    <option value="3">Mar</option>
                                    <option value="4">Abr</option>
                                    <option value="5">Mai</option>
                                    <option value="6">Jun</option>
                                    <option value="7">Jul</option>
                                    <option value="8">Ago</option>
                                    <option value="9">Set</option>
                                    <option value="10">Out</option>
                                    <option value="11">Nov</option>
                                    <option value="12">Dez</option>
                                </select>
                            </div>
                            <div class="col-lg-4">
                                <select name="reg-day" class="form-control" data-name="birthdate">
                                    <option value="0">Dia</option>
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                    <option value="6">6</option>
                                    <option value="7">7</option>
                                    <option value="8">8</option>
                                    <option value="9">9</option>
                                    <option value="10">10</option>
                                    <option value="11">11</option>
                                    <option value="12">12</option>
                                    <option value="13">13</option>
                                    <option value="14">14</option>
                                    <option value="15">15</option>
                                    <option value="16">16</option>
                                    <option value="17">17</option>
                                    <option value="18">18</option>
                                    <option value="19">19</option>
                                    <option value="20">20</option>
                                    <option value="21">21</option>
                                    <option value="22">22</option>
                                    <option value="23">23</option>
                                    <option value="24">24</option>
                                    <option value="25">25</option>
                                    <option value="26">26</option>
                                    <option value="27">27</option>
                                    <option value="28">28</option>
                                    <option value="29">29</option>
                                    <option value="30">30</option>
                                    <option value="31">31</option>
                                </select>
                            </div>                       
                        </div>
                        <div class="col-lg-6 col-md-6">
                            <input name="reg-repass" type="password" class="form-control" placeholder="Repita a senha" data-name="Repetir Senha" value="abc123">
                        </div>
                        <div class="reg-sex col-lg-6 col-md-6">
                            <div class="col-lg-12">
                                <span class="label">Sexo</span>
                            </div>   
                            <div class="btn-group" data-toggle="buttons">
                                <label class="btn btn-primary basic-radio">
                                    <input type="radio" name="reg-sex" id="M" value="M" autocomplete="off" data-name="sex"> Masculino
                                </label>
                                <label class="btn btn-primary basic-radio">
                                    <input type="radio" name="reg-sex" id="F" value="F" autocomplete="off" data-name="sex"> Feminino
                                </label>                                    
                            </div>
                        </div>
                        <div class="col-lg-6 col-md-6">
                            <input type="submit" class="btn btn-success" value="Finalizar cadastro" data-id="submit-home-register">
                        </div>
                        <c:if test="${message != null}">
                            <div class="col-lg-12 col-md-12">
                                <div class="alert alert-danger be-error-report">${message}</div>
                            </div>
                        </c:if>
                    </div>
                </form>
            </div>
            <!-- //HOME REGISTER FORM -->
            <!-- HOME FOOTER -->
            <div class="container home-footer">
                <div class="footer-item"><a href="#">Saiba como funciona</a></div>
                <div class="footer-item"><a href="#">Sugestões</a></div>
                <div class="footer-item"><a href="#">Contate-nos</a></div>
            </div>
            <!-- //HOME FOOTER -->
        </div>
    </body>
</html>
