
  export function dateTimeFormatter(dateTime){
    if (dateTime) {
      const date = dateFormatter(dateTime);
      const time = timeFormatter(dateTime);
      const formattedDateTime = date + " " + time;
      return formattedDateTime
    } else {
      return "No date"
    }
  }

  export function timeFormatter(dateTime){
    if (dateTime) {
      let hour = dateTime.hour.toString().padStart(2, '0');
      let minute = dateTime.minute.toString().padStart(2, '0');
      let second = dateTime.second.toString().padStart(2, '0');;
      const formattedDate = `${hour}:${minute}:${second}`;
      return formattedDate
    } else{
      return "No time"
    }
  }

  export function dateFormatter(dateTime){

    let year = dateTime.year.toString();
    let month = dateTime.monthValue.toString().padStart(2, '0');
    let day = dateTime.dayOfMonth.toString().padStart(2, '0');

    const formattedDate = `${day}.${month}.${year}`;

    return formattedDate
  }

  export function addLevelColor(item) {
    let textColorClass = "text-black"; // Default color is black

    if (item === "SUCCESS") {
        textColorClass = "text-success"; // Green color for SUCCESS
    } else if (item === "FAIL") {
        textColorClass = "text-warning"; // Yellow color for FAIL
    } else if (item === "FATAL") {
        textColorClass = "text-danger"; // Red color for FATAL
    } else if (item === "STEP") {
      textColorClass = "text-primary"; // Light Blue color for STEP
    } else if (item === "ACTION") {
      textColorClass = "text-muted"; // Light Blue color for ACTION
    }

    return textColorClass;
  }

  export function splitByNewLine(str) {
    return str.split("\n");
  }

  export function getAllFilesFromDirectory(directory){
    var fs = require('fs');
    var files = fs.readdirSync(directory);
    return files;
  }

  export function convertSecondsToTime(duration){
    const hours = Math.floor(duration / 3600);
    const minutes = Math.floor((duration % 3600) / 60);
    const seconds = Math.floor(duration % 60);
    const formattedTime = `${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}`;
    return formattedTime
  }

  export function removeRootFolderFromPath(path){
    if(path === null || path === '') 
      return "";
    else {
      return path.replace('report\\','');
    }
  }

  export function getAllSkippedTests(fileInfo){
    const passed = fileInfo.countOfPassedTests;
    const failed = fileInfo.countOfFailedTests;
    const fatal = fileInfo.countOfFatalTests;
    let skipped =fileInfo.countOfSkippedTests;
    const unkown = fileInfo.countOfUnknownTests;
    const allTestCount = fileInfo.allTestsCount;
    const allWhichPassed = passed + failed + fatal + skipped + unkown;
    if( allTestCount != allWhichPassed) {
      skipped = allTestCount - allWhichPassed
    }
    return skipped;
  }