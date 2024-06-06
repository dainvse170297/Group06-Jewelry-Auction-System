import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom'
import 'bootstrap/dist/css/bootstrap.css';
import Home from './component/home/Home.jsx';
import StaffBody from './component/staff/StaffBody'
import ValuationRequestList from './component/staff/ValuationRequestList'
import StaffFunction from './component/staff/StaffFunction'
import ValuationRequestDetail from './component/staff/ValuationRequestDetail'
import ManagerBody from './component/manager/ManagerBody.jsx';
import CreateAuction from './component/manager/CreateAuction.jsx';
import FinalValuationRequestList from './component/manager/FinalValuationRequestList.jsx';
import FinalValuationRequestDetail from './component/manager/FinalValuationRequestDetail.jsx';
import ManagerApprovedList from './component/staff/ManagerApprovedList.jsx';
import ManagerApprovedDetail from './component/staff/ManagerApprovedDetail.jsx';

function App() {

  return (
    <>
      <BrowserRouter>

        <div className="App">
          <Routes>
            <Route path='/' element={<Navigate to='/home' />} />
            <Route path='/home' element={<Home />} />
            <Route path='/staff' element={<StaffBody />} />
            <Route path='/staff-function' element={<StaffFunction />} />
            <Route path='/valuation-request' element={<ValuationRequestList />} />
            <Route path='/valuation-request-detail/:id' element={<ValuationRequestDetail />} />
            <Route path='/manager' element={<ManagerBody />} />
            <Route path='/create-auction' element={<CreateAuction />} />
            <Route path='/final-valuation-request-list' element={<FinalValuationRequestList />} />
            <Route path='/final-valuation-request-detail/:id' element={<FinalValuationRequestDetail />} />
            <Route path='/manager-approved-List' element={<ManagerApprovedList />} />
            <Route path='/manager-approved-detail/:id' element={<ManagerApprovedDetail />} />
          </Routes>
        </div>

      </BrowserRouter>

    </>
  );
}

export default App;
