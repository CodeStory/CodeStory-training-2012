var Browser = require("zombie");
var assert = require("assert");

port = process.argv[2];

browser = new Browser();
browser.visit('http://localhost:' + port + "/index.html", function (e, browser) {
    assert.equal(browser.text("title"), "Code Story");
    assert.ok(browser.query("#timeline li:nth-child(1):contains('jlm on 29/03/2012')"));
    assert.ok(browser.query("#timeline li:nth-child(3):contains('dgageot on 29/03/2012')"));
});
