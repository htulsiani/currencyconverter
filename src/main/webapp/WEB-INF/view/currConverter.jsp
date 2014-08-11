<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<html>
    <head>
        <title>Basic spring mvc app with hibernate</title>
    </head>
    
    <body>
    <h2>Currency Converter App</h2>
    
    <c:out  value="${converSionRate.conversionRate}"/>
    
</body>
</html>

    