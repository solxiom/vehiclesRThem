(function($) {
    $.paf.package("pafGames");
    /**
     * 
     * @returns {App}
     * @author Kavan Soleimanbeigi
     */
    var _model = CoderLeopard.pafGames.Model = function() {
        'use strict';
        var _self = this;
        $.paf.subscribe("start_worker", function() {
            /**
             * firs ask from server if any DB updates is occured and
             * request the DB data only if server has somthing new to offer
             */
            $.ajax({
                url: '/api/lastupdate',
                success: function(data) {
                    var localUpdateInfo = null;
                    try {
                        localUpdateInfo = JSON.parse(sessionStorage.getItem("localUpdateInfo"));
                    } catch (e) {
                        localUpdateInfo = null;
                    }
                    if (localUpdateInfo === null || localUpdateInfo !== data.date) {
                        console.log(new Date());
                        sessionStorage.setItem("localUpdateInfo", data.date);
                        $.paf.publish({key: "orders_request", data: undefined});
                        $.paf.publish({key: "colors_request", data: undefined});
                    }

                },
                complete: function() {
                    // Schedule the next request when the current one's complete
                    setTimeout(function() {
                        $.paf.publish({key: "start_worker", data: undefined});
                    }, 2000);
                }
            });
        });
        $.paf.subscribe("save_order", function(order) {
            $.ajax({
                type: "POST",
                url: $.paf.url.root_path + "/api/order/new",
                data: JSON.stringify(order),
                dataType: "json",
                async: true,
                contentType: 'application/json; charset=utf-8',
                complete: function(msg) {
                    if (msg.status >= 400) {
                        $.paf.publish({key: "show_notification", data: "Bad Command Request! Server rejected your Command"});

                    } else {
                        $.paf.publish({key: "colors_request", data: undefined});
                        $.paf.publish({key: "orders_request", data: undefined});
                        $.paf.publish({key: "clear_command", data: undefined});
                    }
                }
            });
        });
        $.paf.subscribe("colors_request", function() {
            $.getJSON($.paf.url.root_path + "/api/color/list", function(data) {
                $.paf.publish({key: "update_colors", data: data});
            });
        });
        $.paf.subscribe("orders_request", function() {
            $.getJSON($.paf.url.root_path + "/api/order/list", function(data) {
                $.paf.publish({key: "update_orders", data: data});
            });
        });
        $.paf.subscribe("refill_request", function() {

            $.ajax({
                type: "POST",
                url: $.paf.url.root_path + "/api/color/refill",
                dataType: "json",
                async: true,
                contentType: 'application/json; charset=utf-8',
                complete: function(msg) {
                    $.paf.publish({key: "colors_request", data: undefined});
                }
            });
        });

    }


}(jQuery));