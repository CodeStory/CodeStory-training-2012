export NODE_PATH=/usr/local/lib/node_modules
export PATH=$PATH:/usr/local/bin

mocha --ui qunit --compilers coffee:coffee-script --reporter xunit $1