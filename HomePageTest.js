var Browser = require("zombie");
var assert = require("assert");

Browser.visit("http://localhost:8080/", function (e, browser, status) {
    assert.equal(browser.text("title"), "CodeStory - Homepage");
});
