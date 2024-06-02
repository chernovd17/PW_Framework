import { useSelector, useDispatch } from 'react-redux'
import { useState, useEffect } from 'react';
import { TabsAside } from "../components/TabsAside";
import { TabsMain } from "../components/TabsMain";
import { Statistic } from '../components/Statistic';
import { Tests } from "../components/Tests";
import { Reports } from "../components/Reports";
import { setFile, setFileList } from '../redux/file-slice';
import trigger from '../..//logger/suites.txt';

export function Main() {
  const dispatch = useDispatch()
  const activeTabAside = useSelector(state => state.tabs.tabAside)
  const activeTabMain = useSelector(state => state.tabs.tabMain)
  const fileInfo = useSelector(state => state.file.fileInfo)
  const testsAll = fileInfo.tests
  let files

  let fetchData = async () => {
    let inf = await trigger;
    return await inf.text;
  }
  let f;

  useEffect(() => {
    f = fetchData();
    files = require.context('../../logger/suite/', false);
    let filesList = [];
    files.keys().forEach((key) => {
      const file = files(key);
      const fileContent = file.default;
      filesList.push(fileContent);
    });
    console.log(filesList)
    filesList.reverse()
    dispatch(setFileList(filesList))
    dispatch(setFile(filesList[0]))
  }, [f])

  function renderContentAside() {
    switch (activeTabAside) {
      case 'tab1':
        return <Statistic />
      case 'tab2':
        return <div className="w-100">
          <TabsMain />
          {renderContentMain()}
        </div>
      case 'tab3':
        return <Reports />
      default:
        return null
    }
  }

  function renderContentMain() {
    const testsPassed = testsAll.filter(test => test.testStatus == 'SUCCESS')
    const testsFailed = testsAll.filter(test => test.testStatus == 'FAIL')
    const testsFatal = testsAll.filter(test => test.testStatus == 'FATAL')
    switch (activeTabMain) {
      case 'tab1':
        return <Tests tests={testsAll} />
      case 'tab2':
        return <Tests tests={testsPassed} />
      case 'tab3':
        return <Tests tests={testsFailed} />
      case 'tab4':
        return <Tests tests={testsFatal} />
      default:
        return null
    }
  }

  return (
    <div className="d-flex">
      <TabsAside />
      {renderContentAside()}
    </div>
  )
}
