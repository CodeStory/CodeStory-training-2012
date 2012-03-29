var Browser = require("zombie");
var assert = require("assert");

browser = new Browser();
browser.visit('file:src/main/resources/index.html', function (e, browser) {
    assert.ok(browser.success);
    assert.equal(browser.text("title"), "Code Story");
    assert.ok(browser.query("#timeline li:nth-child(1):contains('dgageot on 29/04/2013')"));
    assert.ok(browser.query("#timeline li:nth-child(2):contains('jlm on 28/04/2013')"));
});
