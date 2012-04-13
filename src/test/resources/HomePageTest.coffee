# Needs Mocha : http://visionmedia.github.com/mocha/
# Expect.js : https://github.com/LearnBoost/expect.js
# Coffee-Script : http://coffeescript.org/
# Run with mocha --ui qunit --compilers coffee:coffee-script --growl test.coffee
Browser = require("zombie")
expect = require("expect.js")

port = process.argv[2]
home = "http://localhost:" + port + "/"

suite('HomePage')

test("Checking Commits", ->
  Browser.visit(home, (e, browser, status) ->
    expect(browser.success).to.be.ok()
    expect(browser.text("title")).to.be("CodeStory - HomePage")

    expect(browser.query("#commits :nth-child(1):contains('Quatrieme commit')")).to.be.ok()
    expect(browser.query("#commits :nth-child(2):contains('Troisieme commit')")).to.be.ok()
    expect(browser.query("#commits :nth-child(3):contains('Deuxieme commit')")).to.be.ok()
    expect(browser.query("#commits :nth-child(4):contains('Premier commit')")).to.be.ok()

    expect(browser.query("#commits :nth-child(1):contains('03/01/2012')")).to.be.ok()
    expect(browser.query("#commits :nth-child(1):contains('03/01/2012')")).to.be.ok()
    expect(browser.query("#commits :nth-child(1):contains('02/01/2012')")).to.be.ok()
    expect(browser.query("#commits :nth-child(1):contains('01/01/2012')")).to.be.ok()
  )
)

test("Checking Badges", ->
  Browser.visit(home, (e, browser, status) ->
    expect(browser.query("#best-commiter:contains('Awarded on 3/4/2012 for 2 commits.')")).to.be.ok()
    expect(browser.query("#best-commiter:contains('Best Commiter')")).to.be.ok()
    expect(browser.query("#best-commiter")).to.be.ok();
  )
)
