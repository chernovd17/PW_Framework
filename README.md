Hello! 

This project is being developed to automate testing of web applications.

A special feature of this project is a built-in reporting system that can help you speed up both Test Development and Run analysis.

## Technologies stack: 
1) Main Language: Java
2) Automation tool for interacting with browser: Playwright
3) Build automation and project management tool: Maven
4) Testing framewotk: TestNG
5) Approach to test automation: Page Object Pattern

## UI-Report tech-stack:
1) Logger: log4j + JSON
2) UI: JavaScript + React + Bootstrap
3) Build tool: Vite

## OPEN UI REPORT: open console -> go to "report" folder -> "npm run dev"
if something goes wrong:
1) if node.js is not installed: "npm install --production"
2) "npm update vite" or "npm create vite@latest Report -- --template"

## Report Structure:
### 1) REPORTS page: all reports (JSON files) from your project
![image](https://github.com/user-attachments/assets/1c599f13-53df-4ddf-8596-e60da81dab91)  

### 2) STATISTICS page: Diagram and statistics for chosen report
![image](https://github.com/user-attachments/assets/b67e29a6-5160-4472-809f-f4190449e629)
![image](https://github.com/user-attachments/assets/659c0a41-865b-463b-927c-297573109fb1)

### 3) TESTS page: tests info and steps
![image](https://github.com/user-attachments/assets/c08b3daf-2a8d-4870-b3d0-9d914918a9ee)
![image](https://github.com/user-attachments/assets/4af05d5a-b88e-4166-b4d7-3e5848986b90)
![image](https://github.com/user-attachments/assets/e64af7cf-cd7e-484d-b996-fa618becf7e1)
![image](https://github.com/user-attachments/assets/6da185ae-e3a3-44f9-8316-edc79aaaa6ee)

## run test suites by Maven 
"mvn clean install test -DsuiteXmlFile={suite}" (without extension ".xml")




