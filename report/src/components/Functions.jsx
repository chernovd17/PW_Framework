export const convertDateTime = dateTime => dateTime ? formatDateTime(dateTime) : "No date"

export const convertTime = dateTime => dateTime ? timeFormatter(dateTime) : "No time"

export const formatDateTime = dateTime => dateFormatter(dateTime) + " " + timeFormatter(dateTime)

export const timeFormatter = dateTime => convertToDate(dateTime)
    .toLocaleTimeString('en-US', {hour: "2-digit", minute: "2-digit", second: "2-digit", hour12: false})

export const dateFormatter = dateTime => convertToDate(dateTime)
    .toLocaleDateString('en-US', {day: "2-digit", month: "2-digit", year: "numeric"})

export const convertToDate = dateTime =>
    new Date(
        dateTime.year,
        dateTime.monthValue - 1,
        dateTime.dayOfMonth,
        dateTime.hour,
        dateTime.minute,
        dateTime.second,
        0
    )

export function addLevelColor(item) {
    const textColorMap = {
        SUCCESS: "text-success", FAIL: "text-warning", FATAL: "text-danger", STEP: "text-primary", ACTION: "text-muted",
    }

    return textColorMap[item] || "text-black"
}

export const splitByNewLine = str => str.split("\n")

export const getAllFilesFromDirectory = directory => require('fs').readdirSync(directory)

export function convertSecondsToTime(duration) {
    const hours = padNumber(Math.floor(duration / 3600))
    const minutes = padNumber(Math.floor((duration % 3600) / 60))
    const seconds = padNumber(Math.floor(duration % 60))
    return `${hours}:${minutes}:${seconds}`;
}

export const padNumber = number => String(number).padStart(2, '0')

export const removeRootFolderFromPath = path => (path === null || path === '') ? "" : path.replace('report', '')

export function getAllSkippedTests(fileInfo) {
    const {countOfPassedTests, countOfFailedTests, countOfFatalTests, countOfUnknownTests, countOfSkippedTests, allTestsCount} = fileInfo
    const allWhichPassed = countOfPassedTests + countOfFailedTests + countOfFatalTests + countOfSkippedTests + countOfUnknownTests;
    return (allTestsCount !== allWhichPassed) ? allTestsCount - allWhichPassed : countOfSkippedTests
}
