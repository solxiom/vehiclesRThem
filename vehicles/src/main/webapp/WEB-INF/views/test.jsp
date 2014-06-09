<%-- 
    Document   : test
    Created on : Dec 20, 2013, 11:25:34 PM
    Author     : kavan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
        <script src="<%=request.getContextPath()%>/resources/js/loader.js" type="text/javascript"></script>
        <script src="<%=request.getContextPath()%>/resources/js/init.js" type="text/javascript"></script>
        <script src="<%=request.getContextPath()%>/resources/js/lib/qunit-1.12.0.js" ></script>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/style/standard_style.css" type="text/css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/style/lib/qunit-1.12.0.css" type="text/css">

        <title>Unit testing</title>
    </head>
    <body style="margin-top:35px">

            <div id="qunit"></div>
            <div id="qunit-fixture"></div>
            <script>

                module("group a: basic things");
                test("a basic test example", function() {
                    var value = "hello";
                    equal(value, "hello", "We expect value to be hello");
                });            
            </script>
            <script src="<%=request.getContextPath()%>/resources/js/test/loader_test.js" type="text/javascript"></script>
        
    </body>
</html>
