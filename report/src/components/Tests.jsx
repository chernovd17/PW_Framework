import { useSelector } from 'react-redux'
import { TabsTest } from "./TabsTest"
import { useState } from 'react'
import * as Functions from "./Functions"
import ModalImage from "react-modal-image"
import '../styles/Styles.css';


export function Tests(props) {
  const activeTabTest = useSelector(state => state.tabs.tabTest)
  const [testName, setTestName] = useState('')
  const tests = props.tests
  let testTitle = tests.filter(test => test.title == testName)

  function handleClickTest(event) {
    const { id } = event.target.dataset
    setTestName(id)
  }

  function renderHeader(){
    return <div className="row border">
            <div className="col-2 border fw-bold  py-2" align="center">
              <span align="center">Level {"\n"}</span>
              <span align="center">Datetime</span>
            </div>
            <div className="col-8 border fw-bold fs-5 py-2" align="center">
              <span align="center">Message</span>
            </div>
            <div className="col-2 border fw-bold fs-5 py-2" align="center">
              <span align="center">Screenshot</span>
            </div>
          </div>
  }

  function renderRow(item, index) {
    return <div className='row row-cols-4 border ' key={index}>
              <div className="col-2 border fw-medium fw-light align-items-center fs-6" align="center">
                <div className='row row-cols-1 border'>
                  <div className={`align-bottom ${Functions.addLevelColor(item.logLevel)}`} align="center" >{item.logLevel}</div>
                </div>
                <div className="small_text">
                  <div>{Functions.dateFormatter(item.dateTime)}</div>
                  <div>{Functions.timeFormatter(item.dateTime)}</div>
                </div>
              </div>
              <div className={`col-8 border fw-medium fs-5 ${Functions.addLevelColor(item.logLevel)}`}>{
                Functions.splitByNewLine(item.info).map((inf, i) => (
                  <div className="g-0" key={i}>{inf}</div>
                ))}</div>
              <div className="col-2 border" >
                  <ModalImage className="modal-image max_pic_height" small={Functions.removeRootFolderFromPath(item.screenshotPath)} medium={Functions.removeRootFolderFromPath(item.screenshotPath)}
                    hideDownload={true} />
                  {/*<img src="public\screenshot_1710589699534.png" alt="Descr" height="70px" width="150px"></img>
                   <img src={"{item.screenshotPath}"} alt="Description of image"></img>  */}
              </div>
            </div>
  }
  
  function renderTable() {
    switch (activeTabTest) {
      case 'tab1':
        return  <div className="container text-left border">
                  {renderHeader()}
                  {testTitle[0].testRows.map((item, index) => {return renderRow(item, index)})}
                </div>
      case 'tab2':
        return  <div className="container text-left border">
                  {renderHeader()}
                  {testTitle[0].beforeTestRows.map((item, index) => {return renderRow(item, index)})}
                </div>
      case 'tab3':
        return  <div className="container text-left border">
                  {renderHeader()}
                  {testTitle[0].testRows.map((item, index) => {return renderRow(item, index)})}
        </div>
      case 'tab4':
        return  <div className="container text-left border">
                  {renderHeader()}
                  {testTitle[0].afterTestRows.map((item, index) => {return renderRow(item, index)})}
                </div>
      default:
        return null
    }
  }

  function colorizeTestBtnTitle(test){
    const status = test.testStatus;
    if (status === 'SUCCESS') {
      return 'success_text_color';
    } else if (status === 'FAIL') {
      return 'fail_text_color';
    } else if (status === 'FATAL') {
      return 'fatal_text_color';
    }
  }

  function renderContent() {
    return (
      <div className="d-flex">
        <div className="w-25">
          <h1 className="text-center">Tests</h1>
          <ul className="list-group mx-5">
            
            {tests.map((test, index) => <button data-id={test.title} key={index} className={`${colorizeTestBtnTitle(test)} btn my-1  fw-semibold fs-8 ${testName === test.title ? 'btn-primary' : 'btn-outline-primary'} `} onClick={handleClickTest}>{test.title}</button>)}
          </ul>
        </div>
        {testTitle.map((test, index) => {
          const stringsArray = Functions.splitByNewLine(test.finalStatusString);
          return (
            <div className="w-75" key={index}>
              <div className='d-flex'>
                <div className='w-25'>
                  <div className="fs-4 fw-bold" align="center">Info</div>
                  <div className="row ">
                    <div className="col fw-bold fs-10 " align="right">
                      <span align="right">Test Name</span>
                    </div>
                    <div className="col  fw-bold fs-10">
                      <span align="left">{test.title}</span>
                    </div>
                  </div>

                  <div className="row ">
                    <div className="col fw-bold fs-10 " align="right">
                      <span align="right">Description</span>
                    </div>
                    <div className="col  fw-bold fs-10">
                      <span align="left">{test.description}</span>
                    </div>
                  </div>

                  <div className="row ">
                    <div className="col fw-bold fs-10 " align="right">
                      <span align="right">Link</span>
                    </div>
                    <div className="col  fw-bold fs-10">
                      <span align="left">TR/TL Link</span>
                    </div>
                  </div>

                  <div className="row ">
                    <div className="col fw-bold fs-10 " align="right">
                      <span align="right">Duration</span>
                    </div>
                    <div className="col  fw-bold fs-10">
                      <span align="left">duration</span>
                    </div>
                  </div>
                  <div className="row ">
                    <div className="col fw-bold fs-10 " align="right">
                      <span align="right">Bugs</span>
                    </div>
                    <div className="col  fw-bold fs-10">
                      <span align="left">{test.bug}</span>
                    </div>
                  </div>
                </div>
                <div className="w-25"></div>
                <div className="w-50">
                  <div className="fw-bold" key={index}>
                    <div className="fs-4" align="center">Final Status</div>
                    <div className="fw-semibold">{
                      stringsArray.map((item, i) => (
                        <div className="pt-0 pb-0" key={i}>{item}</div>
                      ))}
                    </div>
                  </div>
                </div>
              </div>

              <div>
                <TabsTest />
                {renderTable()}
              </div>
            </div>
          )
        })}
      </div>
    )
  }

  return renderContent()
}
