import { BrowserRouter, Route, Routes } from "react-router-dom"
import Login from './components/Login'
import PageNotFound from "./components/PageNotFound"
import ExecutiveDashboard from "./components/executive/ExecutiveDashboard"
import CustomerDashboard from "./components/customer/CustomerDashboard"
 
const App = ()=> {
   return(
    <>
      <BrowserRouter >
        <Routes>
          <Route path="" element={<Login />}></Route>
          <Route path="/customer" element={<CustomerDashboard />}></Route>
          <Route path="/executive" element={<ExecutiveDashboard />}></Route>
          <Route path="/*" element={<PageNotFound />}></Route>
        </Routes>
      </BrowserRouter>
    </>
   )
}

export default App
