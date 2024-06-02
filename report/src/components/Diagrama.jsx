import { useSelector } from 'react-redux'
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from "chart.js";
import { Doughnut } from "react-chartjs-2";
import * as F from "./Functions"

ChartJS.register(ArcElement, Tooltip, Legend);


export function Diagrama() {
  const fileInfo = useSelector(state => state.file.fileInfo)
  const passed = fileInfo.countOfPassedTests;
  const failed = fileInfo.countOfFailedTests;
  const fatal = fileInfo.countOfFatalTests;
  let skipped = F.getAllSkippedTests(fileInfo);
  const unkown = fileInfo.countOfUnknownTests;
  const allTestCount = fileInfo.allTestsCount;
  
  function countPercentOfTestsWithStatus(countOfTests) {
    const allTestCount = fileInfo.allTestsCount;
    let percentage = ((countOfTests / allTestCount) * 100).toFixed(2);
    return percentage;
  }

  const passedPer = countPercentOfTestsWithStatus(passed);
  const failedPer = countPercentOfTestsWithStatus(failed);
  const fatalPer = countPercentOfTestsWithStatus(fatal)
  const skippedPer = countPercentOfTestsWithStatus(skipped);
  const unknownPer = countPercentOfTestsWithStatus(unkown);

  let colors = ["#01a901", "#fce251", "#f61717"];
  let labels = ["PASSED " + passed, "FAILED " + failed, "FATAL " + fatal];
  let data = [passed, failed, fatal];
  let pieChartData;

  if (skipped === 0 ) {
    if(unkown != 0) {
      labels.push("UNKNOWN " + unkown);
      data.push(unkown);
      colors.push("#bdb76b");
    }
  } else {
    if(unkown === 0) {
      labels.push("SKIPPED " + skipped);
      data.push(skipped);
      colors.push("#C0C0C0");
    } else {
      labels.push("SKIPPED " + skipped);
      data.push(skipped);
      colors.push("#C0C0C0");
      labels.push("UNKNOWN " + unkown);
      data.push(unkown);
      colors.push("#bdb76b");
    }
  }

  pieChartData = {
    labels: labels,
    datasets: [{
      data: data,
      label: "Tests ",
      backgroundColor: colors,
    }]
  };

  const pieChart = (
    <Doughnut
      data={pieChartData}
    />
  );
  return pieChart;
}
