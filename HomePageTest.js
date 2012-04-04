var Browser = require("zombie");
var assert = require("assert");

Browser.visit("http://localhost:8080/", function (e, browser, status) {
    assert.equal(browser.text("title"), "CodeStory - Homepage");
});

Browser.visit("http://localhost:8080/", function (e, browser, status) {
    assert.ok(browser.query("ul li:nth-child(1):contains('dgageot 02/12/2012')"));
    assert.ok(browser.query("ul li:nth-child(2):contains('jeanlaurent 01/12/2012')"));
    assert.ok(browser.query("ul li:nth-child(3):contains('eric 29/11/2012')"));
});

