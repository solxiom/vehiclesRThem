<%-- 
    Document   : index
    Created on : Dec 15, 2013, 10:29:53 PM
    Author     : kavan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vehicles"R"Them</title>
        <link rel="icon" 
              type="image/gif" 
              href="<%=request.getContextPath()%>/resources/img/paf_icon.gif">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/style/standard_style.css" type="text/css">
        <!--Development Scripts start -->
        <!-- external -->
        <script src="<%=request.getContextPath()%>/resources/js/lib/jquery-2.0.0.min.js"></script>
        <script src="<%=request.getContextPath()%>/resources/js/lib/mustache.min.js"></script>
        <!-- -->

        <script src="<%=request.getContextPath()%>/resources/js/root.js" type="text/javascript"></script>
        <script src="<%=request.getContextPath()%>/resources/js/App.js" type="text/javascript"></script>
        <script src="<%=request.getContextPath()%>/resources/js/Model.js" type="text/javascript"></script>
        <script src="<%=request.getContextPath()%>/resources/js/View.js" type="text/javascript"></script>   
        
        <!--vehiclesJS_kavan.min-->
    </head>
    <body>
        <header>
            <div id="header_title">
                <span> Vehicles "R" Them</span>
                <img src="<%=request.getContextPath()%>/resources/img/paf_log.png"/>
                <div id="commandDiv">
                    <form>
                        <input id="commandTxt" name="command" type="text">
                        <input id="commandBT" type="submit" value="SEND">    
                    </form>
                </div>

            </div>
        </div>

    </header>
    <div id="main" > 

        <div id="orders">
            <h1>Orders: </h1>
            <div class="inner">
                <span>Vehicle: Car</span>

            </div>
        </div>
        <div id="colors">
            <span>Available Colors:<input id="refillBT" type="button" value="refill"/></span>
            <div class="inner"></div>
        </div>
    </div>  
    <div class="notification"> <span id="closeNotife">close</span> <br/>
        <span id="notifText">bad command exception!</span> </div>

    <script id="carTemp" type="text/template">
                <div class="order_item car_order">  
                <image style='width:90px;height60px;' src={{img}} />
              <span>{{vehicleType}}</span>
                <span>Color:</span>
                 <span style="color:{{color.name}}"> {{color.name}} </span> 
                <span>wheels: {{wheels}}</span>
                <span>Steering wheels: {{steeringWheels}}
                </span>
        </div>

    </script>
    <script id="ferrariTemp" type="text/template">
                <div class="order_item car_order">  
                <image style='width:90px;height60px;' src={{img}} />
              <span>{{vehicleType}}</span>
                <span>Color:</span>
                 <span style="color:{{color.name}}"> {{color.name}} </span> 
                <span>wheels: {{wheels}}</span>
                <span>Steering wheels: {{steeringWheels}}
                </span>
                <span class='coolTag'> COOL OBJECT!</span>
        </div>

    </script>
    <script id="motorTemp" type="text/template">
        <div class="order_item motor_order">  
        <image style='width:90px;height60px;' src={{img}} />
        <span>{{vehicleType}}</span>
        <span>Color:</span>
        <span style="color:{{color.name}}"> {{color.name}} </span> 
        <span>Rider: {{riderGender}}</span>
        </span>
        </div>

    </script>
    <script id="bicycleTemp" type="text/template">
        <div class="order_item bicycle_order">  
        <image style='width:90px;height60px;' src={{img}} />
        <span>{{vehicleType}}</span>
        <span>Color:</span>
        <span style="color:{{color.name}}"> {{color.name}} </span> 
        <span>Type: {{bicycleType}}</span>
        </span>
        </div>

    </script>
    <script id="boatTemp" type="text/template">
            <div class="order_item boat_order">  
            <image style='width:90px;height60px;' src={{img}} />
            <span>{{vehicleType}}</span>
            <span>Color:</span>
            <span style="color:{{color.name}}"> {{color.name}} </span> 
            <span >Floats: {{floats}}</span>
            <span>{{periscopeNumbers}}</span>
            </span>
            </div>

    </script>

</body>
</html>
