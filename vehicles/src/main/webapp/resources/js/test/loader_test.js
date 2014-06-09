/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//deepEqual is the best choice mostly
//asyncTest for asnyc functions
//expect(2) for number of assertions

module("group b: loader functions");
test("loader, correct file, fileExist Method", function() {
    var loader = new Loader();
    var value = loader.fileExists("link", "href", "standard_style.css");
//    var value = true;
    strictEqual(value, true, "We expect value to be true");
});
test("loader, wrong file, fileExist Method", function() {
    var loader = new Loader();
    var value = loader.fileExists("link", "href", "standard_style333.css");
//    var value = false;
    strictEqual(value, false, "We expect value to be false");
});
              