var Browser = require("zombie");
var assert = require("assert");

port = process.argv[2];

Browser.visit("http://localhost:" + port + "/", function (e, browser) {
    assert.equal(browser.text("title"), "CodeStory - HomePage");

    assert.ok(browser.query("#commits :nth-child(1):contains('Troisieme commit')"));
    assert.ok(browser.query("#commits :nth-child(2):contains('Deuxieme commit')"));
    assert.ok(browser.query("#commits :nth-child(3):contains('Premier commit')"));

    assert.ok(browser.query("#commits :nth-child(1):contains('03/01/2012')"));
    assert.ok(browser.query("#commits :nth-child(2):contains('02/01/2012')"));
    assert.ok(browser.query("#commits :nth-child(3):contains('01/01/2012')"));
});
