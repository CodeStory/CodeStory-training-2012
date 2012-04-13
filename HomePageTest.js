var Browser = require("zombie");
var assert = require("assert");

Browser.visit("http://localhost:8080/", function (e, browser, status) {
    assert.equal(browser.text("title"), "CodeStory - Homepage");
});

Browser.visit("http://localhost:8080/", function (e, browser, status) {
    assert.ok(browser.query("ul li:nth-child(1):contains('dgageot')"));
    assert.ok(browser.query("ul li:nth-child(2):contains('jeanlaurent')"));
    assert.ok(browser.query("ul li:nth-child(3):contains('eric')"));

    assert.ok(browser.query("ul li:nth-child(1):contains('02/12/2012')"));
    assert.ok(browser.query("ul li:nth-child(2):contains('01/12/2012')"));
    assert.ok(browser.query("ul li:nth-child(3):contains('29/11/2012')"));

    assert.ok(browser.query("ul li:nth-child(1):contains('Troisième commit')"));
    assert.ok(browser.query("ul li:nth-child(2):contains('Deuxième commit')"));
    assert.ok(browser.query("ul li:nth-child(3):contains('Premier commit')"));

    assert.ok(browser.query("ul li:nth-child(1) img[src='https://secure.gravatar.com/avatar/f0887bf6175ba40dca795eb37883a8cf']"));
    assert.ok(browser.query("ul li:nth-child(2) img[src='https://secure.gravatar.com/avatar/649d3668d3ba68e75a3441dec9eac26e']"));
    assert.ok(browser.query("ul li:nth-child(3) img[src='https://secure.gravatar.com/avatar/77da98419ae312eb0e322a3dac44a734']"));
});
