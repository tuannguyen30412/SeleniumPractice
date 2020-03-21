REM start a node configured by the node.json
REM https://github.com/SeleniumHQ/selenium/blob/selenium-3.141.59/java/server/src/org/openqa/grid/common/defaults/DefaultNodeWebDriver.json
java -jar -Dwebdriver.gecko.driver=E:\SeleniumPractice\drivers\geckodriver.exe -Dwebdriver.chrome.driver=E:\SeleniumPractice\drivers\chromedriver.exe selenium-server-3.141.59.jar -role node -nodeConfig node.json