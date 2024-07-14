import {ArcElement, Chart as ChartJS, Legend, Tooltip} from "chart.js";
import {Doughnut} from "react-chartjs-2";
import {calculatePercentage, getAllSkippedTests} from "../../functions/Functions.jsx";

ChartJS.register(ArcElement, Tooltip, Legend);

export function Diagram({info}) {
    const {countOfPassedTests, countOfFailedTests, countOfFatalTests, countOfUnknownTests, allTestsCount} = info
    let skipped = getAllSkippedTests(info);

    const passedPer = calculatePercentage(countOfPassedTests, allTestsCount);
    const failedPer = calculatePercentage(countOfFailedTests, allTestsCount);
    const fatalPer = calculatePercentage(countOfFatalTests, allTestsCount)
    const skippedPer = calculatePercentage(skipped, allTestsCount);
    const unknownPer = calculatePercentage(countOfUnknownTests, allTestsCount);

    let colors = ["#01a901", "#fce251", "#f61717"];
    let labels = ["PASSED " + countOfPassedTests, "FAILED " + countOfFailedTests, "FATAL " + countOfFatalTests];
    let data = [countOfPassedTests, countOfFailedTests, countOfFatalTests];

    if (skipped > 0) {
        labels.push("SKIPPED " + skipped);
        data.push(skipped);
        colors.push("#C0C0C0");
    }
    if (countOfUnknownTests > 0) {
        labels.push("UNKNOWN " + countOfUnknownTests);
        data.push(countOfUnknownTests);
        colors.push("#bdb76b");
    }

    const pieChartData = {
        labels: labels,
        datasets: [{
            data: data,
            label: "Tests ",
            backgroundColor: colors,
        }]
    };

    return (
        <div>
            <h3 className="text-center text-uppercase">General stat</h3>
            <div className="w-75 h-100 mx-auto">
                <Doughnut data={pieChartData}/>
            </div>
        </div>
    )
}
