import { Diagrama } from "./Diagrama"
import { useSelector, useDispatch } from 'react-redux'
import * as Functions from "./Functions"

function countPercentages(allTests, countOfRequiredTests) {
  if(countOfRequiredTests === 0) 
    return 0;
  else {
    let percentage = ((countOfRequiredTests / allTests) * 100).toFixed(2);
    return countOfRequiredTests + "(" + percentage + " %)";
  }
}

function renderTestsStatRow(title, message){
  let textColorClass = "text-black";
  if(title == "Passed"){
    textColorClass = "success_text_color";
  } else if(title == "Failed"){
    textColorClass = "fail_text_color";
  } else if(title == "Fatal"){
    textColorClass = "fatal_text_color";
  } else if(title == "Skipped"){
    textColorClass = "skipped_text_color";
  } else if(title == "Unknown"){
    textColorClass = "unknown_text_color";
  }
    return (
      <div className="row ">
        <div className="col fw-bold fs-10 " align="left">
          <span align="center" className={"large_text " + textColorClass}>{title}</span>
        </div>
        <div className="col  fw-bold fs-10 ">
          <span align="left" className={"large_text " + textColorClass}>{message}</span>
        </div>
      </div >
    )
}

function renderOptionalTestsStatRow(title, message){
  if(message != 0){
    return renderTestsStatRow(title, message);
  }
}

export function Statistic() {
  const info = useSelector(state => state.file.fileInfo)
  const allTests = info.allTestsCount;
  const duration = info.duration
  let skipped = Functions.getAllSkippedTests(info);
  let seconds = null
  if (duration) {
    seconds = duration.seconds
  }
  return (
    <div className="w-100 vh-100">
      <div className="d-flex w-100 mb-3">
        <div className="w-50">
          <h3 className="text-center text-uppercase">General stat</h3>
          <div className="w-75 h-100 mx-auto">
            <Diagrama />
          </div>
        </div>

        <div className="w-50 ">

        <div className="w-50">
            <h1 className="text-center text-uppercase">Tests Info</h1>
              {renderTestsStatRow("Total", allTests)}
              {renderTestsStatRow("Passed", countPercentages(allTests, info.countOfPassedTests))}
              {renderTestsStatRow("Failed", countPercentages(allTests, info.countOfFailedTests))}
              {renderTestsStatRow("Fatal", countPercentages(allTests, info.countOfFatalTests))}
              {renderOptionalTestsStatRow("Skipped", countPercentages(allTests, skipped))}
              {renderOptionalTestsStatRow("Unknown", countPercentages(allTests, info.countOfUnknownTests))}


        </div>
          <div className="w-50">
              <h1 className="text-center text-uppercase">System Info</h1>
              <div className="row ">
                <div className="col fw-bold fs-10 " align="left">
                  <span align="center">Operation System</span>
                </div>
                <div className="col  fw-bold fs-10 ">
                  <span align="left">{info.operationSystem}</span>
                </div>
              </div >
  
              <div className="row ">
                <div className="col fw-bold fs-10 " align="left">
                  <span align="center">Browser</span>
                </div>
                <div className="col  fw-bold fs-10">
                  <span align="left">{info.browser}</span>
                </div>
              </div >
  
              <div className="row ">
                <div className="col fw-bold fs-10 " align="left">
                  <span align="center">Threads</span>
                </div>
                <div className="col  fw-bold fs-10">
                  <span align="left">{info.countOfThreads}</span>
                </div>
              </div >
  
              <div className="row ">
                <div className="col fw-bold fs-10 " align="left">
                  <span align="center">Start Date-Time</span>
                </div>
                
                <div className="col  fw-bold fs-10 ">
                  <span align="left">{Functions.dateTimeFormatter(info.startDateTime)}</span>
                </div>
              </div >
  
              <div className="row ">
                <div className="col fw-bold fs-10 " align="left">
                  <span align="center">End Date Time</span>
                </div>
                <div className="col  fw-bold fs-10 ">
                  <span align="left">{Functions.dateTimeFormatter(info.endDateTime)}</span>
                </div>
              </div >
  
              <div className="row ">
                <div className="col fw-bold fs-10 " align="left">
                  <span align="center">Suite Duration</span>
                </div>
                <div className="col  fw-bold fs-10">
                  <span align="left">{Functions.convertSecondsToTime(seconds)}</span>
                </div>
              </div >
  
              <div className="row ">
                <div className="col fw-bold fs-10 " align="left">
                  <span align="center">Avg. test duration</span>
                </div>
                <div className="col  fw-bold fs-10 ">
                  <span align="left">{Functions.convertSecondsToTime(seconds / info.allTestsCount)}</span>
                </div>
              </div >
              <div className="row ">
                <div className="col fw-bold fs-10 " align="left">
                  <span align="center">The longest test:</span>
                </div>
                <div className="col  fw-bold fs-10 ">
                  <span align="left">need to implement</span>
                </div>
              </div >
              <div className="row ">
                <div className="col fw-bold fs-10 " align="left">
                  <span align="center">Count of bugs:</span>
                </div>
                <div className="col  fw-bold fs-10 ">
                  <span align="left">need to implement</span>
                </div>
              </div >
              <div className="row ">
                <div className="col fw-bold fs-10 " align="left">
                  <span>All Linked bugs:</span>
                </div>
                <div className="col  fw-bold fs-10 ">
                  <span align="left">need to implement</span>
                </div>
              </div >
          </div>
        </div>
      </div>
    </div>
  )
}
