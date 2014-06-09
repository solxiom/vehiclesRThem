(function($) {
    $.paf.package("pafGames");
    /**
     * 
     * @returns {App}
     * @author Kavan Soleimanbeigi
     */
    var _view = CoderLeopard.pafGames.View = function() {
        'use strict';
        var _self = this;
        $("#commandDiv form").on("submit", function(event) {
            event.preventDefault();
            $.paf.publish({key: "save_order", data: {date: new Date(),
                    command: $("#commandTxt").val()}
            });
        });
        $("#refillBT").click(function() {
            $.paf.publish({key: "refill_request", data: undefined});
        });
        //
        $.paf.subscribe("update_view", function(data) {
            $("#main").empty();
            $.each(data, function() {
                $("#addGameDialog").css("display", "none");
                var result = Mustache.to_html($("#gameTemp").html(), this);
                $("#main").prepend(result);
            })
        });
        $.paf.subscribe("update_colors", function(data) {
            $("#colors .inner").empty();
            $.each(data, function() {
//                var result = Mustache.to_html($("#gameTemp").html(), this);
                $("#colors .inner").prepend("<span style='color:" + this + "'>" + this + " </span>");

            })
        });
        $.paf.subscribe("show_notification",function(data){
            $(".notification").css("display","block");
            $(".notification #notifText").html(data);
        })
        $.paf.subscribe("clear_command", function(){
            $("#commandTxt").val("");
            $("#commandTxt").val("");
        });
        $.paf.subscribe("update_orders", function(data) {
            $("#orders .inner").empty();
            $.each(data, function() {
                var result = "";
                if (this.vehicle.vehicleType.toString().toLowerCase() === "ferrari") {
                    this.vehicle.img = $.paf.url.root_path + "/resources/img/car/ferrari.png";
                    result = Mustache.to_html($("#ferrariTemp").html(), this.vehicle);
                } else if (this.vehicle.vehicleType.toString().toLowerCase() === "car") {
                    this.vehicle.img = $.paf.url.root_path + "/resources/img/car/" + this.vehicle.color.name.toString().toLowerCase() + "_car.png";
                    result = Mustache.to_html($("#carTemp").html(), this.vehicle);

                } else if (this.vehicle.vehicleType.toString().toLowerCase() === "motorcycle") {

                    this.vehicle.img = $.paf.url.root_path + "/resources/img/bike/" + this.vehicle.riderGender.toLowerCase() + ".png";
                    if(this.vehicle.riderGender.toLowerCase() === "female"){
                        this.vehicle.riderGender = this.vehicle.riderGender +" a.k.a Chick";
                    }else{
                        this.vehicle.riderGender = this.vehicle.riderGender+" a.k.a Dude ";
;
                    }
                    result = Mustache.to_html($("#motorTemp").html(), this.vehicle);

                } else if (this.vehicle.vehicleType.toString().toLowerCase() === "bicycle") {
                    this.vehicle.img = $.paf.url.root_path + "/resources/img/bike/bicycle.png";
                    result = Mustache.to_html($("#bicycleTemp").html(), this.vehicle);

                } else if (this.vehicle.vehicleType.toString().toLowerCase() === "boat") {
                    this.vehicle.img = $.paf.url.root_path + "/resources/img/boat/" + this.vehicle.floats + "_boat.png";

                    if (this.vehicle.floats === false || this.vehicle.floats === "false") {
                        this.vehicle.periscopeNumbers = "Periscopes: " + this.vehicle.periscopes;
                    }
                    result = Mustache.to_html($("#boatTemp").html(), this.vehicle);
                }
                if (result !== ""){
                    $("#orders .inner").prepend(result);
                }

            })
        });

    }


}(jQuery)
        );