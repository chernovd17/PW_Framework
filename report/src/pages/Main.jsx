import { useSelector, useDispatch } from 'react-redux'
import { useEffect } from 'react';
import { TabsAside } from "../components/tabs/aside/TabsAside.jsx";
import { TabsMain } from "../components/tabs/main/TabsMain.jsx";
import { Statistics } from '../components/statistics/Statistics.jsx';
import { Tests } from "../components/test/Tests.jsx";
import { Reports } from "../components/reports/Reports.jsx";
import { setFileList } from '../redux/file-slice';

export function Main() {
  const dispatch = useDispatch()
  const activeTabAside = useSelector(state => state.tabs.tabAside)
  const activeTabMain = useSelector(state => state.tabs.tabMain)
  const fileInfo = useSelector(state => state.file.fileInfo)
  const testsAll = fileInfo.tests
  let suite = require.context('../../logger/', false);
  let files

  useEffect(() => {
    files = require.context('../../logger/suite/', false);
    let filesList = [];
    files.keys().forEach((key) => {
      const file = files(key);
      const fileContent = file.default;
      filesList.push(fileContent);
    });
    filesList.reverse()
    dispatch(setFileList(filesList))
  }, [suite])

  function renderContentAside() {
    switch (activeTabAside) {
      case 'tab1':
        return <Statistics info={fileInfo}/>
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
    if (testsAll) {
      const testsPassed = testsAll.filter(test => test.testStatus === 'SUCCESS')
      const testsFailed = testsAll.filter(test => test.testStatus === 'FAIL')
      const testsFatal = testsAll.filter(test => test.testStatus === 'FATAL')
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
  }

  return (
    <div className="d-flex">
      <TabsAside />
      {renderContentAside()}
    </div>
  )
}
