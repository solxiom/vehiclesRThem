(function($) {
    $.paf.package("pafGames");
    /**
     * 
     * @returns {App}
     * @author Kavan Soleimanbeigi
     */
    var _app = CoderLeopard.pafGames.App = function() {
        'use strict';
        var _self = this;





    }
    _app.prototype.start = function() {
        'use strict';

        var _self = this;
        _self.Model = new CoderLeopard.pafGames.Model();
        _self.View = new CoderLeopard.pafGames.View();
        $(".notification #closeNotife").click(function() {
            $(".notification").css("display", "none");
        });
        $.paf.publish({key: "colors_request", data: undefined});
        $.paf.publish({key: "orders_request", data: undefined});
        $.paf.publish({key: "start_worker", data: undefined});
        window.location = $.paf.url.root_path + "/#vehicles";

    }
   
    $("document").ready(function() {
        _app = new CoderLeopard.pafGames.App();
        _app.start();

    });
}(jQuery));


