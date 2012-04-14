# Needs
# Mocha : http://visionmedia.github.com/mocha/
# Expect.js : https://github.com/LearnBoost/expect.js
# Coffee-Script : http://coffeescript.org/
# Run with mocha -R spec --compilers coffee:coffee-script test.coffee
#
zombie = require("zombie")
expect = require("expect.js")

home = "http://localhost:" + process.env.PORT + "/"

browser = new zombie.Browser()

describe('When displaying HomePage', ->
	it('should have correct title', (done) ->
		browser.visit(home, ->
			expect(browser.text("title")).to.be('CodeStory - HomePage')
			done()
		)
	)

	it('should show commits', (done) ->
		browser.visit(home, ->
			expect(browser.query("#commits :nth-child(1):contains('Quatrieme commit')")).to.be.ok()
			expect(browser.query("#commits :nth-child(2):contains('Troisieme commit')")).to.be.ok()
			expect(browser.query("#commits :nth-child(3):contains('Deuxieme commit')")).to.be.ok()
			expect(browser.query("#commits :nth-child(4):contains('Premier commit')")).to.be.ok()

			expect(browser.query("#commits :nth-child(1):contains('2012-01-03')")).to.be.ok()
			expect(browser.query("#commits :nth-child(2):contains('2012-01-03')")).to.be.ok()
			expect(browser.query("#commits :nth-child(3):contains('2012-01-02')")).to.be.ok()
			expect(browser.query("#commits :nth-child(4):contains('2012-01-01')")).to.be.ok()
			done()
		)
	)

	it('should show badges', (done) ->
		browser.visit(home, ->
			expect(browser.query("#best-commiter:contains('Top Commiter')")).to.be.ok()
			expect(browser.query("#best-commiter:contains('jeanlaurent')")).to.be.ok()
			done()
		)
	)
)
